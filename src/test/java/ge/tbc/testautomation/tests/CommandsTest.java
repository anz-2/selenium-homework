package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class CommandsTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Constants.NAVIGATE_TO_WEB);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void buttonTest(){
        WebElement enableButton = driver.findElement(By.cssSelector("#input-example > button"));
        enableButton.click();

        WebElement message = new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> driver.findElement(By.cssSelector("#message")));
        Assert.assertEquals(message.getText(), Constants.ENABLE_MESSAGE);
        Assert.assertEquals(enableButton.getText(), Constants.DISABLE_TEXT );
        WebElement input = driver.findElement(By.cssSelector("#input-example > input[type=text]"));
        Assert.assertTrue(input.isEnabled());
        input.sendKeys(Constants.INPUT_TEXT);
        input.clear();
    }

    @Test
    public void labelsTest(){
        WebElement heading = driver.findElement(By.cssSelector("#content > div.example > h4:nth-child(1)"));
        Assert.assertEquals(heading.getText(), Constants.MAIN_HEADING);

        WebElement description = driver.findElement(By.cssSelector("#content > div.example > p"));
        Assert.assertEquals(description.getText(), Constants.DESCRIPTION_TEXT);
    }

}
