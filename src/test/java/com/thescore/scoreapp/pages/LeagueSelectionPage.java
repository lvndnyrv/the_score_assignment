package com.thescore.scoreapp.pages;

import com.thescore.scoreapp.utils.TheScoreUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@Getter
@Setter
public class LeagueSelectionPage extends TheScoreUtils {

    private WebDriverWait wait;
    private AndroidDriver driver;

    public LeagueSelectionPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AndroidFindBy(id = "com.fivemobile.thescore:id/title")
    private WebElement selectedLeague;

    @Override
    public void selectFavoriteOptions(String option) {
        if (!isVisibleTextPresentOnScreen(option)) {
            scrollToVisibleText(option);
        }
        Assert.assertTrue(driver.findElement(By.xpath(String.format("//*[@text = '%s']", option))).isDisplayed());
        driver.findElement(By.xpath((String.format("//*[@text = '%s']", option)))).click();
        Assert.assertTrue(selectedLeague.isDisplayed());
    }
}