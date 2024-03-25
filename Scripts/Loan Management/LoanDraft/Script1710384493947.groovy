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

WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanManagementNav/a_Loan'))

WebUI.delay(1)

WebUI.waitForElementVisible(findTestObject('Object Repository/LoanManagementModule/LoanManagementNav/a_LoanManagement'), 10)
WebUI.waitForElementClickable(findTestObject('Object Repository/LoanManagementModule/LoanManagementNav/a_LoanManagement'), 10)
WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanManagementNav/a_LoanManagement'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/h4_Loan Management'), 5)
WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/h4_Loan Information'), 5)

WebElement btnLoan = driver.findElement(By.id('btnCreationModal'))

if (btnLoan != null) {
	String disabledAttribute = btnLoan.getAttribute("disabled")
	if (disabledAttribute != null && disabledAttribute.equals("true")) {
		println("Loan is disabled.")
	}
	else{
		println("Error: Loan button should be disabled!")
		KeywordUtil.markFailed("Error: Loan button should be disabled!")
	}
}

// Specify the data file
def getcid = findTestData('DoneInitialPay')

// Get the number of rows in the data file
int rowCount = getcid.getRowNumbers()

if (rowCount >= 1) {
	String randomCID
    // Iterate through rows
    for (int i = 1; i <= rowCount; i++) {
        // Generate a random index to select a CID
        Random random = new Random()
        int randomIndex = random.nextInt(rowCount) + 1
        
        // Get the data for the CID column based on the random index
        randomCID = getcid.getValue('CID', randomIndex)
    }

	WebUI.sendKeys(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/input_NameCID_txtloanCID'), randomCID + Keys.chord(Keys.ENTER))//GlobalVariable.cid 
	
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/span_Create Loan'))
	
	// Get today's date
	LocalDate currentDate = LocalDate.now()
	
	// Define the date format
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy") // Change the format as needed
	
	// Format the current date
	String formattedDate = currentDate.format(dateFormatter)
	
	WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_Date inquiryto MIDAS_txtInquiryMIDAS'), formattedDate)
	
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_-- Select --Negative HitsPositive HitsNot Applicable'))
	WebElement selected_hits = driver.findElement(By.cssSelector("option[value='1877']"));
	selected_hits.click()
	
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_-- Select --ApprovedDisapprovedNot Applicable'))
	WebElement selected_remarks = driver.findElement(By.cssSelector("option[value='1880']"));
	selected_remarks.click()
	
	WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_-- SelectLoanType'), 'MF - SIKAP', true)
	WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_-- SelectLoanCategory'), 'MANUFACTURING', true)
	WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_LoanPurpose_cboLoanPurpose'), 'Manufacture of Food Products, Beverages, etc.', true)
	WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_BusinessType_cboBusinessType'), 'Candy Factory', true)
	WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_LoanFrequency_cboLoanFrequency'), 'Weekly', true)
	WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_Term_cboTerm'), '4', true)
	
	WebUI.sendKeys(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_Amount_txtAmount'), GlobalVariable.loanAmount + Keys.chord(Keys.ENTER))
	
	WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_Name_txtCoName2'), "TestData")
	WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_Relationship'), 'Friend', true)
	
	get_due =  WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_Due_txtDue'), 'value')
	
	// Format the current date
	String bday = currentDate.format(dateFormatter)
	
	WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_Birthday_txtCoBirthday2'), bday)
	WebUI.delay(2)
	((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -500);");
	WebUI.waitForElementClickable(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Save as draft'), 10)
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Save as draft'))
	WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanDraft/h2_Are you sure you want to save this as draft'), 10)
	WebUI.verifyTextPresent("Are you sure you want to save this as draft?", true)
	WebUI.waitForElementClickable(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Cancel'), 10)
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Cancel'))
	
	WebUI.waitForElementClickable(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Save as draft'), 10)
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Save as draft'))
	WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanDraft/h2_Are you sure you want to save this as draft'), 10)
	WebUI.verifyTextPresent("Are you sure you want to save this as draft?", true)
	WebUI.waitForElementClickable(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Save as draft'), 10)
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Yes'))
	WebUI.delay(2)
	WebUI.verifyTextPresent("The Loan has been successfully saved as a draft", true)
	WebUI.delay(1)
	
	Actions actions3 = new Actions(driver)
	// Simulate pressing the Enter key using the Actions class
	actions3.sendKeys(Keys.ENTER).perform()
	
	WebUI.delay(2)
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanDraft/strong_Loan'), 10)
	
	WebUI.sendKeys(findTestObject('Object Repository/LoanManagementModule/LoanDraft/input_Search_form-control form-control-sm'), randomCID + Keys.chord(Keys.ENTER))
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/LoanManagementModule/LoanDraft/i_zcresurrecion_ri-pencil-fill'), 10)
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDraft/i_zcresurrecion_ri-pencil-fill'))
	
	WebUI.waitForElementClickable(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Next'), 10)
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Next'))
	
	WebUI.waitForElementClickable(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Previous'), 10)
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Previous'))
	
	//WebUI.waitForElementClickable(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Cancel'), 10)
	//WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDraft/button_Cancel'))
	WebUI.delay(1)
	WebElement cancel = driver.findElement(By.id('prev'))
	cancel.click()
	WebUI.delay(1)
	WebUI.waitForElementClickable(findTestObject('Object Repository/LoanManagementModule/LoanDraft/i_zcresurrecion_ri-close-circle-fill'), 10)
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanDraft/i_zcresurrecion_ri-close-circle-fill'))
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/LoanManagementModule/LoanDraft/h2_Are you sure you want to delete this Draft'), 10)
	WebUI.delay(1)
	WebElement yes = driver.findElement(By.className('swal2-confirm'))
	yes.click()
	WebUI.delay(1)
}
else {
	println("Error: Data file contains no rows.")
	KeywordUtil.markFailed("Error: Data file contains no rows.");
}




