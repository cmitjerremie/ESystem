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

WebUI.setText(findTestObject('Object Repository/CBU/inputtxtDepositAmount'), '1000')

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

WebUI.delay(1)

