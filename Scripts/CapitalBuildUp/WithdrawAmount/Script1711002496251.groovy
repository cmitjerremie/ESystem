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
	
	//Test Withdraw
	WebUI.click(findTestObject('Object Repository/CBU/span_Withdrawal'))
	
	WebUI.setText(findTestObject('Object Repository/CBU/inputtxtWithdrawAmount'), "101")
	
	DecimalFormat pesoFormat = new DecimalFormat("#,##0.00");
	
	WebElement w_get_element = driver.findElement(By.id("txtWithdrawBengBal"))
	String wget_beg_bal = w_get_element.getAttribute("value").replace(",", "")
	
	Double parse_beg_bal = Double.parseDouble(wget_beg_bal)
	String formatted_wnew_beg = pesoFormat.format(parse_beg_bal);
	
	//verify if ending amount is displayed correctly
	WebElement w_ending_dis = driver.findElement(By.id("txtWithdrawEndBal"))
	String get_ending = w_ending_dis.getAttribute('value').replace(",", "")
	Double parse_wEnding = Double.parseDouble(get_ending)
	double with_amount = 101.00;
	
	Double w_expected_end = parse_beg_bal - with_amount;
	String formatted_expected = pesoFormat.format(w_expected_end);
	
	if(formatted_expected == w_ending_dis.getAttribute('value'))
	{
		println("Withdrawal Ending Balance: " + w_ending_dis.getAttribute('value') + " Expected: " + formatted_expected)
		println("Withdrawal Ending Balance display correct!")
	}
	else
	{
		println("Withdrawal Ending Balance: " + w_ending_dis.getAttribute('value') + " Expected: " + formatted_expected)
		KeywordUtil.markFailed("ERROR: Incorrect Withdrawal Ending Balance!");
	}
	
	WebElement wNew_begBal = driver.findElement(By.id('txtWithdrawBengBal'))
	
	WebUI.click(findTestObject('Object Repository/CBU/button_Post_1'))
	
	WebUI.click(findTestObject('Object Repository/CBU/button_Cancel_wd'))
	
	WebUI.click(findTestObject('Object Repository/CBU/button_Post_1'))
	
	WebUI.click(findTestObject('Object Repository/CBU/button_Yes'))
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/CBU/div_i  SuccessfulWithdrawal of Cash has bee_9370dc'), 10)
	
	String new_beg_Wbalance = wNew_begBal.getAttribute('value')
	if(new_beg_Wbalance.equals(formatted_expected))
	{
		println("Withdrawal new beggining balance is Successfully updated: " + new_beg_Wbalance)
	}
	else
	{
		println("Error: Withdrawal new beggining was not changed: " + new_beg_Wbalance + "Expected: " + formatted_expected)
		KeywordUtil.markFailed("Error: Withdrawal new beggining was not changed: " + new_beg_Wbalance + "Expected: " + formatted_expected)
	}
	
	WebUI.delay(1)
	WebUI.click(findTestObject('Object Repository/CBU/button_OK'))
	
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
	
	WebUI.setText(findTestObject('Object Repository/CBU/input_Search_form-control form-control-sm'), "101.00")
	WebUI.sendKeys(findTestObject('Object Repository/CBU/input_Search_form-control form-control-sm'), Keys.chord(Keys.ENTER))
	
	WebUI.delay(2)
	
	TestObject tableObject2 = findTestObject('Object Repository/CBU/div_Transaction DateTRNTRN DescriptionUsern_71bb85')
	String tableText2 = WebUI.getText(tableObject2)
	
	WebUI.delay(1)
	
	// Check if table has results
	if (tableText2.contains("No matching records found")) {
		println("Table has no results.")
		KeywordUtil.markFailed("ERROR: Table has no results. Transactions should be displayed! - withdraw 101.00");
	} else {
		println("Table has results.")
	}
}
else {
    println("Error: Data file contains no rows.")
	KeywordUtil.markFailed("Error: Data file contains no rows.");
}

