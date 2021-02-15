package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

public class SaganTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.chromedriver.driver", "./drivers/chromedriver");
		driver.get("http://localhost:8080/");
		//Probar el header
		header(driver);
		driver.close();

	}
	
	public static void header(WebDriver driver) throws InterruptedException {
		Header.switch_theme(driver).click();
		Thread.sleep(3000);
		Header.community_target_list(driver).click();
		Thread.sleep(3000);
		Header.learn_target_list(driver).click();
		Thread.sleep(3000);
		Header.project_target_list(driver).click();
		Thread.sleep(3000);
		Header.why_spring_target_list(driver).click();
		Thread.sleep(3000);
		Header.support_target(driver).click();
		Thread.sleep(3000);
		Header.training_target(driver).click();
		Thread.sleep(3000);
		Header.spring_logo(driver).click();
		Thread.sleep(3000);
		
	}

}
