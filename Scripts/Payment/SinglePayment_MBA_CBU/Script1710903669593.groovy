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

import internal.GlobalVariable as GlobalVariable //retrieve data

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*

WebUI.delay(1)

WebUI.openBrowser('')

WebUI.maximizeWindow();

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtUsername'), GlobalVariable.user2)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtPassword'), GlobalVariable.pass2)

WebUI.doubleClick(findTestObject('Object Repository/LoginPage/itoggletxtPassword'))

WebUI.click(findTestObject('Object Repository/LoginPage/button_Login'))

WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/i_ri-parking-fill'), 30)

WebUI.click(findTestObject('Object Repository/PaymentModule/i_ri-parking-fill'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/PaymentModule/a_SinglePayment'))

def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

//get cid from the approved clients
// Specify the data file
def approvedClients = findTestData('ApprovedClients')
def rowCount = approvedClients.getRowNumbers()
println(rowCount)
if (rowCount >= 1) {
	// Iterate through rows
	for (int i = rowCount; i >= rowCount; i--) {
		// Get data for each column
		String cid = approvedClients.getValue('CID', i)
		String acc = approvedClients.getValue('ACC', i)
		String firstName = approvedClients.getValue('FIRSTNAME', i)
		String middleName = approvedClients.getValue('MIDDLENAME', i)
		String lastName = approvedClients.getValue('LASTNAME', i)
		
		println(cid)
		
		WebUI.setText(findTestObject('Object Repository/PaymentModule/inputtxtCid'), cid)
		
		WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/inputtxtCid'), Keys.chord(Keys.ENTER))
		
		WebUI.setText(findTestObject('Object Repository/PaymentModule/inputtxtNetCash'), "70")
	
		WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/inputtxtNetCash'), Keys.chord(Keys.ENTER))
		
		WebUI.setText(findTestObject('Object Repository/PaymentModule/inputinputPaid'), "70")//input payment
		
		WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/inputinputPaid'), Keys.chord(Keys.ENTER))
		
		String get_collect = driver.findElement(By.id('txtTotalCollection')).getAttribute('value')
		String get_val = driver.findElement(By.id('txtValidation')).getAttribute('value')
		
		Double parse_col = Double.parseDouble(get_collect)
		
		DecimalFormat pesoFormat = new DecimalFormat("#,##0.00");
		String formatted_getcol = pesoFormat.format(parse_col);
		
		if(formatted_getcol.equals("70.00"))
		{
			println("Collection display works! " + formatted_getcol)
		}
		else
		{
			println("Collection display not works! Expected: 0, Actual " + formatted_getcol)
			KeywordUtil.markFailed("ERROR: Collection display not works! Expected: 70, Actual " + formatted_getcol);
		}
		
		if(get_val.equals("0"))
		{
			println("Validation works! " + get_val)
		}
		else
		{
			println("Validation not works! Expected: 0, Actual " + get_val)
			KeywordUtil.markFailed("ERROR: Validation not works! Expected: 0, Actual " + get_val);
		}
		
		WebUI.click(findTestObject('Object Repository/PaymentModule/i_uil uil-file-edit-alt'))
		WebUI.delay(2)
		String ttl = driver.findElement(By.id('txtTotalSingleDetails')).getAttribute('value')
		
		if(ttl.equals("70.00"))
		{
			println("Total in view details is Correct! " + ttl)
			
			WebUI.click(findTestObject('Object Repository/PaymentModule/button_Done'))
			
			WebUI.click(findTestObject('Object Repository/PaymentModule/button_Post'))
			
			WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/inputtxtPRno'), 20)
			WebUI.setText(findTestObject('Object Repository/PaymentModule/inputtxtPRno'), '123')
			
			WebUI.click(findTestObject('Object Repository/PaymentModule/button_OK'))
			
			WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/div_Single Payment was successfully Posted'), 10)
			
			String msg_spay = WebUI.getText(findTestObject('Object Repository/PaymentModule/div_Single Payment was successfully Posted'))
			
			if(msg_spay == "Single Payment was successfully Posted")
			{
				println(msg_spay)
				WebUI.delay(2)
				
				Actions actions = new Actions(driver)
				actions.sendKeys(Keys.ENTER).perform()
				
				WebUI.delay(2)
				
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
						for (int j = rowCount; j >= rowCount; j--) {
							// Get data for each column
							String getcid = tbtransfered.getValue('CID', j)
							String getacc = tbtransfered.getValue('ACC', j)
							String getfname = tbtransfered.getValue('FIRSTNAME', j)
							String getmname = tbtransfered.getValue('MIDDLENAME', j)
							String getlname = tbtransfered.getValue('LASTNAME', j)
							
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
			}
			else
			{
				println("Single Payment Error msg: " + msg_spay)
				KeywordUtil.markFailed("Single Payment Error msg: " + msg_spay);
			}
		}
		else
		{
			println("Total is Incorrect! Expected: 70.00, Actual " + ttl)
			KeywordUtil.markFailed("ERROR: Validation not works! Expected: 70.00, Actual " + ttl);
		}
		
		
	}
}
else {
	println("Error: Data file contains no rows. - Please add and approve customer first!")
	KeywordUtil.markFailed("Error: Data file contains no rows. - Please add and approve customer first!");
}

WebUI.delay(2)
WebUI.verifyElementPresent(findTestObject('Object Repository/Logout/span_(Open)_account-user-avatar'), 30)
WebUI.click(findTestObject('Object Repository/Logout/span_(Open)_account-user-avatar'))

WebUI.click(findTestObject('Object Repository/Logout/i_ChangePassword_mdi mdi-logout me-1'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Logout/img'), 10)

println("Logout Successfull")

WebUI.closeBrowser()
