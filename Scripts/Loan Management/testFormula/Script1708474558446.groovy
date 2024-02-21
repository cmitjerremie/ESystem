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







