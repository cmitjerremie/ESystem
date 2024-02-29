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

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions


WebUI.click(findTestObject('Object Repository/ClientApproval/i_ri-user-3-line'))

WebUI.click(findTestObject('Object Repository/ClientApproval/a_ClientApproval'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/ClientApproval/input_form-control form-control-sm'), GlobalVariable.app_name)//PLease refer to global variable

//verify the search results

TestObject app_table = findTestObject('Object Repository/ClientApproval/ApprovalTable_Client')

// Extract the text content of the table
String tableContent = WebUI.getText(app_table)

// Define the expected result
String expectedResult = GlobalVariable.app_name

// Verify if the table content contains the expected result
if (tableContent.contains(expectedResult)) {
	println(tableContent)
	println("The table contains the expected result: ${expectedResult}")
	WebUI.click(findTestObject('Object Repository/ClientApproval/i_mdi mdi-check-bold'))
	
	WebUI.click(findTestObject('Object Repository/ClientApproval/button_Yes'))
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/ClientApproval/div_New client has been approved CID-100002_073c72'),
	    0)
	
	String get_info = WebUI.getText(findTestObject('Object Repository/ClientApproval/div_New client has been approved CID-100002_073c72')) 
	def matcher = (get_info =~ /CID-(\d+) \/ ACC-(\d+-\d+-\d+)/)
	matcher.find()
	String cid = matcher.group(1)
	String acc = matcher.group(2)
	
	GlobalVariable.gen_cid = cid
	GlobalVariable.gen_acc = acc
	
	println(GlobalVariable.app_name+ " CID: " + GlobalVariable.gen_cid + " ACC NO: " + GlobalVariable.gen_acc)
	
	WebUI.click(findTestObject('Object Repository/ClientApproval/button_OK'))
} else {
	println("The table does not contain the expected result: ${expectedResult}")
	KeywordUtil.markFailed("PLEASE CHECK YOUR CLIENT NAME IF ALREADY DONE CLIENT CREATION");
}

