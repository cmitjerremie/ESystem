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

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.util.KeywordUtil

import org.openqa.selenium.WebElement
import org.openqa.selenium.By

import com.kms.katalon.core.util.KeywordUtil
import java.text.DecimalFormat;

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/CBU/i_uil-wallet'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

// Specify the data file
def addCBU = findTestData('DoneInitialPay')

// Get the number of rows in the data file
int rowCount = addCBU.getRowNumbers()

if (rowCount >= 1) {
	String randomCID
    // Iterate through rows
    for (int i = 1; i <= rowCount; i++) {
        // Generate a random index to select a CID
        Random random = new Random()
        int randomIndex = random.nextInt(rowCount) + 1
        
        // Get the data for the CID column based on the random index
        randomCID = addCBU.getValue('CID', randomIndex)
    }
        
    WebUI.setText(findTestObject('Object Repository/CBU/inputtxtCID'), randomCID) //GlobalVariable.cbu_cid

	WebUI.sendKeys(findTestObject('Object Repository/CBU/inputtxtCID'), Keys.chord(Keys.ENTER))
	
	WebElement get_element = driver.findElement(By.id("txtDepositEndBal"))
	String get_end_bal = get_element.getAttribute("value")
	
	WebUI.click(findTestObject('Object Repository/CBU/span_Deposit'))
	
	WebUI.setText(findTestObject('Object Repository/CBU/inputtxtDepositAmount'), '1050')
	
	WebElement get_element2 = driver.findElement(By.id("txtDepositBengBal"))
	String get_beg_bal = get_element2.getAttribute("value").replace(",", "")
	
	double begBalance = Double.parseDouble(get_beg_bal);
	double dep_amount = 1050.00;
	
	String get_new_end_bal = get_element.getAttribute("value").replace(",", "")
	double new_endBalance = Double.parseDouble(get_new_end_bal);
	
	double cal_ending = begBalance + dep_amount;
	
	DecimalFormat pesoFormat = new DecimalFormat("#,##0.00");
	
	String formatted_beg = pesoFormat.format(begBalance);
	String formatted_cal = pesoFormat.format(cal_ending);
	String formatted_end = pesoFormat.format(new_endBalance);
	
	if(formatted_end == formatted_cal)
	{
		println("Beg Balance: " + formatted_beg + " + Dep Amount: " + dep_amount + " Ending: " + formatted_end)
		println("Deposit Works")
	}
	else
	{
		println("Beg Balance: " + formatted_beg + " + Dep Amount: " + dep_amount + " Ending: " + formatted_end)
		KeywordUtil.markFailed("ERROR: Incorrect deposit computation!");
	}
	
	WebUI.click(findTestObject('Object Repository/CBU/button_Post'))
	
	WebUI.click(findTestObject('Object Repository/CBU/button_Cancel'))
	
	WebUI.click(findTestObject('Object Repository/CBU/button_Post'))
	
	WebUI.click(findTestObject('Object Repository/CBU/button_Yes'))
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/CBU/h2_Successful'), 10)
	WebUI.waitForElementVisible(findTestObject('Object Repository/CBU/div_Deposit of Cash  has been successfully posted'), 10)
	String msg_dep = WebUI.getText(findTestObject('Object Repository/CBU/div_Deposit of Cash  has been successfully posted'))
	if(msg_dep == "Deposit of Cash has been successfully posted.")
	{
		println(msg_dep)
		WebUI.click(findTestObject('Object Repository/CBU/button_OK'))
		WebUI.delay(1)
		WebUI.click(findTestObject('Object Repository/CBU/button_OK'))
		
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
		
		WebUI.delay(2)
		
		//check history
		WebUI.click(findTestObject('Object Repository/CBU/span_Transaction History'))
		
		// Get today's date
		LocalDate today = LocalDate.now()
		
		// Define the date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy")
		
		// Format today's date
		String formattedDate = today.format(formatter)
		
		// Enter the formatted date into the text fields
		WebUI.setText(findTestObject('Object Repository/CBU/input_Date Start_dtMStart'), formattedDate)
		WebUI.setText(findTestObject('Object Repository/CBU/input_Date End_dtMEnd'), formattedDate)
		
		WebUI.sendKeys(findTestObject('Object Repository/CBU/input_Date End_dtMEnd'), Keys.ENTER.toString())
		
		WebUI.delay(1)
		
		TestObject tableObject = findTestObject('Object Repository/CBU/div_Transaction DateTRNTRN DescriptionUsern_71bb85')
		
		// Extract table contents
		String tableText = WebUI.getText(tableObject)
		
		// Check if table has results
		if (tableText.contains("No data available in table")) {
			println("Table has no results.")
			KeywordUtil.markFailed("ERROR: Table has no results. Transactions should be displayed!");
		} else {
			println("Table has results.")
		}
		
		WebUI.setText(findTestObject('Object Repository/CBU/input_Search_form-control form-control-sm'), "1,050.00")
		WebUI.sendKeys(findTestObject('Object Repository/CBU/input_Search_form-control form-control-sm'), Keys.chord(Keys.ENTER))
		
		WebUI.delay(2)
		
		TestObject tableObject2 = findTestObject('Object Repository/CBU/div_Transaction DateTRNTRN DescriptionUsern_71bb85')
		String tableText2 = WebUI.getText(tableObject2)
		
		WebUI.delay(1)
		
		// Check if table has results
		if (tableText2.contains("No matching records found")) {
			println("Table has no results.")
			KeywordUtil.markFailed("ERROR: Table has no results. Transactions should be displayed! - deposit 1,050.00");
		} else {
			println("Table has results.")
		}
	}
	else
	{
		println("Error in deposit amount msg: " + msg_dep)
		KeywordUtil.markFailed("ERROR in deposit amount! msg: " + msg_dep);
	}
}
else {
    println("Error: Data file contains no rows.")
	KeywordUtil.markFailed("Error: Data file contains no rows.");
}

