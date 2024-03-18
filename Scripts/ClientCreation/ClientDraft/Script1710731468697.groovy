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

import org.openqa.selenium.WebElement //for id
import org.openqa.selenium.By

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import java.text.DecimalFormat;

import com.kms.katalon.core.util.KeywordUtil

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions
import java.util.Random



WebUI.click(findTestObject('Object Repository/ClientApproval/i_Dashboard_ri-user-3-line'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/ClientCreation/a_ClientCreation'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_FirstName_txtFname'), "Test")

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_LastName_txtLname'), "Draft")

WebUI.click(findTestObject('Object Repository/ClientCreation/button_SEARCH'))

WebUI.click(findTestObject('Object Repository/ClientCreation/button_CREATE'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--NewTransferred'), '1820', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_-- Select --4Ps BeneficiaryArtistChi_05c006'),
	'1787', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_-- Select --Atty.Dr.Eng.Hon.MrMrsMs'), '155',
	true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--AnnulledLive-inMarriedSepa_54a068'),
	'165', true)

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_FirstName_txtmothersMaidenFName'), "Draft "+'Mname')

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_LastName_txtmothersMaidenLName'), "Draft "+'Lname')

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--AglipayanBorn AgainEvangel_87720a'),
	'0', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--AglipayanBorn AgainEvangel_87720a'),
	'422', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--No Formal EducationElement_aa261c'),
	'173', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--BusinessGovernment Employe_69ab64'),
	'491', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--Accommodation and Food Ser_e45bf9'),
	'687', true)

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_Birthday_txtbday'), '01011999')
WebUI.sendKeys(findTestObject('Object Repository/ClientCreation/input_Birthday_txtbday'), Keys.ENTER.toString())


WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --Abra - CAR (Co_3be376'),
	'10488', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --AlaminosBayBia_8f7d3f'),
	'10548', true)

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_0.00'), '09123456780')

WebUI.selectOptionByLabel(findTestObject('Object Repository/ClientCreation/select_--Select--farmfarm 2farm 3'), GlobalVariable.unit, true)

WebUI.selectOptionByLabel(findTestObject('Object Repository/ClientCreation/select_--      Select      --villagevillage_79bc42'),
	GlobalVariable.center, true)

WebUI.click(findTestObject('Object Repository/ClientCreation/input_YES_CARDScholar'))

WebUI.click(findTestObject('Object Repository/ClientCreation/input_YES_FormerEmployee'))

WebUI.delay(2)
((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -500);");

WebUI.waitForElementClickable(findTestObject('Object Repository/Page_Core/button_Saveas draft'), 10)
WebUI.click(findTestObject('Object Repository/Page_Core/button_Saveas draft'))

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/h2_Are you sure you want to save this as draft'), 10)
WebUI.verifyTextPresent("Are you sure you want to save this as draft?", true)

WebUI.waitForElementClickable(findTestObject('Object Repository/Page_Core/button_Cancel'), 10)
WebUI.click(findTestObject('Object Repository/Page_Core/button_Cancel'))

WebUI.delay(1)
WebUI.waitForElementClickable(findTestObject('Object Repository/Page_Core/button_Saveas draft'), 10)
WebUI.click(findTestObject('Object Repository/Page_Core/button_Saveas draft'))
WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/h2_Are you sure you want to save this as draft'), 10)
WebUI.verifyTextPresent("Are you sure you want to save this as draft?", true)

WebUI.waitForElementClickable(findTestObject('Object Repository/Page_Core/button_Yes'), 10)
WebUI.click(findTestObject('Object Repository/Page_Core/button_Yes'))

WebUI.delay(1)
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/h2_The Record has been successfully saved as a draft'), 10)
WebUI.verifyTextPresent("The Record has been successfully saved as a draft", true)
WebUI.delay(1)

Actions actions3 = new Actions(driver)
// Simulate pressing the Enter key using the Actions class
actions3.sendKeys(Keys.ENTER).perform()

WebUI.delay(2)

WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Core/h4_Draft in Customer Creation'), 10)
WebUI.verifyTextPresent("Draft in Customer Creation", true)




