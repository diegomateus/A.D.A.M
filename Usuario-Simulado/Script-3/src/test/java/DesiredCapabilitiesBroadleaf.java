import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesBroadleaf {

	private static WebDriver driver;
	
	public static void main(String[] args) {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		
		System.setProperty("webdriver.chrome.driver","/home/federico/A.D.A.M/Usuario Simulado/Script-3/drivers/chromedriver/chromedriver");
		driver = new ChromeDriver(options);
		
		driver.get("https://www.broadleafcommerce.com/");
		
		WebElement email_button = driver.findElement(By.id("newsletter-email"));
		Actions builder = new Actions(driver);
		builder.moveToElement(email_button).click().perform();
		
		//Thread.sleep(3000);
		
		driver.close();
		driver.quit();

	}

}


