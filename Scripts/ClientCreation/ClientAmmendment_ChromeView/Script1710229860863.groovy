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

WebUI.openBrowser('')

WebUI.maximizeWindow();

WebUI.navigateToUrl(GlobalVariable.url)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtUsername'), GlobalVariable.user2)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtPassword'), GlobalVariable.pass2)

WebUI.doubleClick(findTestObject('Object Repository/LoginPage/itoggletxtPassword'))

WebUI.click(findTestObject('Object Repository/LoginPage/button_Login'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Dashboard/div_DashboardWelcome to your dashboard Were glad to have you here'), 50)

println('Dashboard Successfully Displayed')

WebUI.waitForElementClickable(findTestObject('Object Repository/ClientAmmendment/a_Client'), 30)
WebUI.click(findTestObject('Object Repository/ClientAmmendment/a_Client'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/ClientAmmendment/a_Inquiryand Amendment'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_CID_txtCID'), GlobalVariable.cid_ammend)

WebUI.sendKeys(findTestObject('Object Repository/ClientAmmendment/input_CID_txtCID'), Keys.chord(Keys.ENTER))

WebUI.verifyElementText(findTestObject('Object Repository/ClientAmmendment/h4_Customers'), 'Customers')

WebUI.verifyElementPresent(findTestObject('Object Repository/ClientAmmendment/div_Show 102550100 entriesCIDFullNameBirthd_897106_1'), 
    0)

WebUI.click(findTestObject('Object Repository/ClientAmmendment/i_Active_mdi mdi-magnify'))

WebUI.waitForElementClickable(findTestObject('Object Repository/ClientAmmendment/span_Amend'), 20)

WebUI.click(findTestObject('Object Repository/ClientAmmendment/span_Amend'))

String fname = driver.findElement(By.id('txtfname')).getAttribute('value')
String lname = driver.findElement(By.id('txtlname')).getAttribute('value')

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--AnnulledLive-inMarriedSepa_54a068'), 
    '162', true)

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_FirstName_txtfname'), fname + " Ammend")

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_LastName_txtlname'), lname + " Ammend")

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--Accommodation and Food Ser_e45bf9'), 
    '694', true)

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_TIN_txtTinID'), '123-456-789-00000')

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--      Select      --villagevillage_79bc42'), 
    '2713', true)

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Save'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/a_Address'))

WebUI.delay(1)

((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);")

WebUI.click(findTestObject('Object Repository/ClientAmmendment/span_Amend'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--      Select      --Abra - CAR (Co_3be376'), 
    '10489', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--      Select      --AgdanganAlabat_8d941c'), 
    '10586', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_Antonino (Ayusan)Bagong Anyo (Pob.)B_a9711d'), 
    '13501', true)

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Save'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/a_ID Presented'))

WebUI.delay(1)

((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -500);")

WebUI.click(findTestObject('Object Repository/ClientAmmendment/span_Amend'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--Barangay CertificateBIR TI_0033e0'), 
    '727', true)

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_Placeof Issue_txtPlaceOfIssue'), 'test')

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Save'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/a_Other Information'))

WebUI.delay(1)

((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -500);")

WebUI.verifyElementPresent(findTestObject('Object Repository/ClientAmmendment/a_DOSRIRPT'), 0)

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Amend'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/input_YES_drone3'))

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_FirstName_txtSpousefname'), 'testdata')

WebUI.click(findTestObject('Object Repository/ClientAmmendment/input_FirstName_txtSpousefname'))

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_MiddleName_txtSpousemname'), 'Testdata')

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_LastName_txtSpouselname'), 'Testdata')

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--FemaleMale'), '74', true)

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_Dateof Marriage_txtSpouseDateOfMarraige'), '01012024')
WebUI.sendKeys(findTestObject('Object Repository/ClientAmmendment/input_Dateof Marriage_txtSpouseDateOfMarraige'), Keys.ENTER.toString())

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_Birthday_txtSpousebday'), '01011999')
WebUI.sendKeys(findTestObject('Object Repository/ClientAmmendment/input_Birthday_txtSpousebday'), Keys.ENTER.toString())

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--Aborlan - PalawanAbra De I_52077c'), 
    '10491', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--BusinessGovernment Employe_69ab64'), 
    '441', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--EmploymentSalariesIncome f_b99596'), 
    '1759', true)

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_SourceOf Income_txtCompanyname'), 'testComp')

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--No Formal EducationElement_aa261c'), 
    '173', true)

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_CellphoneNumber_txtSpouseCpNmber'), '09123456789')

