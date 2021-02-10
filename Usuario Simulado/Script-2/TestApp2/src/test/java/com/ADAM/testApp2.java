package com.ADAM;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class testApp2 {
	 
	private WebDriver driver; 
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/williambaquero/Documents/driver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/");
		
	}
	
	@Test
	public void test() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[3]/a")).click();
		Thread.sleep(2000);
		
		getOwner("Baqueroo");
		Thread.sleep(2000);
		/*if(driver.findElement(By.xpath("/html/body/div/div/h2")).isEnabled()) {
			driver.findElement(By.linkText("William Baquero")).click();
		}else{*/
			addOwner("William","Baquero","U.Javeriana","Bogota","3248034821");
		//}
		//editOwner("William","Baquero","U.Javeriana","Bogota","3248034821");
		addPet("buck","1998-12-12",1);
		addPet("cuak","1998-12-12",2);
		editOwner("","",""," Colina","");
		editPet("","",4);
		addPet("bruno","1998-12-12",3);
		addVisit("Mordida");
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		
		driver.close();
	}
	
	public void addOwner(String firstName, String lastName, String address, String city, String telephone) throws InterruptedException {
		
		driver.findElement(By.linkText("Add Owner")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		driver.findElement(By.id("address")).sendKeys(address);
		driver.findElement(By.id("city")).sendKeys(city);
		driver.findElement(By.id("telephone")).sendKeys(telephone);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/button")).click();
		Thread.sleep(2000);
	}
	
	public void addPet(String name, String date, int i) throws InterruptedException {
		driver.findElement(By.linkText("Add New Pet")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("birthDate")).sendKeys(date);
		Select tipo = new Select(driver.findElement(By.id("type")));
		tipo.selectByIndex(i);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/button")).click();
		Thread.sleep(2000);
	}
	
	public void addVisit(String description) throws InterruptedException {
		driver.findElement(By.linkText("Add Visit")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("description")).sendKeys(description);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/button")).click();
		Thread.sleep(2000);
	}
	
	
	public void editOwner(String firstName, String lastName, String address, String city, String telephone) throws InterruptedException {
		
		driver.findElement(By.linkText("Edit Owner")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		driver.findElement(By.id("address")).sendKeys(address);
		driver.findElement(By.id("city")).sendKeys(city);
		driver.findElement(By.id("telephone")).sendKeys(telephone);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/button")).click();
		
	}
	
public void editPet(String name, String date, int i) throws InterruptedException {
	
	driver.findElement(By.linkText("Edit Pet")).click();
	Thread.sleep(1000);
	driver.findElement(By.id("name")).sendKeys(name);
	driver.findElement(By.id("birthDate")).sendKeys(date);
	Select tipo = new Select(driver.findElement(By.id("type")));
	tipo.selectByIndex(i);
	Thread.sleep(1000);
	driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/button")).click();
	Thread.sleep(2000);
	
	}
	
	public void getOwner(String lastName) throws InterruptedException {
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		driver.findElement(By.xpath("/html/body/div/div/form/div[2]/div/button")).click();
		Thread.sleep(1000);
	}

	

}
