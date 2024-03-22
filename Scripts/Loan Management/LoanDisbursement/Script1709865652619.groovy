import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import org.openqa.selenium.Alert
import com.kms.katalon.core.webui.driver.DriverFactory //for alert

import org.openqa.selenium.WebElement //for id
import org.openqa.selenium.By

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import java.text.DecimalFormat;

import com.kms.katalon.core.util.KeywordUtil

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.interactions.Actions;

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*


WebUI.delay(1)

WebUI.waitForElementVisible(findTestObject('Object Repository/LoanManagementModule/LoanManagementNav/a_Loan'), 30)

WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanManagementNav/a_Loan'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/a_LoanDisbursement'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

// Specify the data file
def approveLoan = findTestData('LoanApproval')

// Get the number of rows in the data file
int rowCount = approveLoan.getRowNumbers()

if (rowCount >= 1) {
	// Iterate through rows
	for (int i = rowCount; i >= 1; i--) {
		String cid = approveLoan.getValue('CID', i)
		
		WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/input_Search_form-control form-control-sm'), cid)
		WebUI.sendKeys(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/input_Search_form-control form-control-sm'), Keys.chord(Keys.ENTER))
		
		TestObject dis_table = findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/LoanDisbursementTable')
		
		// Extract the text content of the table
		String tableContent = WebUI.getText(dis_table)
		
		// Define the expected result
		String expectedResult = GlobalVariable.cid
		
		// Verify if the table content contains the expected result
		if (tableContent.contains(expectedResult)) {
			println(tableContent)
			println("The table contains the expected result: ${expectedResult}")
			
			WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/i_SIKAP 1_ri-checkbox-circle-fill'), 10)
			WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/i_SIKAP 1_ri-checkbox-circle-fill'))
			
			WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/h4_Are yousure you want to disburse this loan'), 10)
			WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/button_YES'))
			
			WebUI.delay(1)
			
			String msg_rls = WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/div_Loan has been Released'), 10)
			
			if(msg_rls == "Loan has been Released")
			{
				WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/button_OK'))
				
				WebUI.delay(1)
				
				// Specify the data file
				def tbDeleted = findTestData('LoanDisbursement')
				
				// Get the path to the Excel file
				String excelFilePath = tbDeleted.getSourceUrl()
				
				// Open the Excel workbook
				FileInputStream fis = new FileInputStream(excelFilePath)
				XSSFWorkbook workbook = new XSSFWorkbook(fis)
				
				Sheet sheet = workbook.getSheetAt(5)
				
				def cidToDelete = cid
				
				// Loop through each row to find and delete the row containing the specified values
				for (int rowIndex = sheet.getLastRowNum(); rowIndex >= 0; rowIndex--) {
					Row row = sheet.getRow(rowIndex)
					if (row != null) {
						Cell cidCell = row.getCell(0)
						
						// Check if the values match the ones to be deleted
						if (cidCell != null && cidCell.getStringCellValue() == cidToDelete ) {
							// Delete the row if found
							sheet.removeRow(row)
							// Shift rows up to fill the gap
							if (rowIndex < sheet.getLastRowNum()) {
								sheet.shiftRows(rowIndex + 1, sheet.getLastRowNum(), -1)
							}
							break
						}
					}
				}
				
				// Save the changes
				FileOutputStream fos = new FileOutputStream(excelFilePath)
				workbook.write(fos)
				
				// Close the FileInputStream and workbook
				fis.close()
				fos.close()
				workbook.close()
				
				// Print a message indicating the deletion
				println("Data Deleted, Loan Approved!")
				
				WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/h2_Amortization Generated'), 10)
				WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/button_OK'))
					
				WebUI.delay(2)
				
				String parentWindowHandle = driver.getWindowHandle()
				
				// Get all window handles
				Set<String> allWindowHandles = driver.getWindowHandles()
				
				// Iterate through all handles and close the newly opened tab
				for (String windowHandle : allWindowHandles) {
					if (!windowHandle.equals(parentWindowHandle)) {
						driver.switchTo().window(windowHandle)
						driver.close()
					}
				}
				
				// Switch back to the parent window
				driver.switchTo().window(parentWindowHandle)
				
				WebUI.delay(3)
			}
			else
			{
				KeywordUtil.markFailed("ERROR in Loan Disbursement" + msg_rls);
			}
			
		} else {
			println("The table does not contain the expected result: ${expectedResult}")
			KeywordUtil.markFailed("PLEASE CHECK YOUR CLIENT NAME IF ALREADY DONE CLIENT CREATION");
		}
		
	}
}
else {
	println("Error: Data file contains no rows.")
	KeywordUtil.markFailed("Error: Data file contains no rows.");
}