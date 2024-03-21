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

import com.kms.katalon.core.testobject.TestObject

import org.openqa.selenium.Alert
import com.kms.katalon.core.webui.driver.DriverFactory //for alert

import org.openqa.selenium.WebElement //for id
import org.openqa.selenium.By

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import java.text.DecimalFormat;

import com.kms.katalon.core.util.KeywordUtil

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;

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

WebUI.verifyElementPresent(findTestObject('Object Repository/Dashboard/div_DashboardWelcome to your dashboard Were glad to have you here'), 50)

println('Dashboard Successfully Displayed')

WebUI.waitForElementClickable(findTestObject('Object Repository/ClientAmendment/a_Client'), 30)
WebUI.click(findTestObject('Object Repository/ClientAmendment/a_Client'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/ClientAmendment/a_Inquiryand Amendment'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_CID_txtCID'), GlobalVariable.cid_ammend)

WebUI.sendKeys(findTestObject('Object Repository/ClientAmendment/input_CID_txtCID'), Keys.chord(Keys.ENTER))

WebUI.delay(5)

WebUI.waitForElementVisible(findTestObject('Object Repository/ClientAmendment/h4_Customers'), 10)

WebUI.verifyElementText(findTestObject('Object Repository/ClientAmendment/h4_Customers'), 'Customers')

WebUI.verifyElementPresent(findTestObject('Object Repository/ClientAmendment/div_Show 102550100 entriesCIDFullNameBirthd_897106_1'),
	0)

WebUI.click(findTestObject('Object Repository/ClientAmendment/i_Active_mdi mdi-magnify'))

WebUI.waitForElementClickable(findTestObject('Object Repository/ClientAmendment/Inquire_Transaction_History/span_Transaction History'), 10)
WebUI.click(findTestObject('Object Repository/ClientAmendment/Inquire_Transaction_History/span_Transaction History'))

// Get today's date
LocalDate today = LocalDate.now()

// Define the date format
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy")

// Format today's date
String formattedDate = today.format(formatter)

WebUI.waitForElementClickable(findTestObject('Object Repository/ClientAmendment/Inquire_Transaction_History/input_Date Start_txtDTStartTranHistory'), 10)

WebUI.waitForElementClickable(findTestObject('Object Repository/ClientAmendment/Inquire_Transaction_History/input_Date End_txtDTEndTranHistory'), 10)

WebUI.setText(findTestObject('Object Repository/ClientAmendment/Inquire_Transaction_History/input_Date Start_txtDTStartTranHistory'), "01012024")
WebUI.setText(findTestObject('Object Repository/ClientAmendment/Inquire_Transaction_History/input_Date End_txtDTEndTranHistory'), formattedDate)

WebUI.sendKeys(findTestObject('Object Repository/ClientAmendment/Inquire_Transaction_History/input_Date End_txtDTEndTranHistory'), Keys.ENTER.toString())

TestObject tableObject = findTestObject('Object Repository/ClientAmendment/Inquire_Transaction_History/Inquire_Table_History')

// Extract table contents
String tableText = WebUI.getText(tableObject)

// Check if table has results
if (tableText.contains("No data available in table")) {
	println("Table has no results.")
	KeywordUtil.markFailed("ERROR: Table has no results. Transactions should be displayed!, or check your cid");
} else {
	println("Table has results.")
}