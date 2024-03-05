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

WebUI.delay(1)

WebUI.openBrowser('')

WebUI.maximizeWindow();

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtUsername'), GlobalVariable.user2)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtPassword'), GlobalVariable.pass2)

WebUI.doubleClick(findTestObject('Object Repository/LoginPage/itoggletxtPassword'))

WebUI.click(findTestObject('Object Repository/LoginPage/button_Login'))

//WebUI.waitForElementClickable(findTestObject('Object Repository/ForValidation/i_ri-parking-fill'), 30)

WebUI.click(findTestObject('Object Repository/PaymentModule/i_ri-parking-fill'))

WebUI.click(findTestObject('Object Repository/PaymentModule/a_SinglePayment'))

def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/PaymentModule/inputtxtCid'), '10000219')

WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/inputtxtCid'), Keys.chord(Keys.ENTER))

WebUI.setText(findTestObject('Object Repository/PaymentModule/inputtxtNetCash'), '1,405')

WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/inputtxtNetCash'), Keys.chord(Keys.ENTER))

WebUI.setText(findTestObject('Object Repository/PaymentModule/inputinputPaid'), '1,405')

WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/inputinputPaid'), Keys.chord(Keys.ENTER))

String txt_due = WebUI.getText(findTestObject('Object Repository/PaymentModule/ForValidation/td_1,405.00'))
String txt_valdue = WebUI.getText(findTestObject('Object Repository/PaymentModule/ForValidation/input_Total Due_txtTotalDue'))
String txt_collect = WebUI.getText(findTestObject('Object Repository/PaymentModule/ForValidation/input_Total Collection_txtTotalCollection'))
String txt_val = WebUI.getText(findTestObject('Object Repository/PaymentModule/ForValidation/input_Validation_txtValidation'))

if(txt_due.equals(txt_valdue))
{
	println("Total Due validated! " + txt_due +" == " + txt_valdue)
}
else
{
	println("Incorrect Total Due Display " + txt_due +" != " + txt_valdue)
	KeywordUtil.markFailed("ERROR: Incorrect Due display");
}

WebUI.click(findTestObject('Object Repository/ForValidation/i_uil uil-file-edit-alt'))

WebUI.click(findTestObject('Object Repository/ForValidation/button_Done'))

WebUI.click(findTestObject('Object Repository/ForValidation/button_Post'))

WebUI.setText(findTestObject('Object Repository/ForValidation/inputtxtPRno'), '123')

