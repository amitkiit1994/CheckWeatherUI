package pageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WeatherPage {
	public WebDriver driver;

	public WeatherPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement weatherPageLogo() {
		return driver.findElement(By.xpath("//div[text()='Current weather conditions in your city.']"));
	}
	public List<WebElement> defaultCheckedCityList() {
		return driver.findElements(By.xpath("//input[@checked='checked']"));
	}
	public WebElement citySearchBox() {
		return driver.findElement(By.xpath("//input[@id='searchBox']"));
	}

	public List<WebElement> cityWeatherDetails(){
		return driver.findElements(By.xpath("//span[@class='heading']/b"));
	}
	
	
	
}
