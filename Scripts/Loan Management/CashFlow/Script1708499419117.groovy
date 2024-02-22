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


WebElement amor = driver.findElement(By.id('txtAppliedLoanAmortization'))//Amortization display
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

WebElement ttl_borrowed = driver.findElement(By.id('txtTotalBrorrowAmount'))
ttl_borrowed.sendKeys('10000')
WebElement amr_ipt = driver.findElement(By.id('txtBrorrowAmortization'))
amr_ipt.sendKeys('833.33')
amr_ipt.sendKeys(Keys.ENTER);

WebElement applied_amount = driver.findElement(By.id('txtAppliedLoan'))
WebElement amr_dis = driver.findElement(By.id('txtAppliedLoanAmortization'))

double applied_amount_double = Double.parseDouble(applied_amount.getAttribute('value').replace(",", ""));
double amr_dis_double = Double.parseDouble(amr_dis.getAttribute('value').replace(",", ""));

WebElement ttl1_fld = driver.findElement(By.id('txtTotalLoanAmount'))
WebElement ttl2_fld = driver.findElement(By.id('txtTotalAmortization'))

double ttl1 = 10000 + applied_amount_double;
double ttl2 = 833.33 + amr_dis_double;

DecimalFormat pesoFormat = new DecimalFormat("#,##0.00");
String formatted_ttl1 = pesoFormat.format(ttl1);
String formatted_ttl2 = pesoFormat.format(ttl2);//total Amortization

if(formatted_ttl1.equals(ttl1_fld.getAttribute('value')) && formatted_ttl2.equals(ttl2_fld.getAttribute('value')))
{
	println("Correct Total Amount")	
}
else
{
	println("Incorrect Total Amount")
	bugList.add("Incorrect Total Amount")
}

WebElement net_income = driver.findElement(By.id('txtNetIncome'))
WebElement dsc = driver.findElement(By.id('txtDSC'))
WebElement capacity = driver.findElement(By.id('txtCapacityToPay'))
WebElement loanable = driver.findElement(By.id('txtLoanableAmount'))
WebElement current = driver.findElement(By.id('txtCurrentLoan'))
WebElement remaining = driver.findElement(By.id('txtRemainingLoanable'))
WebElement amt_applied = driver.findElement(By.id('txtAmountApplied'))
WebElement amt_approved = driver.findElement(By.id('txtAmountApproved'))

get_net = net_income.getAttribute('value')
get_dsc = dsc.getAttribute('value')
get_capacity = capacity.getAttribute('value')
get_loanable = loanable.getAttribute('value')
get_current = current.getAttribute('value').replace(",", "")
get_remaining = remaining.getAttribute('value')
get_amt_applied = amt_applied.getAttribute('value')
get_amt_approved = amt_approved.getAttribute('value')

double dis_net = new_income - new_expense;
double dis_dsc = dis_net/ttl2;
double dis_cap = dis_net*0.50;
double dis_loanable = dis_cap*1; //the selected term is 4 weeks -> months = 1
double dis_current = Double.parseDouble(get_current);
double dis_remaining = dis_loanable - dis_current;
double dis_amt_applied = GlobalVariable.loanAmount;
double dis_amt_approved = dis_amt_applied;

DecimalFormat pesoFormat2 = new DecimalFormat("#,##0.00");
String formatted_net = pesoFormat2.format(dis_net);
String formatted_dsc = pesoFormat2.format(dis_dsc);
String formatted_cap = pesoFormat2.format(dis_cap);
String formatted_loanable = pesoFormat2.format(dis_loanable);
String formatted_current = pesoFormat2.format(dis_current);
String formatted_remaining = pesoFormat2.format(dis_remaining);
String formatted_amt_applied = pesoFormat2.format(dis_amt_applied);
String formatted_amt_approved = pesoFormat2.format(dis_amt_approved);

if(get_net.equals(formatted_net) && get_dsc.equals(formatted_dsc) && get_capacity.equals(formatted_cap) && get_loanable.equals(formatted_loanable)
	&& get_current.equals(formatted_current) && get_remaining.equals(formatted_remaining) && get_amt_applied(formatted_amt_applied) && get_amt_approved.equals(formatted_amt_approved))
{
	println("CashFlow computations are correct!!!")	
}
else
{
	bugList.add("Final Computations Error!!!!!!")
}

WebUI.closeBrowser()








