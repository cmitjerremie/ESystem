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

import org.openqa.selenium.interactions.Actions;
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Alert

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions

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

WebUI.delay(10)
// Get the WebDriver instance
WebDriver driver = DriverFactory.getWebDriver()

try {    
    // Switch to the alert
    Alert alert = driver.switchTo().alert()

    // Accept the alert
    alert.accept()
} catch (Exception e) {
    // Handle the scenario where the alert does not appear
    println("Alert did not appear.")
}

//WebUI.waitForElementVisible(findTestObject('Object Repository/LoginPage/h2_Login Successful'), 10, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Object Repository/Dashboard/div_DashboardWelcome to your dashboard Were glad to have you here'), 50)

println('Dashboard Successfully Displayed')

WebUI.delay(1)