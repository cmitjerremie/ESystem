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

WebUI.openBrowser('')

WebUI.maximizeWindow();

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtUsername'), GlobalVariable.user2)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtPassword'), GlobalVariable.pass2)

WebUI.doubleClick(findTestObject('Object Repository/LoginPage/itoggletxtPassword'))

WebUI.click(findTestObject('Object Repository/LoginPage/button_Login'))

//WebUI.waitForElementVisible(findTestObject('Object Repository/LoginPage/h2_Login Successful'), 10, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Object Repository/Dashboard/div_DashboardWelcome to your dashboard Were glad to have you here'), 50)

println('Dashboard Successfully Displayed')

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/UserManagement/span_Admin'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/UserManagement/a_UserManagement'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.waitForElementClickable(findTestObject('Object Repository/UserManagement/input_Search_form-control form-control-sm'), 20)
WebUI.setText(findTestObject('Object Repository/UserManagement/input_Search_form-control form-control-sm'), 'zcresurrec')

WebUI.delay(1)
WebUI.click(findTestObject('Object Repository/UserManagement/i_Active_mdi mdi-pencil-outline'))

WebUI.selectOptionByLabel(findTestObject('Object Repository/UserManagement/select_-- Select --Area One 505Batangas 1Bg_d2c5c8'), 
    'Bukid', true)

WebUI.selectOptionByLabel(findTestObject('Object Repository/UserManagement/select_--      Select      --Account Office_5f278f'), 'Bookkeeper', true)

WebUI.click(findTestObject('Object Repository/UserManagement/button_Update'))

WebUI.delay(2)
WebUI.verifyElementPresent(findTestObject('Object Repository/Logout/span_(Open)_account-user-avatar'), 30)
WebUI.click(findTestObject('Object Repository/Logout/span_(Open)_account-user-avatar'))

WebUI.click(findTestObject('Object Repository/Logout/i_ChangePassword_mdi mdi-logout me-1'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Logout/img'), 10)

println("Logout Successfull")

WebUI.closeBrowser()

