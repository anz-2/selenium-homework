package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class JiraTestCases {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }


    @Test(description = "საძიებო ველის ფუნქციონალის ტესტი[TA-T14]" )
    public void testSearchHTML() {
        driver.get(Constants.W3_URL);
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("tnb-google-search-input")));
        searchInput.click();
        searchInput.sendKeys("HTML Tutorial");
        searchInput.sendKeys(Keys.ENTER);

        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main']/h1")));
        String titleText = title.getText();
        Assert.assertTrue(titleText.contains("HTML Tutorial"));
    }


    @Test(description = "მთავარ გვერდზე დაბრუნება[TA-T6]")
    public void testHomeButton() {
        driver.get(Constants.W3_URL);

        WebElement javaBtn = driver.findElement(By.xpath("//div[@class='topnavcontainer']//a[text()='JAVA']"));
        javaBtn.click();

        WebElement homeBtn = driver.findElement(By.id("w3-logo"));
        homeBtn.click();
        Assert.assertEquals(driver.getCurrentUrl(), Constants.W3_URL);

    }

    @Test(description = "Python ელემენტის გამოჩენა Scroll-ით[TA-T13")
    public void testScrollToPythonText() {
        driver.get(Constants.W3_URL);

        WebElement pythonTitle = driver.findElement(By.xpath("//div[@class='w3-content']//h1[text()='Python']"));
        js.executeScript("arguments[0].scrollIntoView(true);", pythonTitle);
        wait.until(ExpectedConditions.visibilityOf(pythonTitle));
        Assert.assertTrue(pythonTitle.isDisplayed());
    }


    @Test(description = "ნავიგაცია CSS გაკვეთილებზე[TA-T12]")
    public void cssLinkNavigatorTest() {
        driver.get(Constants.W3_URL);

        WebElement cssBtn = driver.findElement(By.xpath("//div[@class='topnavcontainer']//a[text()='CSS']"));
        cssBtn.click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/css/"));

        WebElement cssTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main']/h1")));
        Assert.assertEquals(cssTitle.getText(), "CSS Tutorial");
    }

    @Test(description = "მთავარი გვერდის ჩატვირთვის დრო[TA-T8]")
    public void homePageLoadTimeTest() {
        long start = System.currentTimeMillis();
        driver.get(Constants.W3_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));

        long end = System.currentTimeMillis();
        long duration = end - start;
        Assert.assertTrue(duration < 3000);
    }

}
