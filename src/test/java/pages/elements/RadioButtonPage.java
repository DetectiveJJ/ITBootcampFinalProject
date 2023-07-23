package pages.elements;

import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RadioButtonPage extends BaseTest {

    public RadioButtonPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "yesRadio")
    public WebElement yesRadio;

    @FindBy(id = "impressiveRadio")
    public WebElement impressiveRadio;

    @FindBy(id = "noRadio")
    public WebElement noRadio;

    @FindBy(className = "text-success")
    public WebElement message;

    public void clickOnYesRadio() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", yesRadio);
    }

    public void clickOnImpressiveRadio() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", impressiveRadio);
    }

    public void clickOnNoRadio() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", noRadio);
    }

}
