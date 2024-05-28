package com.thescore.scoreapp.pages;

import com.thescore.scoreapp.utils.TheScoreUtils;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RealMadridPage extends TheScoreUtils {
    private WebDriverWait wait;
    private AndroidDriver driver;

    public RealMadridPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(id = "com.fivemobile.thescore:id/txt_team_name")
    private WebElement teamName;

    public String getTeamName() {
        return teamName.getText();
    }

    public enum SoccerStatCriteria {
        SHOTS("Shots"),
        SHOTS_ON_TARGET("Shots On Target"),
        GOALS("Goals"),
        PASSES("Passes"),
        COMPLETED_PASSES("Completed Passes"),
        CROSSES("Crosses"),
        LONG_BALLS("Long Balls"),
        TACKLES_ATTEMPTED("Tackles Attempted"),
        TACKLES_SUCCEEDED("Tackles Succeeded"),
        FOULS("Fouls");

        private final String statName;

        SoccerStatCriteria(String statName) {
            this.statName = statName;
        }

        public String getStatName() {
            return statName;
        }
    }
}
