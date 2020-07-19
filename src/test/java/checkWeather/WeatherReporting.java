package checkWeather;


import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.WeatherPage;
import testUtil.TestUtil;



public class WeatherReporting extends resources.TestBase {
	public static Logger log=LogManager.getLogger(WeatherReporting.class.getName());


	HomePage homePage;
	WeatherPage weatherPage;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
	}

	@Test(priority = 0)
	public void openApplication() {
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		log.info("Driver is Initialized");
	}

	@Test(priority = 1)
	public void navigateToWeatherSection() {
		try {
			homePage=new HomePage(driver);
			weatherPage = new WeatherPage(driver);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			String result=TestUtil.navigateSection(homePage.navigationLinks(), homePage.extraLink(), prop.getProperty("NAVIGATE"));
			
			
			if(weatherPage.weatherPageLogo().isDisplayed()) {
				log.info(result);
				
				System.out.println(result);
			}
			else {
				fail("Navigation Failed");
				log.error("Navigation Failed");
				System.out.println("Navigation Failed");
			}
			
		}
		
		catch (Exception e) {
			// TODO: handle exception
			fail("Exception occured");
			log.error("Exception occured");
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	@Test(priority = 2)
	public void selectCity() {
		try {
			String city=prop.getProperty("CITY");
			weatherPage=new WeatherPage(driver);
			if(TestUtil.defaultCheckedCityList(weatherPage.defaultCheckedCityList(), city)) {
				log.info("City is already selected");
				System.out.println("City is already selected");
			}
			else {
				if(TestUtil.selectCity(weatherPage.citySearchBox(), city)){
					log.info(city+" selected successfully");
					System.out.println(city+" selected successfully");
				}
				else {
					log.info("City not found");
					System.out.println("City not found");
					fail("City not found");
				}
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error("Exception occured: City not found");
			fail("City not found");
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	@Test(priority = 3)
	public void getCityDetails() {
		try {
			weatherPage=new WeatherPage(driver);
			String filepath=System.getProperty("user.home")+"\\weatherReportGUI.txt";
			System.out.println(filepath);
			if (TestUtil.createFile(filepath)) {
				log.info("File created successfully");
				System.out.println("File created successfully");
			}
			if(TestUtil.getCityWeatherDetails(prop.getProperty("CITY"),filepath)) {
				log.info("City Information successfully processed");
				System.out.println("City Information successfully processed");
			}
			else {
				log.error("City not available in map");
				System.out.println("City not available in map");
				fail("City not available in map");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			fail("Exception Occured:City not available in map");
			log.error("Exception occured");
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	/*
	@Test(priority = 1)
	public void flightSearch() throws IOException, InterruptedException {
		assertTrue(driver.getTitle().contains(prop.getProperty("TITLE")));
		String flightMode = getExcelData().get(4);
		String sourceCity = getExcelData().get(5);
		String destCity = getExcelData().get(6);
		String deptDate = getExcelData().get(7);
		String[] datesplit = deptDate.split("-");
		String deptDay = datesplit[0];
		String deptMonth = datesplit[1];
		String deptYear = datesplit[2];
		String ticketClass = getExcelData().get(8);
		HomePage homePage = new HomePage(driver);
		// driver.switchTo().frame(homePage.frameNotificationLocator());
		// homePage.frameCloseLocator().click();
		// driver.switchTo().defaultContent();
		List<WebElement> flightModeList = homePage.flghtModeList();
		for (int i = 0; i < flightModeList.size(); i++) {
			if (flightModeList.get(i).getText().equalsIgnoreCase(flightMode)) {
				flightModeList.get(i).click();
			}
		}
		homePage.fromLocator().sendKeys(sourceCity);
		TestUtil.autoSuggestiveDropdown(homePage.fromLocator(), sourceCity);
		homePage.toLocator().sendKeys(destCity);
		TestUtil.autoSuggestiveDropdown(homePage.toLocator(), destCity);
		TestUtil.dateSelector(homePage.dateNavbar(), homePage.monthDisplay(), deptMonth, homePage.dateBody(), deptDay,
				deptYear);
		homePage.ticketClassandPassCount().click();
		TestUtil.staticDropdownSelector(homePage.travelClassDropdown(), ticketClass);
		homePage.searchbtn().click();
	}

	@Test(priority = 2)
	public void searchFlight() throws InterruptedException {
		SelectFlightPage selectFlightPage = new SelectFlightPage(driver);
		assertTrue(selectFlightPage.filterText().isEnabled());
		selectFlightPage.stopArrow().click();
		TestUtil.checkBoxSelector(selectFlightPage.stopList(), prop.getProperty("STOPS"));
		selectFlightPage.airlineArrow().click();
		TestUtil.checkBoxSelector(selectFlightPage.airlineList(), prop.getProperty("AIRLINES"));
		selectFlightPage.bookBtn().click();
	}

	@Test(priority = 3)
	public void enterGuestDetails() throws InterruptedException, IOException {
		String guestName = getExcelData().get(0);
		String[] nameSplit = guestName.split(" ");
		String salutation = nameSplit[0];
		String firstName = nameSplit[1];
		String middleName = nameSplit[2];
		String lastName = nameSplit[3];
		String email = getExcelData().get(1);
		String phNumber = getExcelData().get(2);
		TravellerDetails travellerDetails = new TravellerDetails(driver);
		assertTrue(travellerDetails.summaryTextCheck().isEnabled());
		if (travellerDetails.charityCheckbox().isSelected()) {
			travellerDetails.charityCheckbox().click();
		} else if (travellerDetails.insuranceChecbox().isSelected()) {
			travellerDetails.insuranceChecbox().click();
		}
		TestUtil.staticDropdownSelector(travellerDetails.salutationDrpdwn(), salutation);
		travellerDetails.firstNameBox().sendKeys(firstName);
		travellerDetails.middleNameBox().sendKeys(middleName);
		travellerDetails.lastNameBox().sendKeys(lastName);
		travellerDetails.emailBox().sendKeys(email);
		travellerDetails.phoneNumberBox().sendKeys(phNumber);
		travellerDetails.proceedToPaymentBtn().click();
	}

	@Test(priority = 4)
	public void getTicketDetails() throws InterruptedException {
		TravellerDetails travellerDetails = new TravellerDetails(driver);
		System.out.println("---------------FLIGHT DETAILS----------------");
		System.out.println("Airline Name = " + prop.getProperty("AIRLINES"));
		System.out.println("No. of Stops = " + prop.getProperty("STOPS"));
		System.out.println("---------------------------------------------");
		System.out.println("FIRST FLIGHT DETAILS");
		System.out.println("Date of Journey = " + travellerDetails.firstTicketDetailsFlightDateTab().getText());
		System.out.println(
				"Departure City / Departure Time = " + travellerDetails.firstTicketDetailsFlightdeptCity().getText());
		System.out.println(
				"Arrival City / Arrival Time = " + travellerDetails.firstTicketDetailsFlightdestCity().getText());
		System.out.println("Flight Duration = " + travellerDetails.firstTicketDetailsFlightduration().getText());
		System.out.println("---------------------------------------------");
		System.out.println("SECOND FLIGHT DETAILS");
		System.out.println("Date of Journey = " + travellerDetails.secondTicketDetailsFlightDateTab().getText());
		System.out.println(
				"Departure City / Departure Time = " + travellerDetails.secondTicketDetailsFlightdeptCity().getText());
		System.out.println(
				"Arrival City / Arrival Time = " + travellerDetails.secondTicketDetailsFlightdestCity().getText());
		System.out.println("Flight Duration = " + travellerDetails.secondTicketDetailsFlightduration().getText());
		System.out.println("---------------------------------------------");
		System.out.println("TOTAL FARE = " + travellerDetails.totalTicketPrice().getText());
	}

	@Test(priority = 5)
	public void paymentModeSelection() {
		PaymentGateway paymentGateway = new PaymentGateway(driver);
		for (int i = 0; i < paymentGateway.paymentModeTab().size(); i++) {
			if (paymentGateway.paymentModeTab().get(i).getText().toLowerCase()
					.contains(prop.getProperty("PAYMENTMODE").toLowerCase())) {
				paymentGateway.paymentModeTab().get(i).click();
				System.out.println("---------------------------------------------");
				System.out.println("PAYMENT MODE = " + paymentGateway.paymentModeTab().get(i).getText());
				System.out.println("---------------------------------------------");
			}
		}

	}
	*/

	@AfterClass
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}
}
