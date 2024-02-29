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

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtUsername'), GlobalVariable.user2)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtPassword'), GlobalVariable.pass2)

WebUI.doubleClick(findTestObject('Object Repository/LoginPage/itoggletxtPassword'))

WebUI.click(findTestObject('Object Repository/LoginPage/button_Login'))

//WebUI.waitForElementVisible(findTestObject('Object Repository/LoginPage/h2_Login Successful'), 0, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Object Repository/LoginPage/Home/h4_Dashboard'), 0)

println('Dashboard Successfully Displayed')

WebUI.delay(1)

ArrayList<String> bugList = new ArrayList<String>()//bug storage

WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanManagementNav/a_Loan'))

WebUI.delay(1)

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
		bugList.add('Loan Button is not Disabled!')
	}
}

WebUI.sendKeys(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/input_NameCID_txtloanCID'), GlobalVariable.cid + Keys.chord(Keys.ENTER))

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

WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_-- SelectLoanType'), 'SIKAP 1', true)
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

WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/button_Next'))

WebElement capital = driver.findElement(By.id('txtWorkingCap'))
capital.sendKeys("10000");

WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/select_-- Select --EmploymentSalariesIncome_ce4e89'), 'Employment/Salaries', true)

WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtincomeAmount1'), '20000')

WebUI.selectOptionByValue(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/select_-- Select --FoodWaterElectricityEduc_adf9e8'),'1852', true)
WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtrowExpenseAmount1'), '1000')

WebElement ttl_borrowed = driver.findElement(By.id('txtTotalBrorrowAmount'))
ttl_borrowed.sendKeys('10000')
WebElement amr_ipt = driver.findElement(By.id('txtBrorrowAmortization'))
amr_ipt.sendKeys('833.33')
amr_ipt.sendKeys(Keys.ENTER);


WebElement next = driver.findElement(By.id('next'))
next.click()

WebElement rehiyon = driver.findElement(By.id('cboAnswerList'))
Select select1 = new Select(rehiyon)
select1.selectByValue('9')

WebElement myembro = driver.findElement(By.id('cbosecondQAnswer'))
Select select2 = new Select(myembro)
select2.selectByValue('18')

WebElement baytang = driver.findElement(By.id('cbothirdQAnswer'))
Select select3 = new Select(baytang)
select3.selectByValue('7')

WebElement materyal = driver.findElement(By.id('cbofourthQAnswer'))
Select select4 = new Select(materyal)
select4.selectByValue('5')

WebElement materyal2 = driver.findElement(By.id('cbofifthQAnswer'))
Select select5 = new Select(materyal2)
select5.selectByValue('7')

WebElement kuryente = driver.findElement(By.id('cbosixthQAnswer'))
Select select6 = new Select(kuryente)
select6.selectByValue('7')

WebElement tubig = driver.findElement(By.id('cboseventhQAnswer'))
Select select7 = new Select(tubig)
select7.selectByValue('5')

WebElement ref = driver.findElement(By.id('cboeighthQAnswer'))
Select select8 = new Select(ref)
select8.selectByValue('0')

WebElement tv = driver.findElement(By.id('cboninthQAnswer'))
Select select9 = new Select(tv)
select9.selectByValue('11')

WebElement washing = driver.findElement(By.id('cbotenthQAnswer'))
Select select10 = new Select(washing)
select10.selectByValue('0')

WebUI.delay(1)

WebElement next2 = driver.findElement(By.id('next'))
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("arguments[0].scrollIntoView(false);", next2);
next2.click();

WebElement kyc = driver.findElement(By.id('txtRiskClassList'))
kyc.click()

WebElement q1 = driver.findElement(By.id('cboRiskAnswerList'))
Select ra1 = new Select(q1)
ra1.selectByVisibleText("5");

WebElement q2 = driver.findElement(By.id('cbosecondRiskAnswer'))
Select ra2 = new Select(q2)
ra2.selectByVisibleText("5");

WebElement q3 = driver.findElement(By.id('cbothirdRiskAnswer'))
Select ra3 = new Select(q3)
ra3.selectByVisibleText("5");

WebElement q4 = driver.findElement(By.id('cbofourthRiskAnswer'))
Select ra4 = new Select(q4)
ra4.selectByVisibleText("0");

WebElement q5 = driver.findElement(By.id('cbofifthRiskAnswer'))
Select ra5 = new Select(q5)
ra5.selectByVisibleText("0");

WebElement q6 = driver.findElement(By.id('cbosixthRiskAnswer'))
Select ra6 = new Select(q6)
ra6.selectByVisibleText("10");

WebElement q7 = driver.findElement(By.id('cboseventhRiskAnswer'))
Select ra7 = new Select(q7)
ra7.selectByVisibleText("15");

WebElement save = driver.findElement(By.id('btnsaveCRA'))
save.click()

WebElement next3 = driver.findElement(By.id('next'))
next3.click()

WebElement approve = driver.findElement(By.id('1919'))
approve.click()

WebElement next5 = driver.findElement(By.id('next'))
next5.click()

//WebElement proceed = driver.findElement(By.id('btnProceedLoan'))
//proceed.click()














