package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class Header {

	public static WebElement element = null;

	public static WebElement switch_theme(WebDriver driver) {
		element = driver.findElement(By.id("switch-theme"));
		return element;
	}

	public static WebElement spring_logo(WebDriver driver) {
		element = driver.findElement(By.id("springlogo"));
		return element;
	}
	
	public static WebElement why_spring_target_list(WebDriver driver) {
		element = driver.findElement(By.id("why-target"));
		return element;
	}
	
	public static WebElement learn_target_list(WebDriver driver) {
		element = driver.findElement(By.id("learn-target"));
		return element;
	}
	
	public static WebElement project_target_list(WebDriver driver) {
		element = driver.findElement(By.id("project-target"));
		return element;
	}
	
	public static WebElement community_target_list(WebDriver driver) {
		element = driver.findElement(By.id("community-target"));
		return element;
	}
	
	public static WebElement training_target(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/header[1]/nav[1]/div[1]/ul[1]/li[4]"));
		return element;
	}
	
	public static WebElement support_target(WebDriver driver) {
		element = driver.findElement(By.xpath("//body[1]/header[1]/nav[1]/div[1]/ul[1]/li[5]/a[1]"));
		return element;
	}
}
