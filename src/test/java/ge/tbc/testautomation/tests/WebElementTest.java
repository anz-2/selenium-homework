package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class WebElementTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Constants.DRAG_DROP_URL);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void figureTest(){
        WebElement colA = driver.findElement(By.id("column-a"));
        WebElement colB = driver.findElement(By.id("column-b"));

        int A = colA.getLocation().getY();
        int B = colB.getLocation().getY();
        Assert.assertEquals(A,B);
        Assert.assertEquals(colA.getAttribute("draggable"),"true");
        Assert.assertEquals(colB.getAttribute("draggable"),"true");

    }

    @Test
    public void linkTest(){
        WebElement link = driver.findElement(By.cssSelector("#page-footer > div > div > a"));
        Assert.assertEquals(link.getAttribute( "href"), Constants.ELEMENTAL_SELENIUM_LINK);
    }
}
