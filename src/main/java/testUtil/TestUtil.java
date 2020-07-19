package testUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import resources.TestBase;

public class TestUtil extends TestBase {

//	public static String convertMonthnumbertoName(String monthNumber) {
//		if (monthNumber.equals("01")) {
//			return "January";
//		} else if (monthNumber.equals("02")) {
//			return "February";
//		} else if (monthNumber.equals("03")) {
//			return "March";
//		} else if (monthNumber.equals("04")) {
//			return "April";
//		} else if (monthNumber.equals("05")) {
//			return "May";
//		} else if (monthNumber.equals("06")) {
//			return "June";
//		} else if (monthNumber.equals("07")) {
//			return "July";
//		} else if (monthNumber.equals("08")) {
//			return "August";
//		} else if (monthNumber.equals("09")) {
//			return "September";
//		} else if (monthNumber.equals("10")) {
//			return "October";
//		} else if (monthNumber.equals("11")) {
//			return "November";
//		} else if (monthNumber.equals("12")) {
//			return "December";
//		}
//		return "Invalid Input";
//	}
//	
	public static void disableAlerts(List<WebElement> alert) {
		try {
			if(alert.size()>=0) {
				alert.get(0).click();

			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	public static String navigateSection(List<WebElement> navigationList, WebElement extraList , String section) {
		String result="";
		try {
		extraList.click();
		for (WebElement navigate : navigationList) {
			if(navigate.getText().equalsIgnoreCase(section)) {
				navigate.click();
				result="Navigated to "+section+" Successfully";
			}
			else {
				result="Navigation failed";
			}
		}
		
	}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return result;
	}
	
	public static boolean defaultCheckedCityList(List<WebElement> cityList, String value) {
		try {
			for (WebElement city : cityList) {
				if(city.getAttribute("id").equalsIgnoreCase(value)) {
					return true;
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e);
		}
		return false;
	}

	public static boolean selectCity(WebElement searchBox, String value) throws InterruptedException {
		searchBox.sendKeys(value);
		Thread.sleep(2000);
		searchBox.sendKeys(Keys.ENTER);
		if(driver.findElement(By.xpath("//input[@id='"+value+"']")).isDisplayed()) {
			driver.findElement(By.xpath("//input[@id='"+value+"']")).click();
			return true;
		}
		else {
			return false;
		}
		
		
	}
	public static boolean getCityWeatherDetails(String city, String filePath) throws InterruptedException, IOException {
		if(driver.findElement(By.xpath("//div[text()='"+city+"']")).isDisplayed()) {
			driver.findElement(By.xpath("//div[text()='"+city+"']")).click();
			List<WebElement> listOfInformation= driver.findElements(By.xpath("//span[@class='heading']/b"));
			Thread.sleep(2000);
			FileWriter myWriter = new FileWriter(filePath); 
			myWriter.write("City: "+city+"\r\n");
			//System.out.println(listOfInformation.size());
			for (WebElement webElement : listOfInformation) {
				System.out.println(webElement.getText());
				myWriter.write(webElement.getText()+"\r\n");
			}
			myWriter.close();
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean createFile(String filePath) {
		boolean fvar=false;
		try {
			File file=new File(filePath);
			fvar=file.createNewFile();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		return fvar;
	}
	
	

//	public static void staticDropdownSelector(WebElement statDropwn, String value) {
//		Select classDropdown = new Select(statDropwn);
//		classDropdown.selectByVisibleText(value);
//	}
//
//	public static void dateSelector(WebElement dateNavigation, WebElement monthPick, String month, WebElement dateBody,
//			String day, String year) throws InterruptedException {
//		if (!monthPick.getText().contains(convertMonthnumbertoName(month))) {
//			while (!monthPick.getText().contains(convertMonthnumbertoName(month))) {
//				dateNavigation.click();
//			}
//		}
//
//		WebElement dateWidget = dateBody;
//		java.util.List<WebElement> col = dateWidget.findElements(By.className("calDate"));
//		for (int i = 0; i < col.size(); i++) {
//			if (dateWidget.findElements(By.className("calDate")).get(i).getAttribute("id")
//					.contains(year + month + day)) {
//				dateWidget.findElements(By.className("calDate")).get(i).click();
//				break;
//			}
//		}
//	}
//
//	public static void autoSuggestiveDropdownwithKeys(WebElement dropDown, String valueToBeSelected)
//			throws InterruptedException {
//
//		while (dropDown.getAttribute("value").contains(valueToBeSelected)) {
//
//			Thread.sleep(1000);
//			dropDown.sendKeys(Keys.ARROW_DOWN);
//			Thread.sleep(1000);
//			dropDown.sendKeys(Keys.ENTER);
//			break;
//
//		}
//
//	}
//	
//	public static void autoSuggestiveDropdown(WebElement dropDown, String valueToBeSelected)
//			throws InterruptedException {
//		dropDown.click();
//		dropDown.sendKeys(valueToBeSelected);
//		String xpath="//div[@title='"+valueToBeSelected+"']";
//		driver.findElement(By.xpath(xpath)).click();
//
//	}
//
//	public static ArrayList<String> getData(String sheetName, String excelPath, String guestName) throws IOException {
//		FileInputStream file = new FileInputStream(excelPath);
//		@SuppressWarnings("resource")
//		XSSFWorkbook workbook = new XSSFWorkbook(file);
//		int sheetCount = workbook.getNumberOfSheets();
//		ArrayList<String> list = new ArrayList<String>();
//		for (int i = 0; i < sheetCount; i++) {
//			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
//				XSSFSheet sheet = workbook.getSheetAt(i);
//				Iterator<Row> row = sheet.iterator();
//				while (row.hasNext()) {
//					Row rowObject = row.next();
//					if (rowObject.getCell(0).getStringCellValue().contains(guestName)) {
//						Iterator<Cell> cellObject = rowObject.cellIterator();
//						while (cellObject.hasNext()) {
//							Cell cellObject1 = cellObject.next();
//							if (cellObject1.getCellType() == CellType.STRING) {
//								list.add(cellObject1.getStringCellValue());
//							} else if (cellObject1.getCellType() == CellType.NUMERIC) {
//								if (DateUtil.isCellDateFormatted(cellObject1)) {
//									SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//									list.add(dateFormat.format(cellObject1.getDateCellValue()));
//								} else {
//									list.add(Long.toString((long) cellObject1.getNumericCellValue()));
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		return list;
//	}
}
