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

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.interactions.Actions;


WebUI.delay(1)

WebUI.waitForElementVisible(findTestObject('Object Repository/LoanManagementModule/LoanManagementNav/a_Loan'), 30)

WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanManagementNav/a_Loan'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/a_LoanDisbursement'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/input_Search_form-control form-control-sm'), GlobalVariable.cid)
WebUI.sendKeys(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/input_Search_form-control form-control-sm'), Keys.chord(Keys.ENTER))

TestObject dis_table = findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/LoanDisbursementTable')

// Extract the text content of the table
String tableContent = WebUI.getText(dis_table)

// Define the expected result
String expectedResult = GlobalVariable.cid

// Verify if the table content contains the expected result
if (tableContent.contains(expectedResult)) {
	println(tableContent)
	println("The table contains the expected result: ${expectedResult}")
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/i_SIKAP 1_ri-checkbox-circle-fill'), 10)
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/i_SIKAP 1_ri-checkbox-circle-fill'))
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/h4_Are yousure you want to disburse this loan'), 10)
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/button_YES'))
	
	WebUI.delay(1)
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/div_Loan has been Released'), 10)
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/button_OK'))
	
	WebUI.delay(1)
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/h2_Amortization Generated'), 10)
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDisbursement/button_OK'))
		
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
	
	WebUI.delay(3)
	
	
} else {
	println("The table does not contain the expected result: ${expectedResult}")
	KeywordUtil.markFailed("PLEASE CHECK YOUR CLIENT NAME IF ALREADY DONE CLIENT CREATION");
}