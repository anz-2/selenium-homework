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
@Test(groups = {"წარმატებული ავტორიზაცია და პროდუქტის დამატება კალათაში TA-T31"})
public class AutomatedScenarios {
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

    @Test(priority = 1)
    public void openWebsite(){
        driver.get(Constants.OPENCART_URL);
        WebElement pageTitle = driver.findElement(By.xpath("//img[@alt='Website for automation practice']"));
        Assert.assertTrue(pageTitle.isDisplayed());
    }

    @Test(priority = 2)
    public void LoginTest(){
        WebElement loginBtn = driver.findElement(By.xpath("//a[@href=\"/login\"]"));
        loginBtn.click();

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-email']")));
        emailInput.sendKeys(Constants.LOGIN_EMAIL);

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-password']")));
        passwordInput.sendKeys(Constants.LOGIN_PASSWORD);

        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='login-button']")));
        login.click();

        WebElement loggedIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[b[text()='test']]")));
        Assert.assertTrue(loggedIn.isDisplayed());
    }

    @Test(priority = 3)
    public void ProductSearchTest(){
        WebElement productsBtn = driver.findElement(By.xpath("//a[contains(text(), 'Products')]"));
        productsBtn.click();

        WebElement searchInput = driver.findElement(By.id("search_product"));
        searchInput.sendKeys(Constants.SEARCH_INPUT);

        WebElement searchBtn = driver.findElement(By.id("submit_search"));
        searchBtn.click();

        WebElement productList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-product-id='30']")));
        Assert.assertTrue(productList.isDisplayed());
    }

    @Test(priority = 4)
    public void AddToCartTest(){
        WebElement selectedProduct = driver.findElement(By.xpath("//p[text()='Premium Polo T-Shirts']/following::a[contains(text(), 'View Product')]"));
        selectedProduct.click();

        WebElement addToCartBtn = driver.findElement(By.xpath("//button[@class='btn btn-default cart']"));
        addToCartBtn.click();

        WebElement successWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']")));
        Assert.assertTrue(successWindow.isDisplayed());
    }

    @Test(priority = 5)
    public void CartCheck(){
        WebElement viewCartBtn = driver.findElement(By.xpath("//div[@class='modal-content']//a[u[text()='View Cart']]"));
        viewCartBtn.click();

        WebElement addedProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product-30")));
        Assert.assertTrue(addedProduct.isDisplayed());
    }
}

