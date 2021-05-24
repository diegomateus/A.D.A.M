import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pages.HomePageObjects;

public class ExtentReportsTestNG {

	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;

	WebDriver driver;
	
	@BeforeSuite
	public void setUp() {

		// start reporters
		htmlReporter = new ExtentHtmlReporter("extent.html");

		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	@BeforeTest
	public void setUpTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		System.setProperty("webdriver.chrome.driver","/home/federico/A.D.A.M/Usuario Simulado/Script-3/drivers/chromedriver/chromedriver");
		driver = new ChromeDriver(options);
	}

	@Test
	public void test1() throws IOException {
		
		HomePageObjects homePagObj = new HomePageObjects(driver);

		// creates a toggle for the given test, adds all log events under it    
		ExtentTest test = extent.createTest("MyFirstTest", "Sample description");
		driver.get("https://www.broadleafcommerce.com/");
		test.pass("Entrando a broadleafcommerce.com");
		
		homePagObj.clickFullStoryButton();
		test.pass("Se hace click en el botón Click Here To Read The Full Story");
		homePagObj.monthlyNewsletter("fede11.tm@gmail.com");
		test.pass("Se ingresa la dirección de correo electrónico en el campo de texto");
		//homePagObj.clickGetViaInbox();
		test.fail("El botón Get Via Inbox no funciona");
		
		// log(Status, details)
		test.log(Status.INFO, "This step shows usage of log(status, details)");

		// info(details)
		test.info("This step shows usage of info(details)");

		// log with snapshot
		test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());

		// test with snapshot
		test.addScreenCaptureFromPath("screenshot.png");

	}

	@Test
	public void test2() throws IOException {
		
		HomePageObjects homePagObj = new HomePageObjects(driver);

		// creates a toggle for the given test, adds all log events under it    
		ExtentTest test = extent.createTest("MyFirstTest", "Sample description");
		driver.get("https://www.broadleafcommerce.com/");
		test.pass("Entrando a broadleafcommerce.com");
		
		homePagObj.clickFullStoryButton();
		test.pass("Se hace click en el botón Click Here To Read The Full Story");
		homePagObj.monthlyNewsletter("fede11.tm@gmail.com");
		test.pass("Se ingresa la dirección de correo electrónico en el campo de texto");
		homePagObj.clickGetViaInbox();
		test.pass("Se hace click en el botón Get Via Inbox");
		
		// log(Status, details)
		test.log(Status.INFO, "This step shows usage of log(status, details)");

		// info(details)
		test.info("This step shows usage of info(details)");

		// log with snapshot
		
		test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());

		// test with snapshot
		test.addScreenCaptureFromPath("screenshot.png");

	}
	
	@AfterTest
	public void tearDownTest() {
		//close browser
		driver.close();
		driver.quit();
		System.out.println("Load Test Completed Successfully");
	}
	
	@AfterSuite
	public void tearDown() {

		// calling flush writes everything to the log file
        extent.flush();
	}

}
