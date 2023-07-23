package pages.bookStoreApp;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProfilePage extends BaseTest {

    public ProfilePage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "main-header")
    public WebElement headerProfileText;

    @FindBy(id = "userName-value")
    public WebElement usernameValue;

    @FindBy(css = "#submit")
    public WebElement logOutButton;



}
