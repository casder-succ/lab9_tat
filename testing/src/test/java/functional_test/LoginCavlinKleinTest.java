package functional_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginCavlinKleinTest {
    private WebDriver driver;

//    @BeforeMethod(alwaysRun = true)
//    void getDriver() {
//        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
//
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--start-maximized");
//
//        driver = new ChromeDriver(chromeOptions);
//    }

    @Test(description = "Test empty wishlist")
    void testHomePage() {

        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        firefoxBinary.addCommandLineOptions("--no-sandbox");
        System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        driver = new FirefoxDriver(firefoxOptions);

        String TEST_ACCOUNT_EMAIL = "casderiopus1@gmail.com";
        String TEST_ACCOUNT_PASSWORD = "casdercasder";

        driver.get("https://www.calvinklein.co.uk/men");

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='ck-Button ck-Button__primary cookie-notice__agree-button']")))
                .click();

        (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(By.className("header-account__sign-in")))
                .click();

        (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(By.id("logonId")))
                .sendKeys(TEST_ACCOUNT_EMAIL);
        (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(By.id("logonPassword")))
                .sendKeys(TEST_ACCOUNT_PASSWORD);
        (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='ck-Button ck-Button__primary ck-Button--with-icon ck-Button--full-width login-popup__secondary-action-send']")))
                .click();

        driver.navigate().to("https://www.calvinklein.co.uk/wishlist");

        WebElement emptyViewWrapper = (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Looks like your wishlist is empty']")));

        Assert.assertTrue(emptyViewWrapper.isDisplayed());
        driver.quit();
        driver = null;
    }

//    @AfterMethod(alwaysRun = true)
//    void resetDriver() {
//        driver.quit();
//        driver = null;
//    }
}
