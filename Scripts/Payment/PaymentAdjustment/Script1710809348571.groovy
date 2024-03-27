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
import org.openqa.selenium.By

import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.driver.DriverFactory //for alert

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.JavascriptExecutor;
import java.text.DecimalFormat;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory

WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/i_ri-parking-fill'), 30)

WebUI.click(findTestObject('Object Repository/PaymentModule/i_ri-parking-fill'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/a_PaymentAdjustment'))

def driver = DriverFactory.getWebDriver()

// Switch to the alert
Alert alert = driver.switchTo().alert()

// Accept the alert (you can also use alert.dismiss() to dismiss)
alert.accept()

WebUI.delay(1)

//credit CBU using cash on hand*************************
// Specify the data file

def getCID = findTestData('DoneInitialPay')
int rowCount = getCID.getRowNumbers()

String cid1
String cid2 

// Check if there are enough rows to select 2 random CID values
if (rowCount >= 2) {
   // Generate two random indices
    def randomIndex1 = new Random().nextInt(rowCount) + 1  // Add 1 to ensure index starts from 1
    def randomIndex2

    // Generate the second random index, making sure it's different from the first one
    while (true) {
        randomIndex2 = new Random().nextInt(rowCount) + 1  // Add 1 to ensure index starts from 1
        if (randomIndex2 != randomIndex1) {
            break
        }
    }

    // Get CID values at the random indices
    cid1 = getCID.getValue('CID', randomIndex1)
    cid2 = getCID.getValue('CID', randomIndex2)

    println "Random CID 1: $cid1"
    println "Random CID 2: $cid2"
} else {
    println("Error: Data file contains no rows. - Please add and approve customer first then add cbu!")
    KeywordUtil.markFailed("Error: Data file contains no rows. - Please add and approve customer first then add cbu!")
}


WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid1'), 10)
WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid1'), cid1 + Keys.chord(Keys.ENTER))

WebUI.scrollToElement(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputcbu_credit_c1'), 5)
WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputcbu_credit_c1'), '10')

WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Capital-Build Up_txtCashonHand1'))

WebUI.delay(1)

WebUI.scrollToElement(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputCashOnHand_debit_c1'), 5)

WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputCashOnHand_debit_c1'), '10')

String validation1 = driver.findElement(By.id('txtValidation')).getAttribute('value')
println(validation1)

if(validation1.equals('0.00'))
{
	println("credit CBU using cash on hand* Validation is correct!: " + validation1)
}
else
{
	KeywordUtil.markFailed("credit CBU using cash on hand* Validation is Incorrect! Expected: 0 Actual: " + validation1);
}

WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_PostTransaction'))
WebUI.delay(1)
WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Are you sure you want to proceed this Transaction'), 10)
WebUI.verifyTextPresent("Are you sure you want to proceed this Transaction?", true)
WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Cancel'), 10)
WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Cancel'))
WebUI.delay(1)
WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_PostTransaction'))
WebUI.delay(1)
WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Are you sure you want to proceed this Transaction'), 10)
WebUI.verifyTextPresent("Are you sure you want to proceed this Transaction?", true)
WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Yes'), 10)
WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Yes'))
WebUI.delay(2)
WebUI.waitForElementPresent(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Transaction Posted'), 10)
String msg_trans1 = WebUI.getText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Transaction Posted'))
	
if(msg_trans1 == "Transaction Posted.")
{
	println("credit CBU using cash on hand*************WORKS!************ msg: " + msg_trans1)
}
else
{
	println("credit CBU using cash on hand*************ERROR!************ msg: " + msg_trans1)
	KeywordUtil.markFailed("credit CBU using cash on hand*************ERROR!************ msg: " + msg_trans1);
}

WebUI.delay(2)
Actions action1 = new Actions(driver)
action1.sendKeys(Keys.ENTER).perform()

WebUI.delay(2)

//debit CBU using cash on hand*************************

WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid1'), 10)
WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid1'), cid1 + Keys.chord(Keys.ENTER))

WebUI.scrollToElement(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputcbu_debit_c1'), 5)
WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputcbu_debit_c1'), '10')

WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Capital-Build Up_txtCashonHand1'))

WebUI.delay(1)

WebUI.scrollToElement(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputCashOnHand_credit_c1'), 5)
WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputCashOnHand_credit_c1'), '10')

String validation2 = driver.findElement(By.id('txtValidation')).getAttribute('value')
println(validation2)

if(validation2.equals('0.00'))
{
	println("debit CBU using cash on hand* Validation is correct!: " + validation2)
}
else
{
	KeywordUtil.markFailed("debit CBU using cash on hand* Validation is Incorrect! Expected: 0 Actual: " + validation2);
}

WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_PostTransaction'))
WebUI.delay(1)
WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Are you sure you want to proceed this Transaction'), 10)
WebUI.verifyTextPresent("Are you sure you want to proceed this Transaction?", true)
WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Cancel'), 10)
WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Cancel'))
WebUI.delay(1)
WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_PostTransaction'))
WebUI.delay(1)
WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Are you sure you want to proceed this Transaction'), 10)
WebUI.verifyTextPresent("Are you sure you want to proceed this Transaction?", true)
WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Yes'), 10)
WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/button_Yes'))
WebUI.delay(2)
WebUI.waitForElementPresent(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Transaction Posted'), 10)
String msg_trans2 = WebUI.getText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/h2_Transaction Posted'))
	
if(msg_trans2 == "Transaction Posted.")
{
	println("debit CBU using cash on hand*************WORKS!************ msg: " + msg_trans2)
}
else
{
	println("debit CBU using cash on hand*************ERROR!************ msg: " + msg_trans2)
	KeywordUtil.markFailed("credit CBU using cash on hand*************ERROR!************ msg: " + msg_trans2);
}

WebUI.delay(2)
Actions action2 = new Actions(driver)
action2.sendKeys(Keys.ENTER).perform()

//debit CBU from client 1 and credit to client 2*************************

WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid1'), 10)
WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid1'), cid1 + Keys.chord(Keys.ENTER))

WebUI.delay(1)

WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/i_Cash on Hand_ri-add-circle-fill'), 10)
WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/i_Cash on Hand_ri-add-circle-fill'))

WebUI.delay(1)

WebUI.waitForElementClickable(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid2'), 10)
WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/input_Payment Adjustment_txtCid2'), cid2 + Keys.chord(Keys.ENTER))

WebUI.delay(1)

//WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputcbu_debit_c1'), '50')
//WebUI.setText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/inputcbu_credit_c2'), '50')

// Specify the XPath of the table rows
String tableRowXpath1 = "//table[@id='tblPaymentAdjustmentTable1']//tbody/tr"
// Get the count of rows
int rcount1 = DriverFactory.getWebDriver().findElements(By.xpath(tableRowXpath1)).size()

// Specify the XPath of the table rows
String tableRowXpath2 = "//table[@id='tblPaymentAdjustmentTable2']//tbody/tr"
// Get the count of rows
int rcount2 = DriverFactory.getWebDriver().findElements(By.xpath(tableRowXpath2)).size()

// Print the count
println("Number of rows in the table: " + rcount1)
println("Number of rows in the table: " + rcount2)

String mba_debit = "//table[@id='tblPaymentAdjustmentTable1']//tbody/tr[1]/td[4]/input"
String mba_credit = "//table[@id='tblPaymentAdjustmentTable1']//tbody/tr[1]/td[5]/input"
String loan_debit = "//table[@id='tblPaymentAdjustmentTable1']//tbody/tr[2]/td[4]/input"
String loan_credit = "//table[@id='tblPaymentAdjustmentTable1']//tbody/tr[2]/td[5]/input"
String cbu_debit = "//table[@id='tblPaymentAdjustmentTable1']//tbody/tr["+ (rcount1-1) +"]/td[4]/input"
String cbu_credit = "//table[@id='tblPaymentAdjustmentTable1']//tbody/tr["+ (rcount1-1) +"]/td[5]/input"
String cash_debit = "//table[@id='tblPaymentAdjustmentTable1']//tbody/tr["+ rcount1 +"]/td[4]/input"
String cash_credit = "//table[@id='tblPaymentAdjustmentTable1']//tbody/tr["+ rcount1 +"]/td[5]/input"

String mba_debit2 = "//table[@id='tblPaymentAdjustmentTable2']//tbody/tr[1]/td[4]/input"
String mba_credit2 = "//table[@id='tblPaymentAdjustmentTable2']//tbody/tr[1]/td[5]/input"
String loan_debit2 = "//table[@id='tblPaymentAdjustmentTable2']//tbody/tr[2]/td[4]/input"
String loan_credit2 = "//table[@id='tblPaymentAdjustmentTable2']//tbody/tr[2]/td[5]/input"
String cbu_debit2 = "//table[@id='tblPaymentAdjustmentTable2']//tbody/tr["+ (rcount2-1) +"]/td[4]/input"
String cbu_credit2 = "//table[@id='tblPaymentAdjustmentTable2']//tbody/tr["+ (rcount2-1) +"]/td[5]/input"
String cash_debit2 = "//table[@id='tblPaymentAdjustmentTable2']//tbody/tr["+ rcount2 +"]/td[4]/input"
String cash_credit2 = "//table[@id='tblPaymentAdjustmentTable2']//tbody/tr["+ rcount2 +"]/td[5]/input"

//firstTable
if(rcount1>=4)
{
	WebElement mbaD = DriverFactory.getWebDriver().findElement(By.xpath(mba_debit))
	WebElement mbaC = DriverFactory.getWebDriver().findElement(By.xpath(mba_credit))
	WebElement loanD = DriverFactory.getWebDriver().findElement(By.xpath(mba_debit))
	WebElement loanC = DriverFactory.getWebDriver().findElement(By.xpath(mba_credit))
	WebElement cbuD = DriverFactory.getWebDriver().findElement(By.xpath(cbu_debit))
	WebElement cbuC = DriverFactory.getWebDriver().findElement(By.xpath(cbu_credit))
	WebElement cashD = DriverFactory.getWebDriver().findElement(By.xpath(cash_debit))
	WebElement cashC = DriverFactory.getWebDriver().findElement(By.xpath(cash_credit))
	
	Actions act_t1 = new Actions(DriverFactory.getWebDriver());
	act_t1.moveToElement(cbuD).build().perform();
	cbuD.sendKeys("50")
}
else
{
	
	WebElement mbaD = DriverFactory.getWebDriver().findElement(By.xpath(mba_debit))
	WebElement mbaC = DriverFactory.getWebDriver().findElement(By.xpath(mba_credit))
	WebElement cbuD = DriverFactory.getWebDriver().findElement(By.xpath(cbu_debit))
	WebElement cbuC = DriverFactory.getWebDriver().findElement(By.xpath(cbu_credit))
	WebElement cashD = DriverFactory.getWebDriver().findElement(By.xpath(cash_debit))
	WebElement cashC = DriverFactory.getWebDriver().findElement(By.xpath(cash_credit))
	
	Actions act_t1 = new Actions(DriverFactory.getWebDriver());
	act_t1.moveToElement(cbuD).build().perform();
	cbuD.sendKeys("50")
}
//secondTable
if(rcount2>=4)
{
	WebElement mbaD2 = DriverFactory.getWebDriver().findElement(By.xpath(mba_debit2))
	WebElement mbaC2 = DriverFactory.getWebDriver().findElement(By.xpath(mba_credit2))
	WebElement loanD2 = DriverFactory.getWebDriver().findElement(By.xpath(mba_debit2))
	WebElement loanC2 = DriverFactory.getWebDriver().findElement(By.xpath(mba_credit2))
	WebElement cbuD2 = DriverFactory.getWebDriver().findElement(By.xpath(cbu_debit2))
	WebElement cbuC2 = DriverFactory.getWebDriver().findElement(By.xpath(cbu_credit2))
	WebElement cashD2 = DriverFactory.getWebDriver().findElement(By.xpath(cash_debit2))
	WebElement cashC2 = DriverFactory.getWebDriver().findElement(By.xpath(cash_credit2))
	
	Actions act_t2 = new Actions(DriverFactory.getWebDriver());
	act_t2.moveToElement(cashD2).build().perform();
	cbuC2.sendKeys("50")
}
else
{	
	WebElement mbaD2 = DriverFactory.getWebDriver().findElement(By.xpath(mba_debit2))
	WebElement mbaC2 = DriverFactory.getWebDriver().findElement(By.xpath(mba_credit2))
	WebElement cbuD2 = DriverFactory.getWebDriver().findElement(By.xpath(cbu_debit2))
	WebElement cbuC2 = DriverFactory.getWebDriver().findElement(By.xpath(cbu_credit2))
	WebElement cashD2 = DriverFactory.getWebDriver().findElement(By.xpath(cash_debit2))
	WebElement cashC2 = DriverFactory.getWebDriver().findElement(By.xpath(cash_credit2))
	
	Actions act_t2 = new Actions(DriverFactory.getWebDriver());
	act_t2.moveToElement(cashD2).build().perform();
	cbuC2.sendKeys("50")
}

String validation3 = driver.findElement(By.id('txtValidation')).getAttribute('value')
println(validation3)

if(validation3.equals('0.00'))
{
	println("Validation is correct!: " + validation3)
}
else
{
	println("Validation is Incorrect! Expected: 0 Actual: " + validation3);
	KeywordUtil.markFailed("Validation is Incorrect! Expected: 0 Actual: " + validation3);
}

WebUI.scrollToElement(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/PaymentPosting/button_PostTransaction'), 5)
WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/PaymentPosting/button_PostTransaction'))
WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/PaymentPosting/h2_Are you sure you want to proceed this Transaction'), 10)

WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/PaymentPosting/button_Cancel'))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/PaymentPosting/button_PostTransaction'))
WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/PaymentPosting/h2_Are you sure you want to proceed this Transaction'), 10)

WebUI.click(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/PaymentPosting/button_OK'))
WebUI.waitForElementVisible(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/PaymentPosting/h2_Transaction Posted'), 10)
WebUI.delay(7)
String msg_post = WebUI.getText(findTestObject('Object Repository/PaymentModule/PaymentAdjustment/PaymentPosting/h2_Transaction Posted'))

if(msg_post == "Transaction Posted.")
{
	println("Success msg: " + msg_post)
}
else
{
	println("Something went wrong in payment adjustment! msg: " + msg_post);
	KeywordUtil.markFailed("Something went wrong in payment adjustment! msg: " + msg_post);
}

WebUI.delay(1)
Actions actionL = new Actions(driver)
actionL.sendKeys(Keys.ENTER).perform()

WebUI.delay(2)



