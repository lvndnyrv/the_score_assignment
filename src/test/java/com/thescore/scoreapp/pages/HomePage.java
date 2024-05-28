package com.thescore.scoreapp.pages;

import com.thescore.scoreapp.utils.TheScoreUtils;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends TheScoreUtils {

    private final WebDriverWait wait;
    private final AndroidDriver driver;

    public HomePage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement getFavoritesBarOptionByText(String text) {
        String dynamicXPath = String.format("//*[@resource-id = 'com.fivemobile.thescore:id/title' and @text = '%s']", text);
        return driver.findElement(By.xpath(dynamicXPath));
    }

    public void navigateToFavoriteTeam(String teamCode) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(getFavoritesBarOptionByText(teamCode)));
        getFavoritesBarOptionByText(teamCode).click();
    }

    public boolean isFeatureSelected(String feature) {
        WebElement element = driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"" + feature + "\"]"));
        return element.getAttribute("selected") != null
                && element.getAttribute("selected").equals("true");
    }
}