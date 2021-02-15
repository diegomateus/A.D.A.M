package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserTest {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		
		System.setProperty("webdriver.chromedriver.driver", "./drivers/chromedriver");
		
		driver.get("http://localhost:8080/");
		
		//Header
		
		
		
		
		
		driver.findElement(By.id("switch-theme")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//body/div[@id='main']/div[@id='hero']/div[1]/div[3]/a[1]")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//body/div[1]/div[2]/section[3]/div[2]/a[1]")).click();
		Thread.sleep(3000);
		
		
		driver.findElement(By.id("learn-target")).click();
		Thread.sleep(3000);
		/*
		
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body[1]/header[1]/nav[1]/div[2]/div[3]/ul[1]/li[2]/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//body[1]/header[1]/nav[1]/div[1]/ul[1]/li[5]/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("FirstName")).sendKeys("Diego");
		driver.findElement(By.id("LastName")).sendKeys("Mateus");
		driver.findElement(By.id("Company")).sendKeys("Javeriana");
		driver.findElement(By.id("Email")).sendKeys("diego.mateus@prueba.com");
		driver.findElement(By.id("Title")).sendKeys("Estudiante");
		driver.findElement(By.id("Country")).click();
		driver.findElement(By.xpath("//body[1]/div[1]/div[2]/section[1]/div[1]/div[2]/form[1]/div[6]/div[1]/div[2]/select[1]/option[50]")).click();
		driver.findElement(By.id("mktoCheckbox_103435_0")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("mktoButtonRow")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("logo-focus")).click();
		driver.findElement(By.id("Email")).sendKeys("diego.mateus@prueba.com");
		driver.findElement(By.id("mktoCheckbox_82901_0")).click();
		driver.findElement(By.xpath("//body[1]/footer[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[3]")).click();
		Thread.sleep(1000);
		*/
		
		driver.close();
	}

}
