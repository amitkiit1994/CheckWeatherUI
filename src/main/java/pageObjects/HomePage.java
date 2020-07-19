package pageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public List<WebElement> alert() {
		return driver.findElements(By.xpath("//a[text()='No Thanks']"));
	}
	public List<WebElement> navigationLinks() {
		return driver.findElements(By.xpath("//div[@class='topnav_cont']/a"));
	}
	
	public WebElement extraLink() {
		return driver.findElement(By.xpath("//a[@id='h_sub_menu']"));
	}
}
