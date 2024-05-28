package com.thescore.scoreapp.base;

import com.thescore.scoreapp.utils.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestBase {

    public static final int TIME_OUT = Integer.parseInt(ConfigReader.getTimeOut());
    public AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", ConfigReader.getPlatformName());
        caps.setCapability("deviceName", ConfigReader.getDeviceName());
        caps.setCapability("appPackage", ConfigReader.getAppPackage());
        caps.setCapability("appActivity", ConfigReader.getAppActivity());
        caps.setCapability("appWaitActivity", ConfigReader.getAppWaitActivity());
        caps.setCapability("automationName", ConfigReader.getAutomationName());
        caps.setCapability("app", ConfigReader.getAppPath());

        driver = new AndroidDriver(new URL(ConfigReader.getHubURL()), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_OUT));

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}