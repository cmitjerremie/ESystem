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

//Loan Info Computaion
import java.text.DecimalFormat;

double int_rate = 28.00/100;
int principal = 61118;
int term = 50;

int inter = (int) Math.ceil(principal*int_rate);
double due = (principal + inter) / term;
int fnl_due = (int) Math.ceil(due / 5) * 5;
int loan_outs = principal + inter;
double lrf = (0.3 * principal * term)/1000;
int fnl_lrf = (int) Math.ceil(lrf);
int net = principal - fnl_lrf;

DecimalFormat pesoFormat = new DecimalFormat("#,##0.00");
String formattedInter = pesoFormat.format(inter);
String formattedDue = pesoFormat.format(fnl_due);
String formatted_outs = pesoFormat.format(loan_outs);
String formatted_lrf = pesoFormat.format(fnl_lrf);
String formatted_net = pesoFormat.format(net);

println("Final Due: "+ formattedDue);
println("Interest: "+ formattedInter);
println("Loan Outstanding: "+ formatted_outs);
println("LRF: "+ formatted_lrf);
println("Net Proceed: "+ formatted_net);

import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testdata.ExcelData

//// Name of the internal test data you created in Katalon Studio
//String testDataName = "reg_cid"
//
//// Access the internal test data using TestDataFactory
//ExcelData testData = TestDataFactory.findTestData(testDataName)
//
//// Read data from the Excel sheet
//// For example, let's assume you have a sheet named "Sheet1" in your Excel file
//String dataFromExcel = testData.getValue(1, 1) // Reading data from the first row and first column (A1)
//
//// Print the data read from the Excel sheet
//println("Data from Excel: " + dataFromExcel)
//
//
//// Cast the TestData object to ExcelData
//ExcelData excelData = testData as ExcelData
//
//// Define the new data you want to add
//List<String> newData = ['New Value 1', 'New Value 2'] // Adjust the values as per your requirement
//
//// Get the index of the next row
//int nextRowIndex = excelData.getRowNumbers().size() + 1
//
//// Insert the new data into the Excel file as a single row
//for (int i = 0; i < newData.size(); i++) {
//	excelData.setValue(nextRowIndex, i + 1, newData[i])
//}
//
//// Save changes to the Excel file
//excelData.save()
//
//println("New data inserted into the internal test data successfully.")

import org.apache.commons.lang3.RandomStringUtils


import java.util.Random

// Generate a random first name
def generateRandomFirstName() {
    def firstNameLength = new Random().nextInt(6) + 4 // Random length between 4 and 9 characters
    def firstName = (0..<firstNameLength).collect { ('a'..'z').toList()[new Random().nextInt(26)] }.join()
    return firstName.capitalize()
}

// Generate a random last name
def generateRandomLastName() {
    def lastNameLength = new Random().nextInt(6) + 4 // Random length between 4 and 9 characters
    def lastName = (0..<lastNameLength).collect { ('a'..'z').toList()[new Random().nextInt(26)] }.join()
    return lastName.capitalize()
}

// Generate a random full name
def generateRandomFullName() {
    def firstName = generateRandomFirstName()
    def lastName = generateRandomLastName()
    return "$firstName $lastName"
}

// Generate 5 random full names
(1..5).each {
    println generateRandomFullName()
}







