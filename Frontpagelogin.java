package frontpageapp.frontpage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Frontpagelogin {
	 public static void main(String[] args) throws Exception {

	        // Create an AndroidDriver object and set the desired capabilities
	        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), getDesiredCapabilities());
	        driver.openNotifications();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.findElement(By.id("in.crowdware.one.debug:id/text_skip")).click();
	        driver.findElement(By.id("in.crowdware.one.debug:id/edit_text_phone_number")).sendKeys("8892541090");
	        driver.findElement(By.id("in.crowdware.one.debug:id/fab")).click();
	        AndroidElement notification = driver.findElementByXPath("//android.widget.TextView[contains(@text, 'OTP for FrontPage')]");
		 
	        // Extract OTP from notification text
	        String notificationText = notification.getText();
	        String otp = extractOtpFromText(notificationText);
	        System.out.println(otp);
	        driver.pressKey(new KeyEvent(AndroidKey.BACK));
	        driver.quit();
	    }

	    private static DesiredCapabilities getDesiredCapabilities() {
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        // Set desired capabilities, e.g. app package name and activity name, device name, platform name and version
	        capabilities.setCapability("deviceName", "emulator");
	        capabilities.setCapability("platformName", "android");
	        capabilities.setCapability("automationName", "UiAutomator2");
	        capabilities.setCapability("app","app-dev-debug-7.apk");
	        return capabilities;
	    }

	    private static String extractOtpFromText(String text) {
	        //OTP Extraction Logic
	        String otp = null;
	        if (text != null) {
	            String[] words = text.split(" ");
	            for (String word : words) {
	                if (word.length() == 6 && word.matches("\\d+")) {
	                    otp = word;
	                    break;
	                }
	            }
	        }
	        return otp;
	    }
	}
