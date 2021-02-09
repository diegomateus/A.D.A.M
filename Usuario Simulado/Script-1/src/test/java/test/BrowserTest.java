package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserTest {
	
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		
		System.setProperty("webdriver.chromedriver.driver", "./drivers/chromedriver");
		
		driver.get("https://localhost:8080/");
	}

}
