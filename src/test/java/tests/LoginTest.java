package tests;

import java.awt.RenderingHints.Key;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
  @Test
  public void testOne() throws InterruptedException {
	  driver.get("https://www.google.com/");
	  driver.findElement(By.xpath("//input[@name=\"q\"]")).sendKeys("automation step by step");
	  driver.findElement(By.xpath("//input[@name=\"q\"]")).sendKeys(Keys.ENTER);
	  Thread.sleep(5000);
	  System.out.println("done test one");
  }

  @Test
  public void testTwo() throws InterruptedException {
	  driver.get("https://the-internet.herokuapp.com/login");
	  // driver.findElementById("#username").sendKeys("tomsmith");
	  // driver.findElementById("#password").sendKeys("SuperSecretPassword!");
	  driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys("tomsmith");
	  driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("SuperSecretPassword!");
	  driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
	  Thread.sleep(5000);
	  String message = driver.findElement(By.xpath("//div[@id=\"flash\"]")).getText();
	  System.out.println(message);
  }
}
