package com.thescore.scoreapp.tests;

import com.thescore.scoreapp.base.TestBase;
import com.thescore.scoreapp.pages.*;
import com.thescore.scoreapp.utils.TheScoreUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TheScoreTest extends TestBase {

    RealMadridPage realMadridPage;
    WelcomePage welcomePage;
    LeagueSelectionPage leagueSelection;
    FavouriteTeamsPage favouriteTeams;
    HomePage homePage;

    @BeforeClass
    @Override
    public void setUp() throws MalformedURLException {
        super.setUp();
        welcomePage = new WelcomePage(driver);
        leagueSelection = new LeagueSelectionPage(driver);
        favouriteTeams = new FavouriteTeamsPage(driver);
        homePage = new HomePage(driver);
        realMadridPage = new RealMadridPage(driver);
    }

    @DataProvider(name = "theScoreData")
    public Object[][] theScoreData() {
        return new Object[][]{
                {"Spain Soccer", "Real Madrid", "RMD", "TEAM STATS"}
        };
    }

    @Test(dataProvider = "theScoreData")
    public void ValidateFavoriteTeamSelectionAndNavigation(String league, String teamName, String teamCode, String subTab) {
        welcomePage.waitForElementVisible(welcomePage.getWelcomeText(), TIME_OUT);
        welcomePage.getStarted();

        leagueSelection.selectFavoriteOptions(league);
        leagueSelection.continueToNextPage();

        favouriteTeams.handlePopUps(false);
        favouriteTeams
                .searchFavouriteTeam(teamName)
                .selectFavoriteOptions(teamCode);
        favouriteTeams.navigateBack();
        favouriteTeams.continueToNextPage();
        favouriteTeams.handlePopUps(false);

        homePage.waitForElementVisible(new TheScoreUtils(driver).getBtnAllow(), TIME_OUT);
        homePage.handlePopUps(true);
        Assert.assertTrue(homePage.isFeatureSelected("Favorites"), "Favorites feature is not selected");
        homePage.navigateToFavoriteTeam(teamCode);

        Assert.assertEquals(realMadridPage.getTeamName(), teamName, "Team name doesn't match!");
        Assert.assertTrue(realMadridPage.getSubTab(subTab), "Sub-Tab is NOT selected after tapping!");
        realMadridPage.verifySoccerTeamStats();
        realMadridPage.navigateBack();

        homePage.handlePopUps(false);
        Assert.assertTrue(homePage.isVisibleTextPresentOnScreen("FEED"));
    }
}
