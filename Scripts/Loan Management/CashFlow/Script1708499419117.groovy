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

WebUI.openBrowser('')

WebUI.maximizeWindow()

ArrayList<String> bugList = new ArrayList<String>()//bug storage

WebUI.navigateToUrl('http://10.9.2.27:8880/eSystemNextGenWebApp/pages/Login')

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtUsername'), GlobalVariable.user2)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtPassword'), GlobalVariable.pass2)

WebUI.doubleClick(findTestObject('Object Repository/LoginPage/itoggletxtPassword'))

WebUI.click(findTestObject('Object Repository/LoginPage/button_Login'))

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

WebUI.sendKeys(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_Amount_txtAmount'), "5000" + Keys.chord(Keys.ENTER))

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

WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtincomeAmount1'), '10000')

income =  WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtTotalIncome'), 'value')
double new_income = 0;

if(income == "10,000.00")
{
	println("Correct Amount")
	new_income = 10000;
}
else
{
	println(income + " is not equal to 10,000.00")
	bugList.add("Incorrect Income amount display(first input)")
}

WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/button_AddAnother Income'))

WebElement select_income2 = driver.findElement(By.id('cboincome2'))
Select dropdown = new Select(select_income2);
dropdown.selectByVisibleText("Income from business");

WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtincomeAmount2'), '12,550.50')

income =  WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtTotalIncome'), 'value')
double new_expense = 0;

if(income == "22,550.50")
{
	println("Correct Amount")
	new_income = 22550.50;
}
else
{
	println(income + " is not equal to 22,550.50")
	bugList.add("Incorrect Income amount display(second input)")
}

WebUI.selectOptionByValue(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/select_-- Select --FoodWaterElectricityEduc_adf9e8'),'1852', true)
WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtrowExpenseAmount1'), '1000')

expense = WebUI.getAttribute(findTestObject('Object Repository/Page_Core/inputtxtTotalExpense'), 'value')

if(expense == "1,000.00")
{
	println("Correct Amount")
	new_expense = 1000.00;
}
else
{
	println(expense + " is not equal to 1,000.00")
	bugList.add("Incorrect expense amount display(first input)")
}

WebElement add_expense = driver.findElement(By.id('add-Expense-button'))
add_expense.click()

WebElement select_expense2 = driver.findElement(By.id('cborowExpense2'))
Select dropdown2 = new Select(select_expense2);
dropdown2.selectByVisibleText("Food");

WebElement add_expense2 = driver.findElement(By.id('txtrowExpenseAmount2'))
add_expense2.sendKeys("5010.90");

expense = WebUI.getAttribute(findTestObject('Object Repository/Page_Core/inputtxtTotalExpense'), 'value')

if(expense == "6,010.90")
{
	println("Correct Amount")
	new_expense = 6010.90;
}
else
{
	println(expense + " is not equal to 6,010.90")
	bugList.add("Incorrect expense amount display(second input)")
}


WebElement amor = driver.findElement(By.id('txtAppliedLoanAmortization'))
ttl_amortization = amor.getAttribute("value");

if(ttl_amortization == get_due) 
{
	println("Correct Amr")
}
else
{
	println("Incorrect Amortization: "+ ttl_amortization + "is not equal to expected: "+ get_due)
	bugList.add("Incorrect amortization display")
}











//
//WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtincomeAmount1'), '1,2000')
//
//WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/button_AddAnother Income'))
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Core/select_-- Select --EmploymentSalariesIncome_ce4e89_1'), 
//    '1760', true)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Core/select_-- Select --EmploymentSalariesIncome_ce4e89_1'), 
//    '1760', true)
//
//WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtincomeAmount2'), '1,0000')
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/i_mdi mdi-trash-can-outline font-23'), 0)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/select_-- Select --FoodWaterElectricityEduc_adf9e8'), 
//    '1852', true)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/select_-- Select --FoodWaterElectricityEduc_adf9e8'), 
//    '1852', true)
//
//WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtrowExpenseAmount1'), '1000')
//
//WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/button_AddAnother Expense'))
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Core/select_-- Select --FoodWaterElectricityEduc_adf9e8_1'), 
//    '1850', true)
//
//WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Core/select_-- Select --FoodWaterElectricityEduc_adf9e8_1'), 
//    '1850', true)
//
//WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtrowExpenseAmount2'), '5,000')
//
//WebUI.sendKeys(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtrowExpenseAmount2'), Keys.chord(Keys.ENTER))
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/i_mdi mdi-trash-can-outline font-23_1'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtTotalIncome'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/inputtxtTotalExpense'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/inputtxtActiveLoanTotal'), 0)
//
//WebUI.setText(findTestObject('Object Repository/Page_Core/inputtxtTotalBrorrowAmount'), '1,000')
//
//WebUI.sendKeys(findTestObject('Object Repository/Page_Core/inputtxtTotalBrorrowAmount'), Keys.chord(Keys.ENTER))
//
//WebUI.setText(findTestObject('Object Repository/Page_Core/inputtxtBrorrowAmortization'), '50')
//
//WebUI.sendKeys(findTestObject('Object Repository/Page_Core/inputtxtBrorrowAmortization'), Keys.chord(Keys.ENTER))
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/inputtxtTotalLoanAmount'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/inputtxtTotalAmortization'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/inputtxtNetIncome'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/inputtxtDSC'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/inputtxtCapacityToPay'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/inputtxtLoanableAmount'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/inputtxtCurrentLoan'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/inputtxtRemainingLoanable'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/inputtxtAmountApplied'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/inputtxtAmountApproved'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/button_Previous'), 0)
//
//WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Core/button_Next'), 0)

