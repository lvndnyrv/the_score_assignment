package com.thescore.scoreapp.pages;

import com.thescore.scoreapp.utils.TheScoreUtils;
import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
@Setter
public class WelcomePage extends TheScoreUtils {

    private WebDriverWait wait;

    private AndroidDriver driver;
    public WelcomePage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(id = "com.fivemobile.thescore:id/txt_welcome")
    private WebElement welcomeText;

    @FindBy(id = "com.fivemobile.thescore:id/btn_get_started")
    private WebElement getStarted;

    /**
     * Method will navigate from Welcome Page to League Selection!
     */
    public void getStarted() {
        this.wait.until(ExpectedConditions.elementToBeClickable(getStarted)).click();
    }
}
