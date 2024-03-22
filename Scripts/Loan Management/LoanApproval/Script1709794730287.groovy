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

import internal.GlobalVariable as GlobalVariable
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*


WebUI.delay(1)

WebUI.waitForElementVisible(findTestObject('Object Repository/LoanManagementModule/LoanManagementNav/a_Loan'), 30)

WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanManagementNav/a_Loan'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanApproval/a_LoanApproval'))

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
	// Get data for each column
	String cid = approveLoan.getValue('CID', i)

	WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanApproval/input_Search_form-control form-control-sm'), cid) //GlobalVariable.cid
	WebUI.sendKeys(findTestObject('Object Repository/LoanManagementModule/LoanApproval/input_Search_form-control form-control-sm'), Keys.chord(Keys.ENTER))
	
	TestObject app_table = findTestObject('Object Repository/LoanManagementModule/LoanApproval/LoanApprovalTable')
	
	// Extract the text content of the table
	String tableContent = WebUI.getText(app_table)
	
	// Define the expected result
	String expectedResult = cid
	
	// Verify if the table content contains the expected result
	if (tableContent.contains(expectedResult)) {
		println(tableContent)
		println("The table contains the expected result: ${expectedResult}")
		
		WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanApproval/i_SIKAP 1_mdi mdi-magnify'), 10)
		WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanApproval/i_SIKAP 1_mdi mdi-magnify'))
		
		WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanApproval/strong_Loan Information'), 10)
		WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanApproval/button_Approve'))
		
		WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanApproval/h2_Are you sure you want to approve this loan'), 10)
		
		WebUI.waitForElementClickable(findTestObject('Object Repository/LoanManagementModule/LoanApproval/button_Cancel_approval'), 10)
		WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanApproval/button_Cancel_approval'))
		WebUI.delay(1)
		
		WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanApproval/button_Approve'), 10)
		WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanApproval/button_Approve'))
		
		WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanApproval/h2_Are you sure you want to approve this loan'), 10)
		
		WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanApproval/button_Yes'), 10)
		WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanApproval/button_Yes'))
		WebUI.delay(1)
		
		WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanApproval/div_Loan has been approved'), 10)
		WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanApproval/h2_Successful_LoanApproved'), 10)
		String msg_success = WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanApproval/h2_Successful_LoanApproved'), 10)
		
		if(msg_success == "Successful!")
		{
			// Specify the data file
			def tbDeleted = findTestData('LoanApproval')
			
			// Get the path to the Excel file
			String excelFilePath = tbDeleted.getSourceUrl()
			
			// Open the Excel workbook
			FileInputStream fis = new FileInputStream(excelFilePath)
			XSSFWorkbook workbook = new XSSFWorkbook(fis)
			
			Sheet sheet = workbook.getSheetAt(4)
			
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
			
			
			//transfer cid to "Loan disbursement" sheet
			// Specify the data file
			def tbforDisbursement = findTestData('LoanDisbursement')
			
			// Get the path to the Excel file
			String excelFilePath2 = tbforDisbursement.getSourceUrl()
			
			// Open the Excel workbook
			FileInputStream fis2 = new FileInputStream(excelFilePath2)
			XSSFWorkbook workbook2 = new XSSFWorkbook(fis2)
			
			Sheet sheet2 = workbook2.getSheetAt(5)
			
			// Find the last row index (add 1 to get the next available row)
			int lastRowIndex2 = sheet2.getLastRowNum() + 1
			
			// Create a new row
			Row newRow2 = sheet2.createRow(lastRowIndex2)
			
			// Add data to the new row
			newRow2.createCell(0).setCellValue(cid)
			
			// Save the changes
			FileOutputStream fos2 = new FileOutputStream(excelFilePath2)
			workbook2.write(fos2)
			
			// Close the FileInputStream and workbook
			fis2.close()
			fos2.close()
			workbook2.close()
			
			// Print the added data
			println("Added cid for disbursement")
		}	
		
		WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanApproval/button_OK'))
		WebUI.delay(2)	
		
		
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