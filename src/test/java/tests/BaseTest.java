package tests;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

public class BaseTest {
	AppiumDriver<MobileElement> driver;

	@BeforeTest
	public void beforeTest() {
		try {
			System.out.println("before test..............");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android SDK built for x86");
			caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
			// caps.setCapability(MobileCapabilityType.APP, "");
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "CHROME");
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);

			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AppiumDriver<MobileElement>(url, caps);

		} catch (MalformedURLException e) {
			System.out.println("Cause is: " + e.getCause());
			System.out.println("Error is: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
		driver.close();
		driver.quit();
	}

}
