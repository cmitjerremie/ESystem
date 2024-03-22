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

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*


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
WebUI.delay(3)
WebElement btnLoan = driver.findElement(By.id('btnCreationModal'))

if (btnLoan != null) {
	String disabledAttribute = btnLoan.getAttribute("disabled")
	if (disabledAttribute != null && disabledAttribute.equals("true")) {
		println("Loan is disabled.-Correct!")
	}
	else{
		println("Error: Loan button should be disabled!-Bug")
		bugList.add('Loan Button is not Disabled!-Bug')
	}
}

//String[] loan = ["SIKAP 1", "AGRI LOAN", "SSS PREMIUM LOAN", "EDUCATIONAL LOAN - COLLEGE"];
String[] loan = ["SIKAP 1", "AGRI LOAN"];

// Specify the data file
def loanCreation = findTestData('DoneInitialPay')

// Get the number of rows in the data file
int rowCount = loanCreation.getRowNumbers()

if (rowCount >= 1) {
	String randomCID
	for (int i = 1; i <= rowCount; i++) {
        // Generate a random index to select a CID
        Random random = new Random()
        int randomIndex = random.nextInt(rowCount) + 1
        
        // Get the data for the CID column based on the random index
        randomCID = loanCreation.getValue('CID', randomIndex)
    }

	WebUI.sendKeys(findTestObject('Object Repository/LoanManagementModule/LoanManagementForm/input_NameCID_txtloanCID'),  randomCID + Keys.chord(Keys.ENTER))//GlobalVariable.cid
	
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
	
	// Create a Random object
	Random rand = new Random();

	// Generate a random index within the range of the array length
	int randomIndex111 = rand.nextInt(loan.length);

	// Get the random loan type from the array using the random index
	String randomLoan = loan[randomIndex111];
	
	println("Selected Loan Type: " + randomLoan)
	WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_-- SelectLoanType'), randomLoan, true) //GlobalVariable.loan_type
	//	WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_-- SelectLoanCategory'), 'AGRICULTURE, FORESTRY AND FISHING', true)
	//	WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_LoanPurpose_cboLoanPurpose'), 'Crop Production, Forestry and Logging', true)
	//	WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_BusinessType_cboBusinessType'), 'Corn Farming', true)
	
	// Wait for the element with id 'elementId' to not be present within 10 seconds
	WebUI.delay(3)
	boolean isElementNotPresent = WebUI.waitForElementNotPresent(findTestObject('Object Repository/LoanManagementModule/Additional/div_Existing loan must meet the 70 Payment Policy'), 10)
	
	// If the element is not present, perform actions
	if (isElementNotPresent) {
	    println("Loan is applicable, continue......")
	
		WebElement selectElement = WebUI.findWebElement(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_-- SelectLoanCategory'), 30)
		// Get the total number of options
		List<WebElement> options = selectElement.findElements(By.tagName('option'))
		int optionCount = options.size()
		// Generate a random index
		int randomIndex = new Random().nextInt(optionCount - 1) + 1
		// Get the random option
		WebElement randomOption = options.get(randomIndex)
		WebUI.selectOptionByValue(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_-- SelectLoanCategory'), randomOption.getAttribute('value'), true)
		
		
		WebElement selectElement2 = WebUI.findWebElement(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_LoanPurpose_cboLoanPurpose'), 30)
		// Get the total number of options
		List<WebElement> options2 = selectElement2.findElements(By.tagName('option'))
		int optionCount2 = options2.size()
		// Generate a random index
		int randomIndex2 = new Random().nextInt(optionCount2 - 1) + 1
		// Get the random option
		WebElement randomOption2 = options2.get(randomIndex2)
		WebUI.selectOptionByValue(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_LoanPurpose_cboLoanPurpose'), randomOption2.getAttribute('value'), true)
		
		
		WebElement selectElement3 = WebUI.findWebElement(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_BusinessType_cboBusinessType'), 30)
		// Get the total number of options
		List<WebElement> options3 = selectElement3.findElements(By.tagName('option'))
		int optionCount3 = options3.size()
		// Generate a random index
		int randomIndex3 = new Random().nextInt(optionCount3 - 1) + 1
		// Get the random option
		WebElement randomOption3 = options3.get(randomIndex3)
		WebUI.selectOptionByValue(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_BusinessType_cboBusinessType'), randomOption3.getAttribute('value'), true)
		
		if(randomLoan == "AGRI LOAN")
		{
			WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_LoanFrequency_cboLoanFrequency'), "LumpSum", true)
		}
		else
		{
			WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_LoanFrequency_cboLoanFrequency'), "Weekly", true)
		}
	    //WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_LoanFrequency_cboLoanFrequency'), GlobalVariable.freq, true)
		//WebUI.selectOptionByLabel(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_Term_cboTerm'), '4', true)
		
		// Locate the select element
		WebElement selectElement4 = WebUI.findWebElement(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_Term_cboTerm'), 30)
		// Get the second option
		WebElement secondOption = selectElement4.findElements(By.tagName('option'))[1]
		// Select the second option by its value
		WebUI.selectOptionByValue(findTestObject('Object Repository/LoanManagementModule/LoanCreation/LoanInfo/select_Term_cboTerm'), secondOption.getAttribute('value'), true)
		
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
		
		WebUI.setText(findTestObject('Object Repository/LoanManagementModule/LoanCreation/CashFlow/inputtxtincomeAmount1'), '40000')
		
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
		
		// Scroll to the bottom of the page using Actions class
		Actions actions = new Actions(driver)
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform()
		
		WebUI.delay(1)
		
		WebElement next2 = driver.findElement(By.id('next'))
		next2.click();
		
		WebElement kyc = driver.findElement(By.id('txtRiskClassList'))
		kyc.click()
		
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform()
		
		WebUI.delay(2)
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
		
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform()
		
		WebUI.delay(1)
		
		WebElement proceed = driver.findElement(By.id('btnProceedLoan'))
		proceed.click()
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/LoanManagementModule/Additional/div_i_swal2-success-ring'), 10)
		
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/LoanManagementModule/Additional/div_i  Successful'), 10))
		{
			// Specify the data file
			def loanApproval = findTestData('LoanApproval')
			
			// Get the path to the Excel file
			String excelFilePath = loanApproval.getSourceUrl()
			
			// Open the Excel workbook
			FileInputStream fis = new FileInputStream(excelFilePath)
			XSSFWorkbook workbook = new XSSFWorkbook(fis)
			
			Sheet sheet = workbook.getSheetAt(4)
			
			// Find the last row index (add 1 to get the next available row)
			int lastRowIndex = sheet.getLastRowNum() + 1
			
			// Create a new row
			Row newRow = sheet.createRow(lastRowIndex)
			
			// Add data to the new row
			newRow.createCell(0).setCellValue(randomCID)
			
			// Save the changes
			FileOutputStream fos = new FileOutputStream(excelFilePath)
			workbook.write(fos)
			
			// Close the FileInputStream and workbook
			fis.close()
			fos.close()
			workbook.close()
			
			// Print the added data
			println("Added new record")
			println("Loan saved!")
		}
		else
		{
			println("Error Saving Loan!")
			KeywordUtil.markFailed("Error Saving Loan!");
		}
		
		WebUI.delay(2)
		// Simulate pressing the Enter key using the Actions class
		actions.sendKeys(Keys.ENTER).perform()
	
	} else {
		println("Loan is not applicable, Client Skipped!")
		WebUI.click(findTestObject('Object Repository/LoanManagementModule/Additional/button_OK'))
		WebUI.delay(1)
		KeywordUtil.markFailed("Notice: Please rerun the script, the first random client is currently has loan!");
	}
}
else {
	println("Error: Data file contains no rows.")
	KeywordUtil.markFailed("Error: Data file contains no rows.");
}














