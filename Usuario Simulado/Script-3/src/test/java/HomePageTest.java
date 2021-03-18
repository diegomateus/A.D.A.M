import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import pages.HomePage;

public class HomePageTest {
	
	private static WebDriver driver = null;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","/home/federico/A.D.A.M/Usuario Simulado/Script-3/drivers/chromedriver/chromedriver");

		driver = new ChromeDriver();

		driver.get("https://www.broadleafcommerce.com/");

		/*WebElement fullstory = driver.findElement(By.xpath("//span[contains(text(),'Click Here to Read the Full Story')]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(fullstory).click().perform();*/

		HomePage.readfullstory_button(driver).click();
		
		/*WebElement email = driver.findElement(By.id("newsletter-email"));
		email.sendKeys("fede11.tm@gmail.com");
		WebElement email_button = driver.findElement(By.id("newsletter-subscribe"));
		builder.moveToElement(email_button).click().perform();*/

		Thread.sleep(3000);

		driver.close();
		
		System.out.println("Test Completed Succesfully!");

	}

}
