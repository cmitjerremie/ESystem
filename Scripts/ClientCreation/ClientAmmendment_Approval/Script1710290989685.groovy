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
import com.kms.katalon.core.util.KeywordUtil


WebUI.waitForElementClickable(findTestObject('Object Repository/ClientAmmendment/a_Client'), 30)
WebUI.click(findTestObject('Object Repository/ClientAmmendment/a_Client'))

WebUI.delay(1)

WebUI.waitForElementClickable(findTestObject('Object Repository/ClientApproval/a_ClientApproval'), 10)
WebUI.click(findTestObject('Object Repository/ClientApproval/a_ClientApproval'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/ClientAmmendment/ClientAmmendmentApproval/span_Amendment'))

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/ClientAmmendmentApproval/input_Search_form-control form-control-sm'), GlobalVariable.cid_ammend)

WebUI.sendKeys(findTestObject('Object Repository/ClientAmmendment/ClientAmmendmentApproval/input_Search_form-control form-control-sm'), Keys.chord(Keys.ENTER))

TestObject tableObject = findTestObject('Object Repository/CBU/div_Transaction DateTRNTRN DescriptionUsern_71bb85')

// Extract table contents
String tableText = WebUI.getText(findTestObject('Object Repository/ClientAmmendment/ClientAmmendmentApproval/div_CIDFull NameBirthdayAreaUnitCenterActio_9cffad'))

// Check if table has results
if (tableText.contains("No data available in table")) {
	println("ERROR: Table has no results. Something went wrong or check your cid!")
	KeywordUtil.markFailed("ERROR: Table has no results. Something went wrong or check your cid!");
} else {
	println("Table has results.")
}

WebUI.delay(1)
WebUI.click(findTestObject('Object Repository/ClientAmmendment/ClientAmmendmentApproval/i_Village_mdi mdi-check-bold'))

WebUI.delay(1)
WebUI.click(findTestObject('Object Repository/ClientAmmendment/ClientAmmendmentApproval/button_Cancel'))

WebUI.delay(1)
WebUI.click(findTestObject('Object Repository/ClientAmmendment/ClientAmmendmentApproval/i_Village_mdi mdi-check-bold'))

WebUI.delay(1)
WebUI.click(findTestObject('Object Repository/ClientAmmendment/ClientAmmendmentApproval/button_Yes'))

WebUI.delay(1)
WebUI.waitForElementVisible(findTestObject('Object Repository/ClientAmmendment/ClientAmmendmentApproval/div_Record has been Successfuly Saved'), 0)
WebUI.verifyElementText(findTestObject('Object Repository/ClientAmmendment/ClientAmmendmentApproval/div_Record has been Successfuly Saved'), 'Record has been Successfuly Saved')

WebUI.click(findTestObject('Object Repository/ClientAmmendment/ClientAmmendmentApproval/button_OK'))

