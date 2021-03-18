package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private static WebElement element = null;
	
	public static WebElement readfullstory_button(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[contains(text(),'Click Here to Read the Full Story')]"));
		return element;
	}
}
