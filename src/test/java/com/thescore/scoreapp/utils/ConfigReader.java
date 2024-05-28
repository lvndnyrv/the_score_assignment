package com.thescore.scoreapp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.System.getProperty;

public class ConfigReader {
    Properties properties;
    public ConfigReader() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not read config file");
        }
    }

    public static String getPlatformName() {
        return getProperty("platformName", "Android");
    }

    public static String getTimeOut() {
        return getProperty("timeOut", "60");
    }

    public static String getDeviceName() {
        return getProperty("deviceName", "emulator-5554");
    }

    public static String getAppPackage() {
        return getProperty("appPackage", "com.fivemobile.thescore");
    }

    public static String getAppActivity() {
        return getProperty("appActivity", "com.thescore.startup.activity.StartupActivity");
    }

    public static String getAppWaitActivity() {
        return getProperty("appWaitActivity", "*");
    }

    public static String getAutomationName() {
        return getProperty("automationName", "UiAutomator2");
    }

    public static String getAppPath() {
        return getProperty("user.dir") + "/" + getProperty("appPath", "apps/theScore.apk");
    }

    public static String getHubURL() {
        return getProperty("hubURL", "http://localhost:4723/wd/hub");
    }
}
