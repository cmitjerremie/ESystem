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

def fullname, bday, num, area, unit, center;

fullname = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/input_FullName_txtfullname'), 'value')
bday = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/input_Birthday_txtbirthday'), 'value')
num = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/input_Contact Number_txtcontactNo'), 'value')
area = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/input_AreaName_txtareaName'), 'value')
unit = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/input_UnitName_txtunitName'), 'value')
center = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/input_Center Name_txtcenterName'), 'value')

println("Fullname: " + fullname + "\nBday: " + bday + "\nContact num: " + num + "\nArea: " + area + "\nUnit: " + unit + "\nCenter: " + center)

WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/span_Create Loan'), 0)
WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/span_Create Loan'))

//Loan Creation

WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanCreation/strong_Loan Creation'), 0)
WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/LoanCreation/span_Save as draft'), 0)

//check the customer info

def cid, dis_fullname, dis_bday, dis_area, dis_unit, dis_center, dis_contact;

cid = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/input_CID_mdtxtloanCID'), 'value') 
dis_fullname = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/input_Name_mdtxtfullname'), 'value')
dis_bday = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/input_Birthday_mdtxtbirthday'), 'value')
dis_area = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/input_Area Name_mdtxtareaName'), 'value')
dis_unit = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/input_Unit Name_mdtxtunitName'), 'value')
dis_center = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/input_Center Name_mdtxtcenterName'), 'value')
dis_contact = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/input_Contact Number_mdtxtcontactNo'), 'value')

if(cid == GlobalVariable.cid && dis_fullname == fullname && dis_bday == bday && dis_area == area && dis_unit == unit && dis_center == center && dis_contact == num)
{
	println("All Details are correct")
}
else
{
	bugList.add("Customer details mismatched in Loan Creation!")
	println('Customer Displayed Details Mismatched!')
}

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

def get_due, get_interest, get_outs, get_lrf, get_net;
//def options = [301, 302, 311, 316, 318, 321, 323, 332, 344, 418, 419, 420, 449, 451, 461, 462, 463, 464, 465, 475];
def options = [301]; //316 if want to test monthly/semi/lumpsum note:uncomment the monthly inside the if-else condition
int[] term = [];
double[] rate = [];

