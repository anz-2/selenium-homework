package ge.tbc.testautomation.tests;

import ge.tbcitacademy.data.Constants;
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
import java.util.Objects;

public class NavigationTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Constants.BASA_URL);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void goToServicesAndBackTest(){
        WebElement serviceLink = driver.findElement(By.cssSelector("#menu-item-218392 > a"));
        serviceLink.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> Objects.equals(d.getCurrentUrl(), Constants.SERVICES_URL));
        Assert.assertEquals(driver.getCurrentUrl(), Constants.SERVICES_URL);
        driver.navigate().back();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> Objects.equals(d.getCurrentUrl(), Constants.BASA_URL));
        Assert.assertEquals(driver.getCurrentUrl(), Constants.BASA_URL);


    }

}
