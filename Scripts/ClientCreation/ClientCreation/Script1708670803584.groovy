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

WebUI.navigateToUrl('http://10.9.2.27:8880/eSystemNextGenWebApp/pages/Login')

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtUsername'), GlobalVariable.user2)

WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtPassword'), GlobalVariable.pass2)

WebUI.doubleClick(findTestObject('Object Repository/LoginPage/itoggletxtPassword'))

WebUI.click(findTestObject('Object Repository/LoginPage/button_Login'))

WebUI.click(findTestObject('Object Repository/ClientCreation/span_Client_menu-arrow'))


WebUI.click(findTestObject('Object Repository/ClientCreation/a_ClientCreation'))

// Get the WebDriver instance
def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_FirstName_txtFname'), 'Mang')

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_LastName_txtLname'), 'Tomas')

WebUI.click(findTestObject('Object Repository/ClientCreation/button_SEARCH'))

WebUI.click(findTestObject('Object Repository/ClientCreation/button_CREATE'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--NewTransferred'), '1820', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_-- Select --4Ps BeneficiaryArtistChi_05c006'), 
    '1787', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_-- Select --Atty.Dr.Eng.Hon.MrMrsMs'), '155', 
    true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--AnnulledLive-inMarriedSepa_54a068'), 
    '165', true)

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_FirstName_txtmothersMaidenFName'), 'MT Mname')

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_LastName_txtmothersMaidenLName'), 'MT Lname')

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--AglipayanBorn AgainEvangel_87720a'), 
    '0', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--AglipayanBorn AgainEvangel_87720a'), 
    '422', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--No Formal EducationElement_aa261c'), 
    '173', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--BusinessGovernment Employe_69ab64'), 
    '491', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--Accommodation and Food Ser_e45bf9'), 
    '687', true)

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_Birthday_txtbday'), '01011999')
WebUI.sendKeys(findTestObject('Object Repository/ClientCreation/input_Birthday_txtbday'), Keys.ENTER.toString())


WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --Abra - CAR (Co_3be376'), 
    '10488', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --AlaminosBayBia_8f7d3f'), 
    '10548', true)

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_0.00'), '09123456780')

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--farmfarm 2farm 3'), '0', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--farmfarm 2farm 3'), '2709', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --villagevillage_79bc42'), 
    '2712', true)

WebUI.click(findTestObject('Object Repository/ClientCreation/input_YES_CARDScholar'))

WebUI.click(findTestObject('Object Repository/ClientCreation/input_YES_FormerEmployee'))

WebUI.click(findTestObject('Object Repository/ClientCreation/button_NEXT'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --Abra - CAR (Co_3be376_1'), 
    '10488', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --AlaminosBayBia_8f7d3f_1'), 
    '10548', true)

WebUI.click(findTestObject('Object Repository/ClientCreation/input_Same with the Present AddressPhysical_c9b861'))

WebUI.click(findTestObject('Object Repository/ClientCreation/button_NEXT'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--Barangay CertificateBIR TI_0033e0'), 
    '738', true)

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_IDNumber_txtIdNumber'), '123')

WebUI.click(findTestObject('Object Repository/ClientCreation/button_NEXT'))

WebUI.click(findTestObject('Object Repository/ClientCreation/input_DOSRI_dosri'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_-- Select --CARD MRI MemberCARD MRI _8bd785'), 
    '1805', true)

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_Referredby_txtReferredbyOtherPrimary'), 'MT refer')

WebUI.click(findTestObject('Object Repository/ClientCreation/input_YES_ImmediateFamilyMember'))

WebUI.click(findTestObject('Object Repository/ClientCreation/button_NEXT'))

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_Remarks_txtBeneficiaryName1'), 'MT dependent')

WebElement dep_bday = driver.findElement(By.id('txtBeneficiaryBday1'))
dep_bday.sendKeys('01011999')

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--BrotherBrother-in-LawCousi_e1fca2'), 
    '763', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--FemaleMale'), '75', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--No Formal EducationElement_aa261c_1'), 
    '173', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--YESNO'), '0', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--YESNO_1'), '0', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--BeneficiaryLegal Dependent_a71cea'), 
    '1803', true)

WebUI.click(findTestObject('Object Repository/ClientCreation/button_NEXT'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_Select      51015'), '1', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_Select      51015_1'), '4', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_Select      51020'), '7', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_Select      01020'), '11', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_Select      0030'), '14', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_Select      51015_1_2'), '16', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_Select      51015_1_2_3'), '19', true)

WebUI.click(findTestObject('Object Repository/ClientCreation/button_NEXT'))

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_Numberof Household Member_txtHouseholdMember'), '5')

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--EmploymentSalariesIncome f_b99596'), 
    '0', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--EmploymentSalariesIncome f_b99596'), 
    '1759', true)

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_Sourceof Fund_txtOtherKYCSourceOfIncome'), 'MT test Company')

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--Below 1,000 pesos1001 - 3,_8e7891'), 
    '0', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--Below 1,000 pesos1001 - 3,_8e7891'), 
    '1776', true)

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_HouseholdMonthly Expense_txtMonthlyExpense'), '5000.00')

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_PostGraduate_txtHighSchool'), '1')

WebUI.setText(findTestObject('Object Repository/ClientCreation/input_Vocational_txtSeniorHighSchool'), '1')

WebUI.click(findTestObject('Object Repository/ClientCreation/button_NEXT'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --0 - ARMM8 - Il_f2aada'), 
    '9', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --0 - 7 o higit _f836c6'), 
    '9', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --0 - Ika-6 na b_ee969e'), 
    '0', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --0 - Magaang ma_b84cbb'), 
    '5', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --0 - Magaang ma_840447'), 
    '0', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --0 - Wala7 - Oo'), '0', 
    true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --0 - Iba pa5 - _f3691f'), 
    '10', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --0 - Hindi13 - Oo'), '0', 
    true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --0 - Hindi11 - Oo'), '11', 
    true)

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--      Select      --0 - Hindi8 - Oo'), '0', 
    true)

//WebUI.click(findTestObject('Object Repository/ClientCreation/button_NEXT'))
//
//WebUI.click(findTestObject('Object Repository/ClientCreation/button_OK'))

WebUI.selectOptionByValue(findTestObject('Object Repository/ClientCreation/select_--Select--DSHPGLIP 1GLIP 2IPPaglambo_b40e80'), 
    '677', true)

WebUI.click(findTestObject('Object Repository/ClientCreation/button_NEXT'))

WebUI.click(findTestObject('Object Repository/ClientCreation/button_NEXT'))

WebUI.click(findTestObject('Object Repository/ClientCreation/button_NEXT'))

WebUI.click(findTestObject('Object Repository/ClientCreation/button_NEXT'))
