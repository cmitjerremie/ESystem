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

WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/a_PaymentAdjustment'))

def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

//credit CBU using cash on hand*************************

String CID1 = "10000325"
String CID2 = "10000324"

//WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid1'), 10)
//WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid1'), CID1 + Keys.chord(Keys.ENTER))
//
//WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputcbu_credit_c1'), '50')
//
//WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Capital-Build Up_txtCashonHand1'))
//
//WebUI.delay(1)
//
//WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputCashOnHand_debit_c1'), '50')
//
//String validation1 = driver.findElement(By.id('txtValidation')).getAttribute('value')
//println(validation1)
//
//if(validation1.equals('0.00'))
//{
//	println("Validation is correct!: " + validation1)
//}
//else
//{
//	KeywordUtil.markFailed("Validation is Incorrect! Expected: 0 Actual: " + validation1);
//}
//
//WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_PostTransaction'))
//WebUI.delay(1)
//WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Are you sure you want to proceed this Transaction'), 10)
//WebUI.verifyTextPresent("Are you sure you want to proceed this Transaction?", true)
//WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Cancel'), 10)
//WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Cancel'))
//WebUI.delay(1)
//WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_PostTransaction'))
//WebUI.delay(1)
//WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Are you sure you want to proceed this Transaction'), 10)
//WebUI.verifyTextPresent("Are you sure you want to proceed this Transaction?", true)
//WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Yes'), 10)
//WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Yes'))
//WebUI.delay(2)
//WebUI.waitForElementPresent(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Transaction Posted'), 10)
//WebUI.verifyTextPresent("Transaction Posted.", true)
//
//WebUI.delay(2)
//Actions action1 = new Actions(driver)
//action1.sendKeys(Keys.ENTER).perform()
//
//WebUI.delay(2)
//
////debit CBU using cash on hand*************************
//
//WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid1'), 10)
//WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid1'), CID1 + Keys.chord(Keys.ENTER))
//
//WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputcbu_debit_c1'), '50')
//
//WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Capital-Build Up_txtCashonHand1'))
//
//WebUI.delay(1)
//
//WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputCashOnHand_credit_c1'), '50')
//
//String validation2 = driver.findElement(By.id('txtValidation')).getAttribute('value')
//println(validation2)
//
//if(validation2.equals('0.00'))
//{
//	println("Validation is correct!: " + validation2)
//}
//else
//{
//	KeywordUtil.markFailed("Validation is Incorrect! Expected: 0 Actual: " + validation2);
//}
//
//WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_PostTransaction'))
//WebUI.delay(1)
//WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Are you sure you want to proceed this Transaction'), 10)
//WebUI.verifyTextPresent("Are you sure you want to proceed this Transaction?", true)
//WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Cancel'), 10)
//WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Cancel'))
//WebUI.delay(1)
//WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_PostTransaction'))
//WebUI.delay(1)
//WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Are you sure you want to proceed this Transaction'), 10)
//WebUI.verifyTextPresent("Are you sure you want to proceed this Transaction?", true)
//WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Yes'), 10)
//WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Yes'))
//WebUI.delay(2)
//WebUI.waitForElementPresent(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Transaction Posted'), 10)
//WebUI.verifyTextPresent("Transaction Posted.", true)
//
//WebUI.delay(2)
//Actions action2 = new Actions(driver)
//action2.sendKeys(Keys.ENTER).perform()

//debit CBU from client 1 and credit to client 2*************************

WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid1'), 10)
WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid1'), CID1 + Keys.chord(Keys.ENTER))

WebUI.delay(1)

WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/i_Cash on Hand_ri-add-circle-fill'), 10)
WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/i_Cash on Hand_ri-add-circle-fill'))

WebUI.delay(1)

WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid2'), 10)
WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid2'), CID2 + Keys.chord(Keys.ENTER))

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputcbu_debit_c1'), '50')
WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputcbu_credit_c2'), '50')

WebUI.scrollToElement(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_PostTransaction'), 10)

String validation2 = driver.findElement(By.id('txtValidation')).getAttribute('value')
println(validation2)

if(validation2.equals('0.00'))
{
	println("Validation is correct!: " + validation2)
}
else
{
	KeywordUtil.markFailed("Validation is Incorrect! Expected: 0 Actual: " + validation2);
}




