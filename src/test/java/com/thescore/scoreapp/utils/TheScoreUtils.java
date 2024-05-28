package com.thescore.scoreapp.utils;

import com.thescore.scoreapp.pages.RealMadridPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static com.thescore.scoreapp.base.TestBase.TIME_OUT;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

@Getter
@Setter
public class TheScoreUtils {
    protected AndroidDriver driver;
    private WebDriverWait wait;

    public TheScoreUtils(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.fivemobile.thescore:id/btn_next")
    private WebElement btnNext;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/btn_allow")
    private WebElement btnAllow;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/btn_disallow")
    private WebElement btnDisallow;

    /**
     * Selects the favorite option by locating the option element and clicking on it.
     *
     * @param option The text of the option to be selected.
     */
    public void selectFavoriteOptions(String option) {
        Assert.assertTrue(driver.findElement(By.xpath(String.format("//*[@text = '%s']", option))).isDisplayed());
        driver.findElement(By.xpath((String.format("//*[@text = '%s']", option)))).click();
    }

    /**
     * Scrolls to the visible text on the screen using UiScrollable.
     *
     * @param text The text to be scrolled to.
     */
    public void scrollToVisibleText(String text) {
        driver.findElements(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +
                ".setMaxSearchSwipes(20).scrollIntoView(new UiSelector().textContains(\"" + text + "\"))"));
    }

    /**
     * Checks if the specified text is visible on the screen.
     *
     * @param text The text to check for visibility.
     * @return true if the text is visible, false otherwise.
     */
    public boolean isVisibleTextPresentOnScreen(String text) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(800));
        List<WebElement> elements = driver.findElements(By.xpath(String.format("//*[@text = '%s']", text)));

        return !elements.isEmpty() && elements.get(0).isDisplayed();
    }

    /**
     * Clicks on the 'Next' button to continue to the next page.
     */
    public void continueToNextPage() {
        wait.until(elementToBeClickable(btnNext)).click();
    }

    /**
     * Handles the pop-up by either allowing or disallowing it.
     *
     * @param allowPopUp true to allow the pop-up, false to disallow.
     */
    public void handlePopUps(boolean allowPopUp) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = allowPopUp ? btnAllow : btnDisallow;

        try {
            button.click();
        } catch (StaleElementReferenceException e) {
            button = allowPopUp ? btnAllow : btnDisallow;
            wait.until(elementToBeClickable(button)).click();
        }
        wait.until(invisibilityOf(button));
    }

    /**
     * Clicks on the element after clearing its existing content and sending the specified keys.
     *
     * @param element The element to be clicked.
     * @param sendKey The keys to be sent to the element.
     */
    public void clickClearSendKeysElement(WebElement element, String sendKey) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(visibilityOf(element)).click();
        element.clear();
        element.sendKeys(sendKey);
    }

    /**
     * Navigates back to the previous page OR if keyBoard is visible then hides it.
     */
    public void navigateBack() {
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    /**
     * Gets the subTab with the specified name and waits for it to be clickable.
     *
     * @param tab The name of the subTab.
     * @return true if the subTab is found and clicked, false otherwise.
     */
    public boolean getSubTab(String tab) {
        try {
            WebElement tabElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//*[@text = '%s']", tab.toUpperCase()))));
            tabElement.click();
            wait.until(ExpectedConditions.attributeToBe(tabElement, "selected", "true"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Waits for the specified element to be visible on the screen within the given timeout.
     *
     * @param locator       The WebElement to wait for.
     * @param timeOutMillis The timeout in milliseconds.
     */
    public void waitForElementVisible(WebElement locator, long timeOutMillis) {
        Duration timeOut = Duration.ofSeconds(timeOutMillis == 0 ? TIME_OUT * 1000L : timeOutMillis);

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(locator));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Verifies the soccer team statistics by scrolling to each stat indicator and checking its visibility.
     * Assumption is - all soccer teams have same stat categories.
     */
    public void verifySoccerTeamStats() {
        for (RealMadridPage.SoccerStatCriteria statsIndicators : RealMadridPage.SoccerStatCriteria.values()) {
            int attempts = 0;
            final int maxAttempts = 3;
            while (!isVisibleTextPresentOnScreen(statsIndicators.getStatName()) && attempts < maxAttempts) {
                scrollToVisibleText(statsIndicators.getStatName());
                attempts++;
            }
            if (attempts == maxAttempts) {
                System.out.println("Failed to find stat after scrolling: " + statsIndicators.getStatName());
            }
        }
    }
}