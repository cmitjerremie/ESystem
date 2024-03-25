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

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions

import internal.GlobalVariable as GlobalVariable // to retrieve data in datafile
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*//delete data in datafile


WebUI.click(findTestObject('Object Repository/ClientApproval/i_ri-user-3-line'))

WebUI.click(findTestObject('Object Repository/ClientApproval/a_ClientApproval'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

// Specify the data file
def pendingCustomer = findTestData('CreatedClients')

// Get the number of rows in the data file
int rowCount = pendingCustomer.getRowNumbers()

if (rowCount >= 1) {
	// Iterate through rows
	for (int i = rowCount; i >= rowCount; i--) {
		// Get data for each column
		String firstName = pendingCustomer.getValue('FIRSTNAME', i)
		String middleName = pendingCustomer.getValue('MIDDLENAME', i)
		String lastName = pendingCustomer.getValue('LASTNAME', i)
	
		String tbapproved = firstName + " " + middleName + " " + lastName
	
		WebUI.setText(findTestObject('Object Repository/ClientApproval/input_form-control form-control-sm'), tbapproved)//PLease refer to global variable //GlobalVariable.app_name
		
		//verify the search results
		
		TestObject app_table = findTestObject('Object Repository/ClientApproval/ApprovalTable_Client')
		
		// Extract the text content of the table
		String tableContent = WebUI.getText(app_table)
		
		// Define the expected result
		String expectedResult = tbapproved
	
		// Verify if the table content contains the expected result
		if (tableContent.contains(expectedResult)) {
			println(tableContent)
			println("The table contains the expected result: ${expectedResult}")
			
	//		WebUI.waitForElementClickable(findTestObject('Object Repository/ClientApproval/i_mdi mdi-check-bold'), 10)
	//		WebUI.click(findTestObject('Object Repository/ClientApproval/i_mdi mdi-check-bold'))
	//		WebUI.click(findTestObject('Object Repository/ClientApproval/button_Yes'))
			
			WebUI.delay(2)
//			WebUI.click(findTestObject('Object Repository/ClientApproval/DisapproveClient/i_Dayap 1_ri-close-line'))
			WebElement disapp = driver.findElement(By.className('ri-close-line'))
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", disapp);
			
			WebUI.verifyElementPresent(findTestObject('Object Repository/ClientApproval/DisapproveClient/h2_Are you sure you want to disapprove this client'), 20)
			WebUI.click(findTestObject('Object Repository/ClientApproval/DisapproveClient/button_Cancel'))
			
			WebUI.delay(2)
			
			WebElement disapp2 = driver.findElement(By.className('ri-close-line'))
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", disapp2);
			
			WebUI.verifyElementPresent(findTestObject('Object Repository/ClientApproval/DisapproveClient/h2_Are you sure you want to disapprove this client'), 20)
			WebUI.click(findTestObject('Object Repository/ClientApproval/DisapproveClient/button_Yes'))
			
			WebUI.waitForElementVisible(findTestObject('Object Repository/ClientApproval/DisapproveClient/h2_Message'), 10)
			WebUI.waitForElementVisible(findTestObject('Object Repository/ClientApproval/DisapproveClient/div_This Record is Rejected'), 10)
			
			String msg_approvedhdr = WebUI.getText(findTestObject('Object Repository/ClientApproval/DisapproveClient/h2_Message'))
			String msg_approved = WebUI.getText(findTestObject('Object Repository/ClientApproval/DisapproveClient/div_This Record is Rejected'))
			//Message for the meantime but "Success!" in real scenario
			if(msg_approvedhdr == "Message" && msg_approved == "This Record is Rejected!")
			{
				WebUI.delay(1)
				WebUI.click(findTestObject('Object Repository/ClientApproval/button_OK'))
				//delete data in first sheet
				// Specify the data file
				def tbdeleted = findTestData('CreatedClients') // Assuming 'Customer' is the name of the data file
				
				// Get the path to the Excel file
				String excelFilePath2 = tbdeleted.getSourceUrl()
				
				// Open the Excel workbook
				FileInputStream fis2 = new FileInputStream(excelFilePath2)
				XSSFWorkbook workbook2 = new XSSFWorkbook(fis2)
				
				// Get the default sheet (assuming there is only one sheet)
				Sheet sheet2 = workbook2.getSheetAt(0)
				
				// Define the values of FIRSTNAME, MIDDLENAME, and LASTNAME to delete
				def firstNameToDelete = firstName
				def middleNameToDelete = middleName
				def lastNameToDelete = lastName
				
				// Loop through each row to find and delete the row containing the specified values
				for (int rowIndex2 = sheet2.getLastRowNum(); rowIndex2 >= 0; rowIndex2--) {
					Row row2 = sheet2.getRow(rowIndex2)
					if (row2 != null) {
						Cell firstNameCell = row2.getCell(0)
						Cell middleNameCell = row2.getCell(1)
						Cell lastNameCell = row2.getCell(2)
						
						// Check if the values match the ones to be deleted
						if (firstNameCell != null && firstNameCell.getStringCellValue() == firstNameToDelete &&
							middleNameCell != null && middleNameCell.getStringCellValue() == middleNameToDelete &&
							lastNameCell != null && lastNameCell.getStringCellValue() == lastNameToDelete) {
							// Delete the row if found
							sheet2.removeRow(row2)
							// Shift rows up to fill the gap
							if (rowIndex2 < sheet2.getLastRowNum()) {
								sheet2.shiftRows(rowIndex2 + 1, sheet2.getLastRowNum(), -1)
							}
							break
						}
					}
				}
				
				// Save the changes
				FileOutputStream fos2 = new FileOutputStream(excelFilePath2)
				workbook2.write(fos2)
				
				// Close the FileInputStream and workbook
				fis2.close()
				fos2.close()
				workbook2.close()
				
				// Print a message indicating the deletion
				println("Deleted data based on FIRSTNAME, MIDDLENAME, and LASTNAME")
				
	//			WebUI.click(findTestObject('Object Repository/ClientApproval/button_OK'))
				
				Actions btn_ok = new Actions(driver)
				btn_ok.sendKeys(Keys.ENTER).perform()
			}
			else
			{
				KeywordUtil.markFailed("ERROR in client approval " + msg_approved);
			}
			
		} else {
			println("The table does not contain the expected result: ${expectedResult}")
			KeywordUtil.markFailed("PLEASE CHECK YOUR CLIENT NAME IF ALREADY DONE CLIENT CREATION");
		}
	}
}
else {
	println("Error: Data file contains no rows.- Please create client first")
	KeywordUtil.markFailed("Error: Data file contains no rows. - Please create client first");
}

