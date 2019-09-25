package com.upgrade.qa.utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.upgrade.qa.base.TestBase;

//This  java class contains all utility methods declared in static so that the test cases can easily access the most commonly used methods/variables/constants

public class TestUtil extends TestBase {

	private static final String STARTDATE = "01/01/1930";
	private static final String ENDDATE = "01/01/2000";
	public static final long PAGE_LOAD_TIMEOUT = 20;
	public static final long IMPLICIT_WAIT = 20;
	public static final String LOAN_AMOUNT = "loan";
	public static final String pattern = "MM/DD/YYYY";
	public static final String DOB_ERROR_MESSAGE = "You must be at least 18 years old.";

	public final static String TESTDATA_SHEET_PATH = "/Users/alekhya/eclipse-workspace/UpgradeLoanUiTestClient/src/main/java/com/upgrade/qa/testdata/testdata.xlsx";

	static Workbook book;
	static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

	public static void takeScreeshot() throws IOException {

		File Src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(Src, new File("user.dir" + "/Screeshots/" + System.currentTimeMillis() + ".png"));
	}

	public static Boolean isWithinDateRange(String testDate) {

		Boolean isInRange = false;
		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

			isInRange = !(dateFormat.parse(testDate).before(dateFormat.parse(STARTDATE))
					|| dateFormat.parse(testDate).after(dateFormat.parse(ENDDATE)));

		} catch (Exception e) {

			e.printStackTrace();

		}

		return isInRange;

	}

}
