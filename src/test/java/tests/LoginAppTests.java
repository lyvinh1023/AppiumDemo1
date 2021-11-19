package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class LoginAppTests {

	AppiumDriver<MobileElement> driver;

	@BeforeMethod
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
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
	public void testOnWebView1() throws InterruptedException {
		driver.findElement(By.xpath("//android.widget.EditText[@index=\"2\"]")).sendKeys("testOnWebView1");
		driver.findElement(By.xpath("//android.widget.EditText[@index=\"6\"]")).sendKeys("password");
		driver.findElement(By.xpath("//android.widget.Button[@text=\"login\"]")).click();
		assertEquals("Example Domain", driver.findElement(By.xpath("//android.view.View[@index=\"0\"]")).getText());
		assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text=\"More information...\"]")).isDisplayed());
		Thread.sleep(3000);
	}

	@Test
	public void testOnWebView2() throws InterruptedException {
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
			System.out.println(contextName);

		}
		driver.context("WEBVIEW_com.example.samplehybridapp");
		driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("testOnWebView2");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("password");
		driver.findElement(By.xpath("//input[@value=\"login\"]")).click();
		assertEquals("Example Domain", driver.findElement(By.xpath("//h1")).getText());
		assertTrue(driver.findElement(By.xpath("//a[text()=\"More information...\"]")).isDisplayed());
		Thread.sleep(3000);
	}
	
	@AfterMethod
	public void afterTest() {
		driver.closeApp();
		driver.quit();
	}
}
