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
import com.thoughtworks.selenium.webdriven.commands.GetAttribute

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import org.openqa.selenium.Alert
import com.kms.katalon.core.webui.driver.DriverFactory //for alert

import com.kms.katalon.core.util.KeywordUtil
import java.text.DecimalFormat;

import org.openqa.selenium.WebElement
import org.openqa.selenium.By

import java.time.LocalDate
import java.time.format.DateTimeFormatter

WebUI.delay(1)

WebUI.waitForElementClickable(findTestObject('Object Repository/CBU/i_uil-wallet'), 30)

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/CBU/i_uil-wallet'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/CBU/inputtxtCID'), GlobalVariable.cbu_cid)

WebUI.sendKeys(findTestObject('Object Repository/CBU/inputtxtCID'), Keys.chord(Keys.ENTER))

WebUI.click(findTestObject('Object Repository/CBU/span_Deposit'))

WebElement get_element = driver.findElement(By.id("txtDepositEndBal"))
String get_end_bal = get_element.getAttribute("value")

if(!get_end_bal.equals("0.00"))
{
	println(get_end_bal)
	KeywordUtil.markFailed("ERROR: Ending Balance has unexpected value!");
	WebUI.delay(500)
}

WebUI.setText(findTestObject('Object Repository/CBU/inputtxtDepositAmount'), GlobalVariable.cbu_deposit)

WebElement get_element2 = driver.findElement(By.id("txtDepositBengBal"))
String get_beg_bal = get_element2.getAttribute("value").replace(",", "")

double begBalance = Double.parseDouble(get_beg_bal);
double dep_amount = Double.parseDouble(GlobalVariable.cbu_deposit);

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

WebUI.waitForElementVisible(findTestObject('Object Repository/CBU/div_i  SuccessfulDeposit of Cash  has been _650bb7'), 10)

WebUI.click(findTestObject('Object Repository/CBU/button_OK'))

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

String new_beg_bal = get_element2.getAttribute("value").replace(",", "")//verify if the beginning is correct
double parsed_new_beg_bal = Double.parseDouble(new_beg_bal);
String formatted_new_beg = pesoFormat.format(parsed_new_beg_bal);

if(formatted_new_beg == formatted_cal)
{
	println("Beg Balance: " + formatted_new_beg + " Expected: " + formatted_cal)
	println("New Balance Works!")
}
else
{
	println("Beg Balance: " + formatted_new_beg + " Expected: " + formatted_cal)
	KeywordUtil.markFailed("ERROR: Incorrect New Beginning Balance!");
}

WebUI.delay(1)

//Test Withdraw
WebUI.click(findTestObject('Object Repository/CBU/span_Withdrawal'))

String wExpected_beg_bal = formatted_new_beg;

WebElement w_get_element = driver.findElement(By.id("txtDepositBengBal"))
String wget_beg_bal = w_get_element.getAttribute("value").replace(",", "")

Double parse_beg_bal = Double.parseDouble(wget_beg_bal)
String formatted_wnew_beg = pesoFormat.format(parse_beg_bal);

println(formatted_wnew_beg + " = " + wExpected_beg_bal)
if(wExpected_beg_bal == formatted_wnew_beg)
{
	println("Beg Balance: " + formatted_wnew_beg + " Expected: " + wExpected_beg_bal)
	println("Withdrawal Beginning Balance Correct!")
}
else
{
	println("Beg Balance: " + formatted_wnew_beg + " Expected: " + wExpected_beg_bal)
	KeywordUtil.markFailed("ERROR: Incorrect Withdrawal Beginning Balance!");
}

WebUI.setText(findTestObject('Object Repository/CBU/inputtxtWithdrawAmount'), GlobalVariable.cbu_withdraw)

//verify if ending amount is displayed correctly
WebElement w_ending_dis = driver.findElement(By.id("txtWithdrawEndBal"))
String get_ending = w_ending_dis.getAttribute('value').replace(",", "")
Double parse_wEnding = Double.parseDouble(get_ending)
double with_amount = Double.parseDouble(GlobalVariable.cbu_withdraw);

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

WebUI.click(findTestObject('Object Repository/CBU/button_Cancel_1'))

WebUI.click(findTestObject('Object Repository/CBU/button_Post_1'))

WebUI.click(findTestObject('Object Repository/CBU/button_Yes'))

WebUI.waitForElementVisible(findTestObject('Object Repository/CBU/div_i  SuccessfulWithdrawal of Cash has bee_9370dc'), 10)

WebUI.click(findTestObject('Object Repository/CBU/button_OK'))

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

WebUI.setText(findTestObject('Object Repository/CBU/input_Search_form-control form-control-sm'), GlobalVariable.cbu_deposit)
WebUI.sendKeys(findTestObject('Object Repository/CBU/input_Search_form-control form-control-sm'), Keys.chord(Keys.ENTER))

WebUI.delay(1)

if (tableText.contains(GlobalVariable.cbu_deposit)) {
	println("Transaction search success! - Deposit")
} else {
	println("Table has no results.")
	KeywordUtil.markFailed("ERROR: Table has no results. Transactions should be displayed! - deposit search");
}

WebUI.setText(findTestObject('Object Repository/CBU/input_Search_form-control form-control-sm'), "")
WebUI.setText(findTestObject('Object Repository/CBU/input_Search_form-control form-control-sm'), GlobalVariable.cbu_withdraw)
WebUI.sendKeys(findTestObject('Object Repository/CBU/input_Search_form-control form-control-sm'), Keys.chord(Keys.ENTER))

WebUI.delay(1)

if (tableText.contains(GlobalVariable.cbu_withdraw)) {
	println("Transaction search success! - Withdraw")
} else {
	println("Table has no results.")
	KeywordUtil.markFailed("ERROR: Table has no results. Transactions should be displayed! - withdraw search");
}


