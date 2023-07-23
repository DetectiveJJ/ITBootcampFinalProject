package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomepagePage;
import pages.bookStoreApp.LoginPage;
import pages.bookStoreApp.ProfilePage;
import pages.SidebarPage;

public class LoginTest extends BaseTest {

    public HomepagePage homepagePage;
    public SidebarPage sidebarPage;
    public LoginPage loginPage;
    public ProfilePage profilePage;


    String validUsername;
    String validPassword;
    String invalidUsername;
    String invalidPassword;


    @BeforeMethod
    public void pageSetUp() {
        homepagePage = new HomepagePage();
        sidebarPage = new SidebarPage();
        loginPage = new LoginPage();
        profilePage = new ProfilePage();

        driver.manage().window().maximize();
        driver.get(homeURL);
    }

    public void getToLoginPage() {
        homepagePage.clickOnCard("Book Store Application");
        sidebarPage.clickOnSidebarButton("Login");
    }

    @Test
    public void userCanLogInWithValidCredentials() {
        validUsername = excelReader.getStringData("Credentials", 1, 0);
        validPassword = excelReader.getStringData("Credentials", 1, 1);

        getToLoginPage();
        loginPage.insertUsername(validUsername);
        loginPage.insertPassword(validPassword);
        loginPage.clickOnLoginButton();

        waitForURL(profilePageURL);
        Assert.assertEquals(driver.getCurrentUrl(), profilePageURL);

        waitForVisibility(profilePage.logOutButton);
        Assert.assertTrue(profilePage.logOutButton.isDisplayed());
    }

    @Test
    public void userCannotLogInWithInvalidCredentials() {
        invalidUsername = excelReader.getStringData("Credentials", 1, 2);
        invalidPassword = excelReader.getStringData("Credentials", 1, 3);

        getToLoginPage();
        waitForVisibility(loginPage.usernameField);
        loginPage.insertUsername(invalidUsername);
        loginPage.insertPassword(invalidPassword);
        loginPage.clickOnLoginButton();

        waitForVisibility(loginPage.invalidCredentialsMessage);
        scrollIntoView(loginPage.invalidCredentialsMessage);
        Assert.assertEquals(loginPage.invalidCredentialsMessage.getText(), unsuccessfulLoginMessage);
        Assert.assertNotEquals(driver.getCurrentUrl(), profilePageURL);
    }

    @Test
    public void userCannotLogInWithInvalidPassword() {
        validUsername = excelReader.getStringData("Credentials", 1, 0);
        invalidPassword = excelReader.getStringData("Credentials", 1, 3);

        getToLoginPage();
        waitForVisibility(loginPage.usernameField);
        loginPage.insertUsername(validUsername);
        loginPage.insertPassword(invalidPassword);
        loginPage.clickOnLoginButton();

        waitForVisibility(loginPage.invalidCredentialsMessage);
        scrollIntoView(loginPage.invalidCredentialsMessage);
        Assert.assertEquals(loginPage.invalidCredentialsMessage.getText(), unsuccessfulLoginMessage);
        Assert.assertNotEquals(driver.getCurrentUrl(), profilePageURL);
    }

    @Test
    public void userCannotLogInWithInvalidUsername() {
        invalidUsername = excelReader.getStringData("Credentials", 1, 2);
        validPassword = excelReader.getStringData("Credentials", 1, 1);

        getToLoginPage();
        waitForVisibility(loginPage.usernameField);
        loginPage.insertUsername(invalidUsername);
        loginPage.insertPassword(validPassword);
        loginPage.clickOnLoginButton();

        waitForVisibility(loginPage.invalidCredentialsMessage);
        scrollIntoView(loginPage.invalidCredentialsMessage);
        Assert.assertEquals(loginPage.invalidCredentialsMessage.getText(), unsuccessfulLoginMessage);
        Assert.assertNotEquals(driver.getCurrentUrl(), profilePageURL);
    }

    @AfterMethod
    public void pageTearDown() {
        driver.manage().deleteAllCookies();
    }


}
