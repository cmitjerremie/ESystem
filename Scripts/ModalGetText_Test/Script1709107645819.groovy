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

import java.time.LocalDate
import java.time.format.DateTimeFormatter

//WebUI.openBrowser('')
//
//WebUI.maximizeWindow()
//
//WebUI.navigateToUrl(GlobalVariable.url)
//
//WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtUsername'), GlobalVariable.user)
//
//WebUI.setText(findTestObject('Object Repository/LoginPage/inputtxtPassword'), GlobalVariable.pass)
//
//WebUI.doubleClick(findTestObject('Object Repository/LoginPage/itoggletxtPassword'))
//
//WebUI.click(findTestObject('Object Repository/LoginPage/button_Login'))
//
//WebUI.delay(1)
//
//WebUI.click(findTestObject('Object Repository/CBU/i_uil-wallet'))
//
//// Get the WebDriver instance
//def driver = DriverFactory.getWebDriver()
//
//// Switch to the alert
//Alert alert = driver.switchTo().alert()
//
//// Accept the alert (you can also use alert.dismiss() to dismiss)
//alert.accept()
//
//WebUI.delay(1)
//
//WebUI.setText(findTestObject('Object Repository/CBU/inputtxtCID'), '123')
//
//WebUI.sendKeys(findTestObject('Object Repository/CBU/inputtxtCID'), Keys.chord(Keys.ENTER))
//
//String test = WebUI.getText(findTestObject('Object Repository/Test only/div_Invalid CID Number'))
//print(test)

String TestCID = "New Client has been approved CID-10000180 / ACC-1012-0000-00001192"
def matcher = (TestCID =~ /CID-(\d+) \/ ACC-(\d+-\d+-\d+)/)
matcher.find()
String cid = matcher.group(1)
String acc = matcher.group(2)

println(GlobalVariable.gen_cid)
println(GlobalVariable.gen_acc)
GlobalVariable.gen_cid = cid
GlobalVariable.gen_acc = acc
println(GlobalVariable.gen_cid)
println(GlobalVariable.gen_acc)



