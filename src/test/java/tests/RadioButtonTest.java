package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomepagePage;
import pages.SidebarPage;
import pages.elements.RadioButtonPage;

public class RadioButtonTest extends BaseTest {

    public HomepagePage homepagePage;
    public SidebarPage sidebarPage;
    public RadioButtonPage radioButtonPage;

    @BeforeMethod
    public void pageSetUp(){
        homepagePage = new HomepagePage();
        sidebarPage = new SidebarPage();
        radioButtonPage = new RadioButtonPage();

        driver.manage().window().maximize();
        driver.get(homeURL);
        homepagePage.clickOnCard("Elements");
        sidebarPage.clickOnSidebarButton("Radio Button");
    }

    @Test(priority = 10)
    public void clickOnYes() {
        radioButtonPage.clickOnYesRadio();
        Assert.assertTrue(radioButtonPage.yesRadio.isSelected());
        Assert.assertTrue(radioButtonPage.message.isDisplayed());
        Assert.assertEquals(radioButtonPage.message.getText(), "Yes");
    }

    @Test(priority = 20)
    public void clickOnImpressive() {
        radioButtonPage.clickOnImpressiveRadio();
        Assert.assertTrue(radioButtonPage.impressiveRadio.isSelected());
        Assert.assertTrue(radioButtonPage.message.isDisplayed());
        Assert.assertEquals(radioButtonPage.message.getText(), "Impressive");
    }

    @Test(priority = 30)
    public void clickOnNo() {
        radioButtonPage.clickOnNoRadio();
        Assert.assertFalse(radioButtonPage.noRadio.isSelected());
        Assert.assertFalse(elementIsPresent(radioButtonPage.message));
    }

    @AfterMethod
    public void pageTearDown(){
        driver.manage().deleteAllCookies();
    }
}
