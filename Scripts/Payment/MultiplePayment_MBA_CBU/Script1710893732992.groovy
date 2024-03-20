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
import org.openqa.selenium.By

import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.driver.DriverFactory //for alert

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.JavascriptExecutor;
import java.text.DecimalFormat;

import org.openqa.selenium.interactions.Actions;

import internal.GlobalVariable as GlobalVariable//retieve data
import java.text.DecimalFormat;

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*

WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/i_ri-parking-fill'), 50)

WebUI.click(findTestObject('Object Repository/PaymentModule/i_ri-parking-fill'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/PaymentModule/MultiplePayment/li_MultiplePayment'))

def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

// Specify the data file
def appClient = findTestData('ApprovedClients')
def cid_multiple = appClient.getValue('CID', 1)


WebUI.setText(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Multiple Payment_txtCid'), cid_multiple)
WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Multiple Payment_txtCid'), Keys.chord(Keys.ENTER))

// Get the number of rows in the data file
int rowCount = appClient.getRowNumbers()
int toPay = rowCount

int netcash = 70 * rowCount
println(netcash)
String stringNet = Integer.toString(netcash)
println(stringNet)
WebUI.setText(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_NetCash_txtNetCash'), stringNet)
WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_NetCash_txtNetCash'), Keys.chord(Keys.ENTER))

// Iterate through rows
for (int i = 1; i <= rowCount; i++) {
	// Get data for each column
	String cid = appClient.getValue('CID', i)
	
	WebElement pay = driver.findElement(By.id('inputPaid'+cid))
	// Scroll to the 'pay' element
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pay);
	pay.sendKeys('70')
	pay.sendKeys(Keys.ENTER);
}

WebElement get_collect = driver.findElement(By.id('txtTotalCollection'))
String txt_collect = get_collect.getAttribute('value')

DecimalFormat pesoFormat = new DecimalFormat("#,##0.00");
String formatted_netc = pesoFormat.format(netcash);

//check if input amount is equal to collection display
if(txt_collect == formatted_netc)
{
	println("Collection Displayed correctly: " + txt_collect)
}
else
{
	println("Collection Display is incorrect!: " + txt_collect + " Expected: " + formatted_netc)
	KeywordUtil.markFailed("Collection Display is incorrect!: " + txt_collect + " Expected: " + formatted_netc);
}

WebElement get_val = driver.findElement(By.id('txtValidation'))
String txt_val = get_val.getAttribute('value')

if(txt_val.equals("0"))
{
	println("Validation Works!, great!: " + txt_val)
}
else
{
	println("Validation Error!: " + txt_val + " Expected: 0")
	KeywordUtil.markFailed("Validation Error!: " + txt_val + " Expected: 0");
}

WebUI.delay(2)
WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/MultiplePayment/button_Post (1)'), 10)
WebUI.click(findTestObject('Object Repository/PaymentModule/MultiplePayment/button_Post (1)'))

WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/MultiplePayment/h4_Please Enterthe PR Number'), 30)
WebUI.verifyTextPresent("Please Enter the PR Number.", false)
WebUI.setText(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Please Enterthe PR Number_txtPRno'), "123")
WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/MultiplePayment/button_OK'), 10)
WebUI.click(findTestObject('Object Repository/PaymentModule/MultiplePayment/button_OK'))

Actions actions = new Actions(driver)
actions.sendKeys(Keys.ENTER).perform()
WebUI.delay(3)
actions.sendKeys(Keys.ENTER).perform()

WebUI.delay(2)

String parentWindowHandle = driver.getWindowHandle()

// Get all window handles
Set<String> allWindowHandles = driver.getWindowHandles()

// Iterate through all handles and close the newly opened tab
for (String windowHandle : allWindowHandles) {
	if (!windowHandle.equals(parentWindowHandle)) {
		
		def tbtransfered = findTestData('ApprovedClients')
		
		// Iterate through rows
		for (int i = 1; i <= rowCount; i++) {
			// Get data for each column
			String getcid = tbtransfered.getValue('CID', i)
			String getacc = tbtransfered.getValue('ACC', i)
			String getfname = tbtransfered.getValue('FIRSTNAME', i)
			String getmname = tbtransfered.getValue('MIDDLENAME', i)
			String getlname = tbtransfered.getValue('LASTNAME', i)
			
			// Specify the data file
			def DoneInitial = findTestData('DoneInitialPay')
			
			// Get the path to the Excel file
			String excelFilePath = DoneInitial.getSourceUrl()
			
			// Open the Excel workbook
			FileInputStream fis = new FileInputStream(excelFilePath)
			XSSFWorkbook workbook = new XSSFWorkbook(fis)
			
			// Get the default sheet (assuming there is only one sheet)
			Sheet sheet = workbook.getSheetAt(2)
			
			// Find the last row index (add 1 to get the next available row)
			int lastRowIndex = sheet.getLastRowNum() + 1
			
			// Create a new row
			Row newRow = sheet.createRow(lastRowIndex)
			
			// Add data to the new row
			newRow.createCell(0).setCellValue(getcid)
			newRow.createCell(1).setCellValue(getacc)
			newRow.createCell(2).setCellValue(getfname)
			newRow.createCell(3).setCellValue(getmname)
			newRow.createCell(4).setCellValue(getlname)
			
			// Save the changes
			FileOutputStream fos = new FileOutputStream(excelFilePath)
			workbook.write(fos)
			
			// Close the FileInputStream and workbook
			fis.close()
			fos.close()
			workbook.close()//ApprovedClients
			
			// Print the added data
			println("Added new record")
			
			//delete the current record once transfered
			// Specify the data file
			def tbDeleted = findTestData('ApprovedClients')
			
			// Get the path to the Excel file
			String excelFilePath2 = tbDeleted.getSourceUrl()
			
			// Open the Excel workbook
			FileInputStream fis2 = new FileInputStream(excelFilePath2)
			XSSFWorkbook workbook2 = new XSSFWorkbook(fis2)
			
			Sheet sheet2 = workbook2.getSheetAt(1)
			
			// Define the values of FIRSTNAME, MIDDLENAME, and LASTNAME to delete
			def cidToDelete = getcid
			def accToDelete = getacc
			def firstNameToDelete = getfname
			def middleNameToDelete = getmname
			def lastNameToDelete = getlname
			
			// Loop through each row to find and delete the row containing the specified values
			for (int rowIndex2 = sheet2.getLastRowNum(); rowIndex2 >= 0; rowIndex2--) {
				Row row2 = sheet2.getRow(rowIndex2)
				if (row2 != null) {
					Cell cidCell = row2.getCell(0)
					Cell accCell = row2.getCell(1)
					Cell firstNameCell = row2.getCell(2)
					Cell middleNameCell = row2.getCell(3)
					Cell lastNameCell = row2.getCell(4)
					
					// Check if the values match the ones to be deleted
					if (cidCell != null && cidCell.getStringCellValue() == cidToDelete &&
						accCell != null && accCell.getStringCellValue() == accToDelete &&
						firstNameCell != null && firstNameCell.getStringCellValue() == firstNameToDelete &&
						middleNameCell != null && middleNameCell.getStringCellValue() == middleNameToDelete &&
						lastNameCell != null && lastNameCell.getStringCellValue() == lastNameToDelete) {
						// Delete the row if found
						sheet2.removeRow(row2)
						// Shift rows up to fill the gap
						if (rowIndex2 < sheet2.getLastRowNum()) {
							sheet.shiftRows(rowIndex2 + 1, sheet2.getLastRowNum(), -1)
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
			workbook2.close()//DoneInitial
			
			// Print a message indicating the deletion
			println("Data successfully transfered before deleted!")	
		}
		
		
		driver.switchTo().window(windowHandle)
		driver.close()
	}
}

// Switch back to the parent window
driver.switchTo().window(parentWindowHandle)






