package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.By;
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

public class WaitsTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void waitForDisappearance(){
        driver.get(Constants.WAIT_FOR_DISAPPEARANCE_URL);

        WebElement enableBtn = driver.findElement(By.cssSelector("#input-example > button"));
        enableBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        WebElement disabledBtn  = driver.findElement(By.cssSelector("#input-example > button"));
        Assert.assertEquals(disabledBtn.getText(), Constants.DISABLE_BTN_TXT);
        WebElement inputField = driver.findElement(By.cssSelector("#input-example > input[type=text]"));
        inputField.sendKeys(Constants.INPUT);
        inputField.clear();

    }

    @Test
    public void waitForText(){
        driver.get(Constants.WAIT_FOR_TEXT_URL);

        WebElement startBtn = driver.findElement(By.id("startStopButton"));
        startBtn.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#progressBar > div[role='progressbar']"), "100%"));
        String resetBtn = driver.findElement(By.id("resetButton")).getText();
        Assert.assertEquals(resetBtn, Constants.RESET_BTN_TXT);
    }
}
