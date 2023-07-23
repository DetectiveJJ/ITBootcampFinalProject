package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public WebDriverWait wait;
    public ExcelReader excelReader;

    public String homeURL;
    public String profilePageURL;
    public String loginURL;
    public String unsuccessfulLoginMessage;
    public String linksURL;


    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        excelReader = new ExcelReader("src/test/java/TestData.xlsx");

        homeURL = excelReader.getStringData("URL", 1, 0);
        profilePageURL = excelReader.getStringData("URL", 1, 1);
        loginURL = excelReader.getStringData("URL", 1, 2);
        unsuccessfulLoginMessage = excelReader.getStringData("Messages", 1, 0);
        linksURL = excelReader.getStringData("URL", 1, 3);
    }

    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollDownToTheBottom(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void waitForURL(String URL) {
        wait.until(ExpectedConditions.urlToBe(URL));
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean elementIsPresent(WebElement element) {
        boolean nonexistingElement = false;
        try {
            nonexistingElement = element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return nonexistingElement;
    }

    public void openNewTab() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open()");
    }

    @AfterClass
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
