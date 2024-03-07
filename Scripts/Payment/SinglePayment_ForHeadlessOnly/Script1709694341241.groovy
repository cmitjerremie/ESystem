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

// Set up Chrome options for headless mode and specify window size
ChromeOptions options = new ChromeOptions()
options.addArguments("--headless")
options.addArguments("--window-size=1920,1080") // Adjust as per your requirement

// Set the path to the ChromeDriver executable
String chromeDriverPath = "Drivers/chromedriver.exe"

// Set the system property for ChromeDriver
System.setProperty("webdriver.chrome.driver", chromeDriverPath)

// Initialize the ChromeDriver with ChromeOptions
ChromeDriver driver111 = new ChromeDriver(options)

// Set the driver to Katalon DriverFactory
DriverFactory.changeWebDriver(driver111)

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

WebUI.setText(findTestObject('Object Repository/PaymentModule/inputtxtCid'), GlobalVariable.payment_cid)

WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/inputtxtCid'), Keys.chord(Keys.ENTER))

String loan_due = WebUI.getText(findTestObject('Object Repository/PaymentModule/ForValidation/td_1,405.00'))//get loan due amount

WebUI.setText(findTestObject('Object Repository/PaymentModule/inputtxtNetCash'), loan_due)

WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/inputtxtNetCash'), Keys.chord(Keys.ENTER))

WebUI.setText(findTestObject('Object Repository/PaymentModule/inputinputPaid'), loan_due)//input payment

WebUI.sendKeys(findTestObject('Object Repository/PaymentModule/inputinputPaid'), Keys.chord(Keys.ENTER))

WebElement get_val_due = driver.findElement(By.id('txtTotalDue'))
WebElement get_collect = driver.findElement(By.id('txtTotalCollection'))
WebElement get_val = driver.findElement(By.id('txtValidation'))

String txt_val_due = get_val_due.getAttribute('value')
String txt_collect = get_collect.getAttribute('value')
String txt_val = get_val.getAttribute('value')

if(loan_due.equals(txt_val_due) && loan_due.equals(txt_collect) && txt_val.equals("0"))
{
	println("Payment -Due, Collection, and validation works!")
}
else
{
	println("Payment -Due, Collection, and validation works!")
	KeywordUtil.markFailed("ERROR: Incorrect Display, please check the Due, Collection, and validation");
}

WebUI.click(findTestObject('Object Repository/PaymentModule/i_uil uil-file-edit-alt'))

//String mba = WebUI.getText(findTestObject('Object Repository/PaymentModule/ForValidation/input_MBA PREMIUM_inputField_1'))
//String due = WebUI.getText(findTestObject('Object Repository/PaymentModule/ForValidation/input_AGRI LOAN_inputField_2'))
//String cbu = WebUI.getText(findTestObject('Object Repository/PaymentModule/ForValidation/input_Capital-Build Up_inputField_3'))
//String ttl = WebUI.getText(findTestObject('Object Repository/PaymentModule/ForValidation/input_Total_txtTotalSingleDetails'))

String mba = driver.findElement(By.id('inputField_1')).getAttribute('value')
String due = driver.findElement(By.id('inputField_2')).getAttribute('value')
String cbu = driver.findElement(By.id('inputField_3')).getAttribute('value')
String ttl = driver.findElement(By.id('txtTotalSingleDetails')).getAttribute('value')

String get_loanDue = loan_due.replace(",","")
Double parse_loanDue = Double.parseDouble(get_loanDue)
Double dis_due = parse_loanDue - 70

DecimalFormat pesoFormat = new DecimalFormat("#,##0.00");
String formatted_dis_due = pesoFormat.format(dis_due);

if(mba.equals("20.00") && due.equals(formatted_dis_due) && cbu.equals("50.00") && ttl.equals(loan_due))
{
	println("MBA: " + mba + " == 20.00 Due:" + due + " == " + formatted_dis_due + " cbu: " + cbu + " == 50.00 Total: " + ttl + " == " + loan_due)
	println("Payment view details - Validation Works!")
}
else
{
	println("MBA: " + mba + " != 20.00 Due:" + due + " != " + formatted_dis_due + " cbu: " + cbu + " != 50.00 Total: " + ttl + " != " + loan_due)
	KeywordUtil.markFailed("ERROR: Incorrect Display, Payment view details!");
}

WebUI.click(findTestObject('Object Repository/PaymentModule/button_Done'))

WebUI.click(findTestObject('Object Repository/PaymentModule/button_Post'))

WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/inputtxtPRno'), 20)

WebUI.setText(findTestObject('Object Repository/PaymentModule/inputtxtPRno'), '123')

WebUI.click(findTestObject('Object Repository/PaymentModule/button_OK'))

WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/div_Single Payment was successfully Posted'), 10)

String msg = WebUI.getText(findTestObject('Object Repository/PaymentModule/div_Single Payment was successfully Posted'))

if(msg.equals("Single Payment was successfully Posted"))
{
	println("Single Payment was successfully Posted")
}
else
{
	println("ERROR: " + msg + " is incorrect!")
	KeywordUtil.markFailed("ERROR: Error in posting payment please check!");
	
}
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
		driver.switchTo().window(windowHandle)
		driver.close()
	}
}

// Switch back to the parent window
driver.switchTo().window(parentWindowHandle)

WebUI.delay(2)
WebUI.verifyElementPresent(findTestObject('Object Repository/Logout/span_(Open)_account-user-avatar'), 30)
WebUI.click(findTestObject('Object Repository/Logout/span_(Open)_account-user-avatar'))

WebUI.click(findTestObject('Object Repository/Logout/i_ChangePassword_mdi mdi-logout me-1'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Logout/img'), 10)

println("Logout Successfull")

WebUI.closeBrowser()
