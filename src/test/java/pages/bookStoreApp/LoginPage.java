package pages.bookStoreApp;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userName")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "login")
    public WebElement loginButton;

    @FindBy(className = "mb-1")
    public WebElement invalidCredentialsMessage;


    public void insertUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void insertPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnLoginButton(){
        loginButton.click();
    }

}
