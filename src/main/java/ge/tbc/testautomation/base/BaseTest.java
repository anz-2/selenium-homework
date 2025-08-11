package ge.tbc.testautomation.base;

import ge.tbc.testautomation.steps.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;
    protected CommandsSteps commandsSteps;
    protected WebElementSteps webElementSteps;
    protected WaitsSteps waitsSteps;
    protected NavigationSteps navigationSteps;
    protected JiraSteps jiraSteps;
    protected CookiesSteps cookiesSteps;



    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        commandsSteps = new CommandsSteps(driver);
        webElementSteps = new WebElementSteps(driver);
        waitsSteps = new WaitsSteps(driver);
        navigationSteps = new NavigationSteps(driver);
        jiraSteps = new JiraSteps(driver);
        cookiesSteps = new CookiesSteps(driver);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}