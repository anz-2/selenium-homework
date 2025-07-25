package ge.tbc.testautomation.tests;

import com.github.javafaker.Faker;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.util.HelperFunc;
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

public class FormsTest {
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
    public void customDropDownTest(){
        driver.get(Constants.CUSTOMER_FORM_URL);
        driver.findElement(By.xpath("/html/body/div[1]/header/nav/a[2]")).click();

        WebElement options = driver.findElement(By.cssSelector("#dd > ul"));
        Assert.assertFalse(options.isDisplayed());

        WebElement dropdownTable = driver.findElement(By.id("dd"));
        dropdownTable.click();

        wait.until(driver -> options.isDisplayed());
        Assert.assertTrue(dropdownTable.isDisplayed());
        HelperFunc.universalSelector(options, Constants.DROPDOWN_OPTION);
        String selectedText = dropdownTable.getText();
        Assert.assertTrue(selectedText.contains(Constants.DROPDOWN_OPTION));
    }

    @Test
    public void nativeDropDownTest() {
        driver.get(Constants.NATIVE_SELECT_URL);

        WebElement maleRadioBtn = driver.findElement(By.cssSelector("input[type='radio'][value='male']"));
        maleRadioBtn.click();

        WebElement selectModel = driver.findElement(By.cssSelector("select[name='model']"));
        HelperFunc.universalSelector(selectModel, Constants.MODEL_OPTION);
        String selectedText = selectModel.getText();
        Assert.assertTrue(selectedText.contains(Constants.MODEL_OPTION));


        Faker faker = new Faker();
        driver.findElement(By.cssSelector("input[value='First Name']")).sendKeys(faker.name().firstName());
        driver.findElement(By.cssSelector("input[value='Last Name']")).sendKeys(faker.name().lastName());
        driver.findElement(By.cssSelector("input[value='Address1']")).sendKeys(faker.address().streetAddress());
        driver.findElement(By.cssSelector("input[value='Address2']")).sendKeys(faker.address().secondaryAddress());
        driver.findElement(By.cssSelector("input[value='City']")).sendKeys(faker.address().city());
        driver.findElement(By.cssSelector("input[value='Contact1']")).sendKeys(faker.phoneNumber().cellPhone());
        driver.findElement(By.cssSelector("input[value='Contact2']")).sendKeys(faker.phoneNumber().cellPhone());

        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        checkbox.click();
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

}
