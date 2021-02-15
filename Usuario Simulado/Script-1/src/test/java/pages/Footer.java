package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Footer {
	
	public static WebElement element = null;

	public static WebElement get_ahead(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[1]/div[1]/div[2]/a[1]"));
		return element;
	}
	
	public static WebElement get_support(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[1]/div[1]/div[3]/a[1]"));
		return element;
	}
	
	public static WebElement upcoming_events(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[1]/div[1]/div[4]/div[1]/a[1]"));
		return element;
	}
	
	public static WebElement upcoming_events_tour(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[1]/div[1]/div[4]/div[2]/a[1]"));
		return element;
	}
	
	public static WebElement why_spring(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/p[1]/strong[1]/a[1]"));
		return element;
	}
	
	public static WebElement learn(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/p[1]/strong[1]/a[1]"));
		return element;
	}
	
	public static WebElement community(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/p[1]/strong[1]/a[1]"));
		return element;
	}
	
	public static WebElement projects(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/p[1]/strong[1]/a[1]"));
		return element;
	}
	
	public static WebElement training(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/p[2]/strong[1]/a[1]"));
		return element;
	}
	
	public static WebElement support(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/p[3]/strong[1]/a[1]"));
		return element;
	}
	
	public static WebElement thank_you(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/p[4]/strong[1]/a[1]"));
		return element;
	}
	
	public static WebElement email(WebDriver driver) {
		element = driver.findElement(By.id("Email"));
		return element;
	}
	
	public static WebElement subscribe_button(WebDriver driver) {
		element = driver.findElement(By.id("mktoCheckbox_82901_0"));
		return element;
	}
	
	public static WebElement agree_check_box(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[3]"));
		return element;
	}
	
	public static WebElement privacy_policy(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/footer[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/div[1]/div[2]/div[2]/label[1]/a[1]"));
		return element;
	}
	
	
	
}
