package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomepagePage;
import pages.SidebarPage;
import pages.elements.LinksPage;

import java.util.ArrayList;

public class LinksTest extends BaseTest {

    public HomepagePage homepagePage;
    public SidebarPage sidebarPage;
    public LinksPage linksPage;
    public ArrayList<String> tabs;
    String apiLinkName;
    String apiNumber;

    @BeforeMethod
    public void pageSetUp(){
        homepagePage = new HomepagePage();
        sidebarPage = new SidebarPage();
        linksPage = new LinksPage();

        driver.manage().window().maximize();
        driver.get(homeURL);
        homepagePage.clickOnCard("Elements");
        sidebarPage.clickOnSidebarButton("Links");
    }

    @Test (priority = 10)
    public void clickOnHome(){
        linksPage.clickOnLink(linksPage.homeLink);
        tabs = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(tabs.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), homeURL);
    }

    @Test (priority = 20)
    public void clickOnHomePlus(){
        linksPage.clickOnLink(linksPage.homePlusLink);
        tabs = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(tabs.get(2));
        Assert.assertEquals(driver.getCurrentUrl(), homeURL);
    }

    @Test (priority = 5)
    public void clickOnApiLinks() throws InterruptedException {
        for (int i = 1; i <= excelReader.getLastRow("Messages"); i++) {
            apiLinkName = excelReader.getStringData("Messages", i, 1);
            apiNumber = Integer.toString(excelReader.getIntegerData("Messages", i, 2));
            waitForURL(linksURL);
            linksPage.clickOnApiLink(apiLinkName);

            Thread.sleep(250);
            Assert.assertTrue(linksPage.linkResponse.getText().contains(apiLinkName)
                    && linksPage.linkResponse.getText().contains(apiNumber));
        }
    }

    @AfterMethod
    public void pageTearDown(){
        driver.manage().deleteAllCookies();
    }
}
