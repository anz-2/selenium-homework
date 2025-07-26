package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ExceptionsTest {

    WebDriver driver;


    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    //NoSuchElementException
    //ამ ელემენტის ID "SrchBoX" არასწორია. ელემენტი არ არსებობს, ამიტომ მოხდება NoSuchElementException
    @Test
    public void noSuchElementExceptionTest() {
        driver.get(Constants.EXCEPTIONS_URL);
        try {
            WebElement searchBox = driver.findElement(By.id("SrchBoX"));
            Assert.assertTrue(searchBox.isDisplayed());
        } catch (NoSuchElementException e) {
            System.out.println("no such element " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    //NoSuchFrameException
    //ვცდილობთ შევიდეთ frame-ში რომელიც არ არსებობს, რაც გამოიწვევს NoSuchFrameException
    @Test
    public void noSuchFrameExceptionTest() {
        driver.get(Constants.EXCEPTIONS_URL);
        try {
            driver.switchTo().frame("Frame1234");
            Assert.assertTrue(true);
        } catch (NoSuchFrameException e) {
            System.out.println("no such frame " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }



    //NoAlertPresentException
    //alert() არ გამოძახებულა და ამ დროს კეთდება switchTo().alert() რაც გამოიწვევს NoAlertPresentException
    @Test
    public void noAlertPresentExceptionTest() {
        driver.get(Constants.EXCEPTIONS_URL);
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            Assert.assertTrue(true);
        } catch (NoAlertPresentException e) {
            System.out.println("no alert " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    //InvalidSelectorException
    // xpath-ის სელექტორში გატოვებულია დახურვის ფრჩხილი რაც იწვევს InvalidSelectorException"]"
    @Test
    public void invalidSelectorExceptionTest() {
        driver.get(Constants.EXCEPTIONS_URL);
        try {
            WebElement element = driver.findElement(By.xpath("//input[@id='searchInput'"));
            Assert.assertTrue(element.isDisplayed());
        } catch (InvalidSelectorException e) {
            System.out.println("invalid selector " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }


    //ElementNotInteractableException
    //ვცდილობთ ტექსტის შეყვანას <style> ელემენტში sendKeys() მეთოდის გამოყენებით
    //თუმცა <style>-ში ტექსტის შეყვანა შეუძლებელია,ეს ელემენტი გამოიყენება მხოლოდ CSS კოდის განსასაზღვრად.
    //ამიტომ ვიღებთ ElementNotInteractableException
    @Test
    public void elementNotInteractableExceptionTest() {
        driver.get(Constants.EXCEPTIONS_URL);
        try {
            WebElement hidden = driver.findElement(By.tagName("style"));
            hidden.sendKeys("test");
        } catch (ElementNotInteractableException e) {
            System.out.println("element not interactable " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    //StaleElementReferenceException
    //ელემენტი ინახება მეხსიერებაში მაგრამ გვერდის განახლების შემდეგ, ეს ელემენტი, დომის ახალ ვერსიაში აღარ შეესაბამება ძველ რეფერენსს.
    //რაც იწვევს StaleElementReferenceException-ს.
    @Test
    public void staleElementReferenceExceptionTest() {
        driver.get(Constants.EXCEPTIONS_URL);
        try {
            WebElement element = driver.findElement(By.tagName("h1"));
            driver.navigate().refresh();
            Assert.assertTrue(element.isDisplayed());
        } catch (StaleElementReferenceException e) {
            System.out.println("stale element reference " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    //UnhandledAlertException
    //alert ჩნდება, მაგრამ არ ვხურავთ მას, ამიტომ FindElement გამოიწვევს UnhandledAlertException
    @Test
    public void unhandledAlertExceptionTest() {
        driver.get(Constants.EXCEPTIONS_URL);
        try {
            ((JavascriptExecutor) driver).executeScript("alert('Test Alert');");
            WebElement header = driver.findElement(By.tagName("h1"));
            Assert.assertTrue(header.isDisplayed());
        } catch (UnhandledAlertException e) {
            System.out.println("Unhandled alert caught: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }


    //TimeoutException
    //ვუწერთ გვერდს რომ ჩაიტვირთოს 1 მილიწამში, რაც შეუძლებელია და ხდება TimeoutException
    @Test
    public void timeoutExceptionTest() {
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(1));
            driver.get(Constants.EXCEPTIONS_URL);
            Assert.assertTrue(driver.getTitle().contains("Wikipedia"));
        } catch (TimeoutException e) {
            System.out.println("Timeout exception caught: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }
}
