package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomepagePage;
import pages.SidebarPage;
import pages.elements.TextBoxPage;

public class TextBoxTest extends BaseTest {

    public HomepagePage homepagePage;
    public SidebarPage sidebarPage;
    public TextBoxPage textBoxPage;

    String fullName;
    String email;
    String currentAddress;
    String permanentAddress;

    @BeforeMethod
    public void pageSetUp(){
        homepagePage = new HomepagePage();
        sidebarPage = new SidebarPage();
        textBoxPage = new TextBoxPage();

        fullName = excelReader.getStringData("TextBox", 1, 0);
        email = excelReader.getStringData("TextBox", 1, 1);
        currentAddress = excelReader.getStringData("TextBox", 1, 2);
        permanentAddress = excelReader.getStringData("TextBox", 1, 3);

        driver.manage().window().maximize();
        driver.get(homeURL);
        homepagePage.clickOnCard("Elements");
        sidebarPage.clickOnSidebarButton("Text Box");
    }

    @Test
    public void userCanSubmitData(){
        textBoxPage.insertFullName(fullName);
        textBoxPage.insertEmail(email);
        textBoxPage.insertCurrentAddress(currentAddress);
        textBoxPage.insertPermanentAddress(permanentAddress);

        scrollIntoView(textBoxPage.submitButton);
        textBoxPage.clickOnSubmitButton();

        waitForVisibility(textBoxPage.outputBox);

        Assert.assertTrue(textBoxPage.nameOutput.getText().contains(fullName));
        Assert.assertTrue(textBoxPage.emailOutput.getText().contains(email));
        Assert.assertTrue(textBoxPage.currentAddressOutput.getText().contains(currentAddress));
        Assert.assertTrue(textBoxPage.permanentAddressOutput.getText().contains(permanentAddress));
    }
}
