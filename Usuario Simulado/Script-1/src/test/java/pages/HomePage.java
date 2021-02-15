package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private static WebElement element = null;
	
	public static WebElement switch_theme(WebDriver driver) {
		
		element = driver.findElement(By.id("switch-theme"));
		return element;
		
	}

}
