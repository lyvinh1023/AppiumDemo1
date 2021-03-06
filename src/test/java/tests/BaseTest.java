package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

public class BaseTest {
	AppiumDriver<MobileElement> driver;

	@BeforeTest
	public void beforeTest() {
		try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android SDK built for x86");
			caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "CHROME");
			//caps.setCapability(MobileCapabilityType.APP, "D:\\espresso\\app-debug.apk");
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

	@AfterTest
	public void afterTest() {
		driver.close();
		//driver.closeApp();
		driver.quit();
	}

}
