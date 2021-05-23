import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.HomePageObjects;

public class BroadleafPageTest {

	private static WebDriver driver = null;
	
	public static void main(String[] args) throws InterruptedException {
		
		homePageTest();
	}
	
	public static void homePageTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","/home/federico/A.D.A.M/Usuario Simulado/Script-3/drivers/chromedriver/chromedriver");
		driver = new ChromeDriver();
		
		driver.get("https://www.broadleafcommerce.com/");
		
		HomePageObjects homePagObj = new HomePageObjects(driver);
		
		homePagObj.clickFullStoryButton();
		homePagObj.monthlyNewsletter("fede11.tm@gmail.com");
		homePagObj.clickGetViaInbox();
		
		Thread.sleep(3000);
		driver.close();
		
		System.out.println("Load Test Completed Succesfully!");
		
	}

}
