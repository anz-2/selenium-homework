package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

@Test(groups = {"ვალიდური ავტორიზაცია, მაგრამ პროდუქტი არ იძებნება TA-T34"})
public class AutomatedScenarios1 {

        WebDriver driver;
        WebDriverWait wait;
        JavascriptExecutor js;

        @BeforeClass
        public void setup() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            js = (JavascriptExecutor) driver;
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        @AfterClass
        public void tearDown() {
            driver.quit();
        }

        @Test(priority = 1)
        public void openWebsite() {
            driver.get(Constants.OPENCART_URL);
            WebElement pageTitle = driver.findElement(By.xpath("//img[@alt='Website for automation practice']"));
            Assert.assertTrue(pageTitle.isDisplayed());
        }

        @Test(priority = 2)
        public void LoginTest() {
            WebElement loginBtn = driver.findElement(By.xpath("//a[@href=\"/login\"]"));
            loginBtn.click();

            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-email']")));
            emailInput.sendKeys(Constants.INVALID_LOGIN_EMAIL);

            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-password']")));
            passwordInput.sendKeys(Constants.INVALID_LOGIN_PASSWORD );

            WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='login-button']")));
            login.click();

            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"Your email or password is incorrect!\"]")));
            Assert.assertTrue(errorMsg.isDisplayed());
        }


}
