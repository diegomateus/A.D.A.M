package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageObjects {

	WebDriver driver = null;
	
	By readfullstory_button = By.xpath("//span[contains(text(),'Click Here to Read the Full Story')]");
	By getviaemail_button = By.id("newsletter-subscribe");

	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickFullStoryButton() {
		driver.findElement(readfullstory_button).click();
	}
	
	public void monthlyNewsletter(String email) {
		driver.findElement(By.cssSelector("#newsletter-email")).sendKeys(email);
	}
	
	public void clickGetViaInbox() {
		driver.findElement(getviaemail_button).click();
	}
}
