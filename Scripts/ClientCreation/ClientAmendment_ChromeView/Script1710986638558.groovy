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

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import internal.GlobalVariable as GlobalVariable

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*

WebUI.openBrowser('')

WebUI.maximizeWindow();

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtUsername'), GlobalVariable.user2)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtPassword'), GlobalVariable.pass2)

WebUI.doubleClick(findTestObject('Object Repository/LoginPage/itoggletxtPassword'))

WebUI.click(findTestObject('Object Repository/LoginPage/button_Login'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Dashboard/div_DashboardWelcome to your dashboard Were glad to have you here'), 50)

println('Dashboard Successfully Displayed')

WebUI.waitForElementClickable(findTestObject('Object Repository/ClientAmendment/a_Client'), 30)
WebUI.click(findTestObject('Object Repository/ClientAmendment/a_Client'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/ClientAmendment/a_Inquiryand Amendment'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

// Specify the data file
def getCID = findTestData('DoneInitialPay')

// Get the number of rows in the data file
int rowCount = getCID.getRowNumbers()

if (rowCount >= 1) {
// Iterate through rows
	for (int i = rowCount; i >= rowCount; i--) {
		// Get data for each column
		String cid = getCID.getValue('CID', i)
		String firstName = getCID.getValue('FIRSTNAME', i)
		String lastName = getCID.getValue('LASTNAME', i)
		
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_CID_txtCID'), cid) //GlobalVariable.cid_ammend
	
	WebUI.sendKeys(findTestObject('Object Repository/ClientAmendment/input_CID_txtCID'), Keys.chord(Keys.ENTER))
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/ClientAmendment/h4_Customers'), 10)
	WebUI.verifyElementText(findTestObject('Object Repository/ClientAmendment/h4_Customers'), 'Customers')
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/ClientAmendment/div_Show 102550100 entriesCIDFullNameBirthd_897106_1'),
		10)
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/i_Active_mdi mdi-magnify'))
	
	WebUI.waitForElementClickable(findTestObject('Object Repository/ClientAmendment/span_Amend'), 20)
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/span_Amend'))
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--AnnulledLive-inMarriedSepa_54a068'),
		'162', true)
	
	// Function to generate a random middle name with exactly 3 letters
	String vowels = "aeiou";
	String consonants = "bcdfghjklmnpqrstvwxyz";
	Random rand = new Random();
	
	StringBuilder middleNameBuilder = new StringBuilder();
	for (int j = 0; j < 3; j++) {
	    if (j % 2 == 0) {
	        middleNameBuilder.append(consonants.charAt(rand.nextInt(consonants.length())));
	    } else {
	        middleNameBuilder.append(vowels.charAt(rand.nextInt(vowels.length())));
	    }
	}
	String amdName = middleNameBuilder.toString();
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_FirstName_txtfname'), firstName)
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_LastName_txtlname'), lastName + amdName)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--Accommodation and Food Ser_e45bf9'),
		'694', true)
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_TIN_txtTinID'), '123-456-789-00000')
	
	//WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--      Select      --villagevillage_79bc42'),
	//    '2713', true)
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Save'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/a_Address'))
	
	WebUI.delay(1)
	
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);")
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/span_Amend'))
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--      Select      --Abra - CAR (Co_3be376'),
		'10489', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--      Select      --AgdanganAlabat_8d941c'),
		'10586', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_Antonino (Ayusan)Bagong Anyo (Pob.)B_a9711d'),
		'13501', true)
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Save'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/a_ID Presented'))
	
	WebUI.delay(1)
	
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -500);")
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/span_Amend'))
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--Barangay CertificateBIR TI_0033e0'),
		'727', true)
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_Placeof Issue_txtPlaceOfIssue'), 'test')
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Save'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/a_Other Information'))
	
	WebUI.delay(1)
	
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -500);")
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/ClientAmendment/a_DOSRIRPT'), 10)
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Amend'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/input_YES_drone3'))
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_FirstName_txtSpousefname'), 'testdata')
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/input_FirstName_txtSpousefname'))
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_MiddleName_txtSpousemname'), 'Testdata')
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_LastName_txtSpouselname'), 'Testdata')
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--FemaleMale'), '74', true)
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_Dateof Marriage_txtSpouseDateOfMarraige'), '01012024')
	WebUI.sendKeys(findTestObject('Object Repository/ClientAmendment/input_Dateof Marriage_txtSpouseDateOfMarraige'), Keys.ENTER.toString())
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_Birthday_txtSpousebday'), '01011999')
	WebUI.sendKeys(findTestObject('Object Repository/ClientAmendment/input_Birthday_txtSpousebday'), Keys.ENTER.toString())
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--Aborlan - PalawanAbra De I_52077c'),
		'10491', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--BusinessGovernment Employe_69ab64'),
		'441', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--EmploymentSalariesIncome f_b99596'),
		'1759', true)
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_SourceOf Income_txtCompanyname'), 'testComp')
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--No Formal EducationElement_aa261c'),
		'173', true)
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_CellphoneNumber_txtSpouseCpNmber'), '09123456789')
	
	//WebUI.click(findTestObject('Object Repository/ClientAmendment/i_Remarks_beneRow1'))
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 1000);")
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_Remarks_txtBeneficiaryName2'), 'testdata')
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_Remarks_txtBeneficiaryBday2'), '01012007')
	WebUI.sendKeys(findTestObject('Object Repository/ClientAmendment/input_Remarks_txtBeneficiaryBday2'), Keys.ENTER.toString())
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--BrotherBrother-in-LawCousi_e1fca2'),
		'759', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--FemaleMale_1'), '74', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--No Formal EducationElement_aa261c_1'),
		'197', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--NAPrimarySecondary'), '1800', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--YESNO'), '0', true)
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--YESNO_1'), '0', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--BeneficiaryLegal Dependent_a71cea'),
		'1802', true)
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Save'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/a_CRA'))
	
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -500);")
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Amend'))
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_Select      51015'), '2', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_Select      51020'), '8', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_Select      51015_1'), '17', true)
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Save'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/a_KYC'))
	
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -500);")
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/span_Amend'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/label_Numberof Household Member'))
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_Numberof Household Member_txtHouseholdMember'), '6')
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--EmploymentSalariesIncome f_b99596_1'),
		'1760', true)
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_HouseholdMonthly Expense_txtMonthlyExpense'), '7,000.00')
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--Below 1,000 pesos1001 - 3,_8e7891'),
		'1777', true)
	
	WebUI.setText(findTestObject('Object Repository/ClientAmendment/input_College_txtElementary'), '2')
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Save'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/a_DSPPI'))
	
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -500);")
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/span_Amend'))
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--      Select      --0 - 7 o higit _f836c6'),
		'0', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--      Select      --0 - Magaang ma_840447'),
		'7', true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--      Select      --0 - Hindi13 - Oo'), '13',
		true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--      Select      --0 - Hindi8 - Oo'), '8',
		true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--GLIP 1GLIP 2IPPaglambo'), '674',
		true)
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmendment/select_--Select--Adasen InlaudAetaAetaAlang_82eebf'),
		'4481', true)
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Save'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/a_ConsentWaiver'))
	
	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -500);")
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Amend'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/input_YES_healthDeclaration'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/input_Health Declaration_healthDeclaration'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Save'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Submit'))
	
	WebUI.verifyElementPresent(findTestObject('Object Repository/ClientAmendment/h2_Are you sure you want to proceed'), 10)
	
	WebUI.verifyTextPresent('Are you sure you want to proceed?', true)
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Cancel'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Submit'))
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Yes'))
	
	WebUI.delay(1)
	WebUI.waitForElementVisible(findTestObject('Object Repository/ClientAmendment/div_i_swal2-success-ring (1)'), 10)
	WebUI.waitForElementVisible(findTestObject('Object Repository/ClientAmendment/div_The client amendment has been successfu_5db511'), 10)
	String msg_amd = WebUI.getText(findTestObject('Object Repository/ClientAmendment/div_The client amendment has been successfu_5db511'))
	
	if (msg_amd == "The client amendment has been successfully processed. Please inform your approver to save the changes.") {
	    // Code to execute if the text is present
		println(msg_amd)
		// Specify the data file
		def pendingAmmend = findTestData('ForAmendment')
		
		// Get the path to the Excel file
		String excelFilePath = pendingAmmend.getSourceUrl()
		
		// Open the Excel workbook
		FileInputStream fis = new FileInputStream(excelFilePath)
		XSSFWorkbook workbook = new XSSFWorkbook(fis)
		
		Sheet sheet = workbook.getSheetAt(3)
		
		// Find the last row index (add 1 to get the next available row)
		int lastRowIndex = sheet.getLastRowNum() + 1
		
		// Create a new row
		Row newRow = sheet.createRow(lastRowIndex)
		
		// Add data to the new row
		newRow.createCell(0).setCellValue(cid)
		
		// Save the changes
		FileOutputStream fos = new FileOutputStream(excelFilePath)
		workbook.write(fos)
		
		// Close the FileInputStream and workbook
		fis.close()
		fos.close()
		workbook.close()
		
	    println("Ammendment saved successfully")
	} else {
	    // Code to execute if the text is not present
	    println("Ammendment Error msg: " + msg_amd)
		KeywordUtil.markFailed("Ammendment Error msg: " + msg_amd)
	}
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/button_Yes'))
	
	WebUI.delay(2)
	Actions actions3 = new Actions(driver)
	// Simulate pressing the Enter key using the Actions class
	actions3.sendKeys(Keys.ENTER).perform()
	
	WebUI.click(findTestObject('Object Repository/ClientAmendment/span_Close'))
	
	WebUI.delay(1)
	
	}
}
else {
	println("Error: Data file contains no rows. - Please add and approve customer first!")
	KeywordUtil.markFailed("Error: Data file contains no rows. - Please add and approve customer first!");
}

WebUI.verifyElementPresent(findTestObject('Object Repository/Logout/span_(Open)_account-user-avatar'), 30)
WebUI.click(findTestObject('Object Repository/Logout/span_(Open)_account-user-avatar'))

WebUI.click(findTestObject('Object Repository/Logout/i_ChangePassword_mdi mdi-logout me-1'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Logout/img'), 10)

println("Logout Successfull")

WebUI.closeBrowser()

