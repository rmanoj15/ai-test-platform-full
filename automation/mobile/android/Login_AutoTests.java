package automation.mobile.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

/**
 * Example Android mobile test – AI-generated tests can follow this template.
 */
public class Login_AutoTests {

    private AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("app", System.getProperty("android.app.path", "/path/to/app.apk"));

        driver = new AndroidDriver<>(new URL(System.getProperty("appium.server.url", "http://localhost:4723/wd/hub")), caps);
    }

    @Test
    public void login_withValidCredentials_shouldShowHomeScreen() {
        driver.findElementByAccessibilityId("usernameInput").sendKeys("testuser");
        driver.findElementByAccessibilityId("passwordInput").sendKeys("password123");
        driver.findElementByAccessibilityId("loginButton").click();

        boolean homeVisible = driver.findElementsByAccessibilityId("homeScreenRoot").size() > 0;
        assertTrue(homeVisible, "Expected home screen to be visible after login");
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
