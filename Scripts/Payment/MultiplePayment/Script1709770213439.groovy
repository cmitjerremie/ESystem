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

WebUI.click(findTestObject('Object Repository/PaymentModule/MultiplePayment/li_MultiplePayment'))

def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Multiple Payment_txtCid'), '10000213')
WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Multiple Payment_txtCid'), Keys.chord(Keys.ENTER))

WebUI.setText(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_NetCash_txtNetCash'), "200")
WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_NetCash_txtNetCash'), Keys.chord(Keys.ENTER))

WebUI.setText(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Net, Ether_inputPaid10000220'), "200")
WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Net, Ether_inputPaid10000220'), Keys.chord(Keys.ENTER))

WebElement get_collect = driver.findElement(By.id('txtTotalCollection'))
String txt_collect = get_collect.getAttribute('value')

WebElement get_with = driver.findElement(By.id('txtTotalWithdraw'))
String txt_with = get_with.getAttribute('value')

WebElement get_val = driver.findElement(By.id('txtValidation'))
String txt_val = get_val.getAttribute('value')

//check if input amount is equal to collection display
if(txt_collect.equals("200.00"))
{
	println("Collection Displayed correctly: " + txt_collect)
}
else
{
	println("Collection Display is incorrect!: " + txt_collect + " Expected: 200.00")
	KeywordUtil.markFailed("Collection Display is incorrect!: " + txt_collect + " Expected: 200.00");
}

//check if withdraw display is not affected
if(txt_with.equals("0.00"))
{
	println("Withdraw was not affected!, great!: " + txt_with)
}
else
{
	println("Withdraw was affected!, Error!: " + txt_with + " Expected: 0.00")
	KeywordUtil.markFailed("Withdraw was affected!, Error!: " + txt_with + " Expected: 0.00");
}

//check if validation works
if(txt_val.equals("0"))
{
	println("Validation Works!, great!: " + txt_val)
}
else
{
	println("Validation Error!: " + txt_val + " Expected: 0")
	KeywordUtil.markFailed("Validation Error!: " + txt_val + " Expected: 0");
}

WebUI.setText(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Net, Ether_inputPai_c2'), "200")
WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Net, Ether_inputPai_c2'), Keys.chord(Keys.ENTER))

WebUI.setText(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Net, Ether_txtWithdraw10000220'), "100")
WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Net, Ether_txtWithdraw10000220'), Keys.chord(Keys.ENTER))

WebUI.setText(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Net, Ether_txtWithdraw_c2'), "100")
WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/MultiplePayment/input_Net, Ether_txtWithdraw_c2'), Keys.chord(Keys.ENTER))

String txt_collect2 = get_collect.getAttribute('value')
String txt_with2 = get_with.getAttribute('value')
String txt_val2 = get_val.getAttribute('value')

//check if input amount is equal to collection display
if(txt_collect2.equals("400.00"))
{
	println("Collection Displayed correctly: " + txt_collect2)
}
else
{
	println("Collection Display is incorrect!: " + txt_collect2 + " Expected: 400.00")
	KeywordUtil.markFailed("Collection Display is incorrect!: " + txt_collect2 + " Expected: 400.00");
}

//check if withdraw display is not affected
if(txt_with2.equals("200.00"))
{
	println("Withdraw was not affected!, great!: " + txt_with2)
}
else
{
	println("Withdraw was affected!, Error!: " + txt_with2 + " Expected: 200.00")
	KeywordUtil.markFailed("Withdraw was affected!, Error!: " + txt_with2 + " Expected: 200.00");
}

//check if validation works
if(txt_val.equals("0"))
{
	println("Validation Works!, great!: " + txt_val2)
}
else
{
	println("Validation Error!: " + txt_val2 + " Expected: 0")
	KeywordUtil.markFailed("Validation Error!: " + txt_val2 + " Expected: 0");
}





