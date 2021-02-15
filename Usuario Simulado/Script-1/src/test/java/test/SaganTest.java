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
		//header(driver);
		footer(driver);
		Thread.sleep(3000);
		driver.close();

	}
	
	public static void header(WebDriver driver) throws InterruptedException {
		Header.switch_theme(driver).click();
		Header.community_target_list(driver).click();
		Header.learn_target_list(driver).click();
		Header.project_target_list(driver).click();
		Header.why_spring_target_list(driver).click();
		Header.support_target(driver).click();
		Header.training_target(driver).click();
		Header.spring_logo(driver).click();
	}
	
	public static void footer(WebDriver driver) throws InterruptedException {
		Footer.email(driver).sendKeys("adam@prueba.com");
		Footer.agree_check_box(driver).click();
		Footer.subscribe_button(driver).click();
		Footer.youtube_icon(driver).click();
	}
}
