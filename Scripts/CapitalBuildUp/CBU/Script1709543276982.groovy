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

import com.kms.katalon.core.util.KeywordUtil
import java.text.DecimalFormat;

import org.openqa.selenium.WebElement
import org.openqa.selenium.By

WebUI.openBrowser('')

WebUI.maximizeWindow();

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtUsername'), GlobalVariable.user2)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtPassword'), GlobalVariable.pass2)

WebUI.doubleClick(findTestObject('Object Repository/LoginPage/itoggletxtPassword'))

WebUI.click(findTestObject('Object Repository/LoginPage/button_Login'))

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
//double w_beg_bal = pesoFormat.format(begBalance);
//WebUI.click(findTestObject('Object Repository/CBU/span_Withdrawal'))
//
//WebUI.setText(findTestObject('Object Repository/CBU/inputtxtWithdrawAmount'), '500')
//
//WebUI.click(findTestObject('Object Repository/CBU/button_Cancel_1'))
//
//WebUI.setText(findTestObject('Object Repository/CBU/inputtxtWithdrawAmount'), '500')
//
//WebUI.click(findTestObject('Object Repository/CBU/button_Post_1'))
//
//WebUI.click(findTestObject('Object Repository/CBU/button_Cancel'))
//
//WebUI.click(findTestObject('Object Repository/CBU/button_Post_1'))
//
//WebUI.click(findTestObject('Object Repository/CBU/button_Yes'))
//
//WebUI.click(findTestObject('Object Repository/CBU/button_OK'))


