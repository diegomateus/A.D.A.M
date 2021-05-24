import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import pages.HomePageObjects;

public class BroadleafTestNG {

	private static WebDriver driver = null;

	@BeforeTest
	public void setUpTest() {

		System.setProperty("webdriver.chrome.driver","/home/federico/A.D.A.M/Usuario Simulado/Script-3/drivers/chromedriver/chromedriver");
		driver = new ChromeDriver();
	}

	@Test
	public void homePageTest() throws InterruptedException {

		driver.get("https://www.broadleafcommerce.com/");

		HomePageObjects homePagObj = new HomePageObjects(driver);

		homePagObj.clickFullStoryButton();
		homePagObj.monthlyNewsletter("fede11.tm@gmail.com");
		homePagObj.clickGetViaInbox();

		Thread.sleep(3000);
	}

	@AfterTest
	public void tearDownTest() {

		driver.close();
		driver.quit();
		System.out.println("Load Test Completed Succesfully!");
	}

}