WebUI.click(findTestObject('Object Repository/ClientAmmendment/i_Remarks_beneRow1'))

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_Remarks_txtBeneficiaryName2'), 'testdata')

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_Remarks_txtBeneficiaryBday2'), '01012007')
WebUI.sendKeys(findTestObject('Object Repository/ClientAmmendment/input_Remarks_txtBeneficiaryBday2'), Keys.ENTER.toString())

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--BrotherBrother-in-LawCousi_e1fca2'), 
    '759', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--FemaleMale_1'), '74', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--No Formal EducationElement_aa261c_1'), 
    '197', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--NAPrimarySecondary'), '1800', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--YESNO'), '1', true)

WebUI.click(findTestObject('Object Repository/ClientAmmendment/div_Additional Beneficiary If any'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--YESNO'), '0', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--YESNO_1'), '0', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--BeneficiaryLegal Dependent_a71cea'), 
    '1802', true)

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Save'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/a_CRA'))

((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -500);")

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Amend'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_Select      51015'), '2', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_Select      51020'), '8', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_Select      51015_1'), '17', true)

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Save'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/a_KYC'))

((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -500);")

WebUI.click(findTestObject('Object Repository/ClientAmmendment/span_Amend'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/label_Numberof Household Member'))

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_Numberof Household Member_txtHouseholdMember'), '6')

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--EmploymentSalariesIncome f_b99596_1'), 
    '1760', true)

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_HouseholdMonthly Expense_txtMonthlyExpense'), '7,000.00')

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--Below 1,000 pesos1001 - 3,_8e7891'), 
    '1777', true)

WebUI.setText(findTestObject('Object Repository/ClientAmmendment/input_College_txtElementary'), '2')

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Save'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/a_DSPPI'))

((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -500);")

WebUI.click(findTestObject('Object Repository/ClientAmmendment/span_Amend'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--      Select      --0 - 7 o higit _f836c6'), 
    '0', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--      Select      --0 - Magaang ma_840447'), 
    '7', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--      Select      --0 - Hindi13 - Oo'), '13', 
    true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--      Select      --0 - Hindi8 - Oo'), '8', 
    true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--GLIP 1GLIP 2IPPaglambo'), '674', 
    true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientAmmendment/select_--Select--Adasen InlaudAetaAetaAlang_82eebf'), 
    '4481', true)

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Save'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/a_ConsentWaiver'))

((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -500);")

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Amend'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/input_YES_healthDeclaration'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/input_Health Declaration_healthDeclaration'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Save'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Submit'))

WebUI.verifyElementText(findTestObject('Object Repository/ClientAmmendment/h2_Are you sure you want to proceed'), 'Are you sure you want to proceed?')

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Cancel'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Submit'))

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Yes'))

WebUI.verifyElementText(findTestObject('Object Repository/ClientAmmendment/div_The client amendment has been successfu_5db511'), 
    'The client amendment has been successfully processed. Please inform your approver to save the changes.')

WebUI.click(findTestObject('Object Repository/ClientAmmendment/button_Yes'))

WebUI.delay(2)
Actions actions3 = new Actions(driver)
// Simulate pressing the Enter key using the Actions class
actions3.sendKeys(Keys.ENTER).perform()

WebUI.click(findTestObject('Object Repository/ClientAmmendment/span_Close'))

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Object Repository/Logout/span_(Open)_account-user-avatar'), 30)
WebUI.click(findTestObject('Object Repository/Logout/span_(Open)_account-user-avatar'))

WebUI.click(findTestObject('Object Repository/Logout/i_ChangePassword_mdi mdi-logout me-1'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Logout/img'), 10)

println("Logout Successfull")

WebUI.closeBrowser()

