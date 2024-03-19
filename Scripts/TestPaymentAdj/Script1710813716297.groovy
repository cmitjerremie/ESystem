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
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*

// Specify the data file
def testDataFile = findTestData('CreatedClients') // Assuming 'Customer' is the name of the data file

// Get the path to the Excel file
String excelFilePath = testDataFile.getSourceUrl()

// Open the Excel workbook
FileInputStream fis = new FileInputStream(excelFilePath)
XSSFWorkbook workbook = new XSSFWorkbook(fis)

// Get the default sheet (assuming there is only one sheet)
Sheet sheet = workbook.getSheetAt(0)

// Define the values of FIRSTNAME, MIDDLENAME, and LASTNAME to delete
def firstNameToDelete = "test"
def middleNameToDelete = "test"
def lastNameToDelete = "test"

// Loop through each row to find and delete the row containing the specified values
for (int rowIndex = sheet.getLastRowNum(); rowIndex >= 0; rowIndex--) {
	Row row = sheet.getRow(rowIndex)
	if (row != null) {
		Cell firstNameCell = row.getCell(0)
		Cell middleNameCell = row.getCell(1)
		Cell lastNameCell = row.getCell(2)
		
		// Check if the values match the ones to be deleted
		if (firstNameCell != null && firstNameCell.getStringCellValue() == firstNameToDelete &&
			middleNameCell != null && middleNameCell.getStringCellValue() == middleNameToDelete &&
			lastNameCell != null && lastNameCell.getStringCellValue() == lastNameToDelete) {
			// Delete the row if found
			sheet.removeRow(row)
			// Shift rows up to fill the gap
			if (rowIndex < sheet.getLastRowNum()) {
				sheet.shiftRows(rowIndex + 1, sheet.getLastRowNum(), -1)
			}
		}
	}
}

// Save the changes
FileOutputStream fos = new FileOutputStream(excelFilePath)
workbook.write(fos)

// Close the FileInputStream and workbook
fis.close()
fos.close()
workbook.close()

// Print a message indicating the deletion
println("Deleted data based on FIRSTNAME, MIDDLENAME, and LASTNAME")
