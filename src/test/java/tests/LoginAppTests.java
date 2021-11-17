package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class LoginAppTests {

	AppiumDriver<MobileElement> driver;

	@BeforeTest
	public void beforeTest() {
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android SDK built for x86");
			caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
			caps.setCapability(MobileCapabilityType.APP, "D:\\espresso\\app-debug.apk");
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);

			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AppiumDriver<MobileElement>(url, caps);

		} catch (MalformedURLException e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Error is: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testOnNativeView() throws InterruptedException {
		driver.findElement(By.id("com.example.samplehybridapp:id/navigation_dashboard")).click();
		Thread.sleep(2000);
		assertEquals("This is dashboard page", driver.findElement(By.id("com.example.samplehybridapp:id/text_dashboard")).getText());
		
		driver.findElement(By.id("com.example.samplehybridapp:id/navigation_notifications")).click();
		Thread.sleep(2000);
		assertEquals("This is notifications page", driver.findElement(By.id("com.example.samplehybridapp:id/text_notifications")).getText());
		
		driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id=\"com.example.samplehybridapp:id/navigation_home\"]")).click();
		Thread.sleep(2000);
	}
	
	@Test
	public void testOnWebView() throws InterruptedException {
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
			System.out.println(contextName);

		}
		driver.context("WEBVIEW_com.example.samplehybridapp");
		driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("vinhly");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("password");
		driver.findElement(By.xpath("//input[@value=\"login\"]")).click();
		Thread.sleep(3000);
		assertEquals("Example Domain", driver.findElement(By.xpath("//h1")).getText());
		assertTrue(driver.findElement(By.xpath("//a[text()=\"More information...\"]")).isDisplayed());
	}
	
	@AfterTest
	public void afterTest() {
		driver.closeApp();
		driver.quit();
	}
}