//Loop all Loan type Option //Select all
for(int j=0; j<options.size(); j++)
{
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_-- SelectLoanType'))
	WebElement selected = driver.findElement(By.cssSelector("option[value='"+ options[j] +"']"));
	String selected_loan_type = selected.getAttribute("value");
	selected.click()
	
	def principal = 2001;
	
	//IPL-CAMIA (Bundled), IPL RIMANSI (DAKILA), PHILHEALTH, SSS //Missing IPL-MBA
	if(selected_loan_type == '420' || selected_loan_type == '475' || selected_loan_type == '321' || selected_loan_type == '323')
	{
		println('IPL-CAMIA (Bundled), IPL RIMANSI (DAKILA), PHILHEALTH, SSS //Missing IPL-MBA')
		term = [4,8,12,16,20,23,28,32,36,40,44,49,50];
		rate = [1.92, 3.84, 5.76, 7.68, 9.60, 11.04, 13.44, 15.36, 17.28, 19.20, 21.12, 23.52, 24.00];
	}
	//Educational Loan ( Highschool , Senior High / College)
	else if(selected_loan_type == '344' || selected_loan_type == '418' || selected_loan_type == '419')
	{
		println('//Educational Loan ( Highschool , Senior High / College)')
		term = [4,8,12,16,20,23,28,32,36,40,44,50];
		rate = [1.44, 2.88, 4.32, 5.76, 7.20, 8.28, 10.08, 11.52, 12.96, 14.40, 15.85, 18.00];
	}
	//SIKAP 1, SIKAP GLIP, AGRI, SIKAP 2, SOLAR, GADGET, HOME IMPROVEMENT // Missing SLF, SBL, Solar,
	else if(selected_loan_type == '301' || selected_loan_type == '311' || selected_loan_type == '316' || selected_loan_type == '302' || selected_loan_type == '462' || selected_loan_type == '318')
	{
		println('//SIKAP 1, SIKAP GLIP, AGRI, SIKAP 2, SOLAR, GADGET, HOME IMPROVEMENT // Missing SLF, SBL, Solar,')
		term = [4,8,12,16,20,23,28,32,36,40,44,50];
		rate = [2.56, 5.12, 7.68, 10.24, 12.80, 14.72, 17.44, 19.36, 21.28, 23.20, 25.12, 28.00];
	}
	
	
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_-- SelectLoanCategory'))
	WebElement selected_loan_cat = driver.findElement(By.cssSelector("option[value='1926']"));
	selected_loan_cat.click()
	
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_LoanPurpose_cboLoanPurpose'))
	WebElement selected_loan_pur = driver.findElement(By.cssSelector("option[value='1953']"));
	selected_loan_pur.click()
	
	WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_BusinessType_cboBusinessType'))
	WebElement selected_bns_type = driver.findElement(By.cssSelector("option[value='2299']"));
	selected_bns_type.click()
	
	//	50 - weekly
	//	12 - Monthly
	//	24 - Semi-monthly
	//	1 - Lumpsump
	double freq = 0.3 //this is for weekly
	
	if(selected_loan_type == '316')//only used for checking monthly/semi/lump
	{
		freq = 1.25;
		
		//Change this manually according to the target test script
		//Semi-monthly
		WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_LoanFrequency_cboLoanFrequency'), 'Monthly', true)	
		term = [1,2,3,4,5,6,7,8,9,10,11,12];
		rate = [2.67, 5.33, 8.00, 10.67, 13.33, 16.00, 18.00, 20.00, 22.00, 24.00, 26.00, 28.00];
		
		//Uncomment this if you want to test Semi-monthly
		WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_LoanFrequency_cboLoanFrequency'), 'Semi-monthly', true)
		term = [2,4,6,8,10,12,14,16,18,20,22,24];
		rate = [2.67, 5.33, 8.00, 10.67, 13.33, 16.00, 18.00, 20.00, 22.00, 24.00, 26.00, 28.00];
	}
	else
	{
		WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_LoanFrequency_cboLoanFrequency'), 'Weekly', true)
	}
	
	for(int x=0; x<rate.size(); x++) //increase loan amount
	{	
		principal = principal + 1500;
		double int_rate = rate[x]/100;
		int selected_term = term[x]
		
		for(int i=0; i<1; i++)
		{
			int inter = (int) Math.ceil(principal*int_rate);
			double due = (principal + inter) / selected_term;
			int fnl_due = (int) Math.ceil(due / 5) * 5;
			int loan_outs = principal + inter;
			double lrf = (freq * principal * selected_term)/1000;
			int fnl_lrf = (int) Math.ceil(lrf);
			int net = principal - fnl_lrf;
			
			DecimalFormat pesoFormat = new DecimalFormat("#,##0.00");
			String formatted_inter = pesoFormat.format(inter);
			String formatted_due = pesoFormat.format(fnl_due);
			String formatted_outs = pesoFormat.format(loan_outs);
			String formatted_lrf = pesoFormat.format(fnl_lrf);
			String formatted_net = pesoFormat.format(net);
				
			WebUI.click(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_Term_cboTerm'))
			WebElement selected_loan_term = driver.findElement(By.id(String.valueOf(term[x])));
			WebUI.delay(1)
			selected_loan_term.click()
			
			WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_Amount_txtAmount'), "")
			WebUI.sendKeys(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_Amount_txtAmount'), principal + Keys.chord(Keys.ENTER))
			WebUI.delay(1)
			
			//verify if the computations are correct	
			get_due =  WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_Due_txtDue'), 'value')
			get_interest = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_Interest_txtInterest'), 'value')
			get_outs = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_LoanOutstanding_txtLoanOutstanding'), 'value')
			get_lrf = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_LRF_txtLRF'), 'value')
			get_net = WebUI.getAttribute(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/input_Net Proceed_txtNetProcess'), 'value')
			
			if(get_due.equals(formatted_due) && get_interest.equals(formatted_inter) && get_outs.equals(formatted_outs) && get_lrf.equals(formatted_lrf) && get_net.equals(formatted_net))
			{
				println("works!!!!!!!!!!!!!!!!!!!!!!!")
			}
			else
			{
				println(get_due + "!=" +  formatted_due)
				println(get_interest + "!=" +  formatted_inter)
				println(get_outs + "!=" +  formatted_outs)
				println(get_lrf + "!=" +  formatted_lrf)
				println(get_net + "!=" +  formatted_net)
				WebUI.delay(500)
				
				bugList.add("Computation Error in " + "Loan Type id: " + selected_loan_type + "Term " + term[x] + "Inputed principal: " + principal)
				println("error!!!!!!!!!!!!!!!!!!!!!!!")
			}
		}
	}
}

if (!bugList.isEmpty()) {
	// Mark the test case as failed
	for (String error : bugList) {
		KeywordUtil.markFailed(error);
	}
}

WebUI.closeBrowser()











