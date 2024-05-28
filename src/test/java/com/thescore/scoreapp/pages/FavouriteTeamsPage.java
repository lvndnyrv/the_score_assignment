package com.thescore.scoreapp.pages;

import com.thescore.scoreapp.utils.TheScoreUtils;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FavouriteTeamsPage extends TheScoreUtils {
    private WebDriverWait wait;
    private AndroidDriver driver;
    public FavouriteTeamsPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(id = "com.fivemobile.thescore:id/title")
    private WebElement selectedLeague;

    @FindBy(xpath = "//*[contains(@text, 'Search')]")
    private WebElement searchBar;

    public FavouriteTeamsPage searchFavouriteTeam(String favouriteTeam) {
        searchBar.click();
        clickClearSendKeysElement(searchBar, favouriteTeam);
        return this;
    }
}
