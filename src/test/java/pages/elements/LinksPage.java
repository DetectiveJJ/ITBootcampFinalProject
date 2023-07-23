package pages.elements;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LinksPage extends BaseTest {

    public LinksPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "simpleLink")
    public WebElement homeLink;

    @FindBy(id = "dynamicLink")
    public WebElement homePlusLink;

    @FindBy(id = "linkResponse")
    public WebElement linkResponse;

    public void clickOnLink(WebElement element){
        element.click();
    }

    public void clickOnApiLink(String name){
        WebElement apiLink = driver.findElement(By.linkText(name));
        apiLink.click();
    }

}
