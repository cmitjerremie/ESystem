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

import com.kms.katalon.core.testobject.TestObject
import org.openqa.selenium.Alert
import com.kms.katalon.core.webui.driver.DriverFactory //for alert
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable as GlobalVariable
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement

import org.openqa.selenium.interactions.Actions;


WebUI.waitForElementClickable(findTestObject('Object Repository/ClientAmendment/a_Client'), 30)
WebUI.click(findTestObject('Object Repository/ClientAmendment/a_Client'))

WebUI.delay(1)

WebUI.waitForElementClickable(findTestObject('Object Repository/ClientApproval/a_ClientApproval'), 10)
WebUI.click(findTestObject('Object Repository/ClientApproval/a_ClientApproval'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/ClientAmendment/ClientAmmendmentApproval/span_Amendment'))

// Specify the data file
def getPending = findTestData('ForAmendment')

// Get the number of rows in the data file
int rowCount = getPending.getRowNumbers()
println(rowCount)

if (rowCount >= 1) {
	// Iterate through rows
	for (int i = rowCount; i >= rowCount; i--) {
		// Get data for each column
		String cid = getPending.getValue('CID', i)
	
		WebUI.setText(findTestObject('Object Repository/ClientAmendment/ClientAmmendmentApproval/input_Search_form-control form-control-sm'), cid) //GlobalVariable.cid_ammend
		
		WebUI.sendKeys(findTestObject('Object Repository/ClientAmendment/ClientAmmendmentApproval/input_Search_form-control form-control-sm'), Keys.chord(Keys.ENTER))
		
		TestObject tableObject = findTestObject('Object Repository/CBU/div_Transaction DateTRNTRN DescriptionUsern_71bb85')
		
		// Extract table contents
		String tableText = WebUI.getText(findTestObject('Object Repository/ClientAmendment/ClientAmmendmentApproval/div_CIDFull NameBirthdayAreaUnitCenterActio_9cffad'))
		
		// Check if table has results
		if (tableText.contains("No data available in table")) {
			println("ERROR: Table has no results. Something went wrong or check your cid!")
			KeywordUtil.markFailed("ERROR: Table has no results. Something went wrong or check your cid!");
		} else {
			println("Table has results.")
		}
		
		WebUI.delay(1)
		WebElement reject = driver.findElement(By.className('ri-close-line'))
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", reject);
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/ClientAmendment/AmendmentDisapprove/h2_Are you sure you want to disapprove this Amendment'), 10)
		
		WebUI.delay(1)
		WebUI.click(findTestObject('Object Repository/ClientAmendment/AmendmentDisapprove/button_Cancel'))
		
		WebUI.delay(1)
		
		WebElement reject2 = driver.findElement(By.className('ri-close-line'))
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", reject2);
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/ClientAmendment/AmendmentDisapprove/h2_Are you sure you want to disapprove this Amendment'), 10)
		
		WebUI.delay(1)
		
		WebUI.click(findTestObject('Object Repository/ClientAmendment/AmendmentDisapprove/button_Yes'))
		
		WebUI.delay(1)
		WebUI.waitForElementVisible(findTestObject('Object Repository/ClientAmendment/AmendmentDisapprove/h2_Successful'), 10)
		WebUI.delay(1)
		String msg_disApp = WebUI.getText(findTestObject('Object Repository/ClientAmendment/AmendmentDisapprove/h2_Successful'))
		
		//Message for the meantime but "Successful!" in real scenario
		if(msg_disApp == "Successful!")
		{
			Actions actions = new Actions(DriverFactory.getWebDriver())
			actions.sendKeys(Keys.ENTER).perform()
			// Specify the data file
			def tbDeleted = findTestData('ForAmendment')
			
			// Get the path to the Excel file
			String excelFilePath2 = tbDeleted.getSourceUrl()
			
			// Open the Excel workbook
			FileInputStream fis2 = new FileInputStream(excelFilePath2)
			XSSFWorkbook workbook2 = new XSSFWorkbook(fis2)
			
			// Get the default sheet (assuming there is only one sheet)
			Sheet sheet2 = workbook2.getSheetAt(3)
			
			def cidToDelete = cid
			
			// Loop through each row to find and delete the row containing the specified values
			for (int rowIndex = sheet2.getLastRowNum(); rowIndex >= 0; rowIndex--) {
				Row row2 = sheet2.getRow(rowIndex)
				if (row2 != null) {
					Cell cidNameCell = row2.getCell(0)
					
					// Check if the values match the ones to be deleted
					if (cidNameCell != null && cidNameCell.getStringCellValue() == cidToDelete){
						// Delete the row if found
						println("Row Deleted")
						sheet2.removeRow(row2)
						// Shift rows up to fill the gap
						if (rowIndex < sheet2.getLastRowNum()) {
							sheet2.shiftRows(rowIndex + 1, sheet2.getLastRowNum(), -1)
						}
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
			println("Approved CID Deleted")
		}
		else
		{
			println("Something went wrong in amendment approval! msg: " + msg_disApp)
			KeywordUtil.markFailed("Something went wrong in amendment approval! msg: " + msg_disApp);
		}
	
	}
}
else {
	println("Error: Data file contains no rows. - Please make sure to have pending amendment!")
	KeywordUtil.markFailed("Error: Data file contains no rows. - Please make sure to have pending amendment!");
}

