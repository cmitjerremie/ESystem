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

import java.util.Random

// Function to generate a random first name
String generateRandomFirstName() {
	String vowels = "aeiou"
	String consonants = "bcdfghjklmnpqrstvwxyz"
	Random rand = new Random()
	
	StringBuilder firstNameBuilder = new StringBuilder()
	int length = rand.nextInt(5) + 4 // Random length between 4 to 8 characters
	for (int i = 0; i < length; i++) {
		if (i % 2 == 0) {
			firstNameBuilder.append(consonants.charAt(rand.nextInt(consonants.length())))
		} else {
			firstNameBuilder.append(vowels.charAt(rand.nextInt(vowels.length())))
		}
	}
	
	return firstNameBuilder.toString().substring(0,1).toUpperCase() + firstNameBuilder.toString().substring(1)
}

// Function to generate a random last name
String generateRandomLastName() {
	String vowels = "aeiou"
	String consonants = "bcdfghjklmnpqrstvwxyz"
	Random rand = new Random()
	
	StringBuilder lastNameBuilder = new StringBuilder()
	int length = rand.nextInt(7) + 5 // Random length between 5 to 11 characters
	for (int i = 0; i < length; i++) {
		if (i % 2 == 0) {
			lastNameBuilder.append(consonants.charAt(rand.nextInt(consonants.length())))
		} else {
			lastNameBuilder.append(vowels.charAt(rand.nextInt(vowels.length())))
		}
	}
	
	return lastNameBuilder.toString().substring(0,1).toUpperCase() + lastNameBuilder.toString().substring(1)
}

for(int x=0; x<10;x++)
{
	// Generate a random first name
	String randomFirstName = generateRandomFirstName()
	
	// Generate a random last name
	String randomLastName = generateRandomLastName()
	
	//println("Random First Name: " + randomFirstName)
	//println("Random Last Name: " + randomLastName)
	
	println("Random First Name: " + randomFirstName + " " + randomLastName)
}

