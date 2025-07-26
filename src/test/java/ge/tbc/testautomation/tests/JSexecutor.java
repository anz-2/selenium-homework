package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.*;


public class JSexecutor {
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

    @Test
    public void deleteTest(){
        driver.get(Constants.DELETE_TEST_URL);
        WebElement practiceMagic = driver.findElement(By.xpath("//li[contains(text(), 'Practice magic')]"));

        Actions actions = new Actions(driver);
        actions.moveToElement(practiceMagic).perform();

        WebElement deleteBtn = driver.findElement(By.xpath("//li[.//text()[normalize-space()='Practice magic']]//i[contains(@class, 'fa-trash')]"));
        js.executeScript("arguments[0].click();", deleteBtn);

        boolean isDeleted = wait.until(ExpectedConditions.invisibilityOf(practiceMagic));
        Assert.assertTrue(isDeleted);
    }


    @Test
    public void anotherScrollTest(){
        driver.get(Constants.ANOTHER_SCROLL_TEST_URL);

        WebElement entriesBox = driver.findElement(By.id("zone2"));
        js.executeScript("arguments[0].scrollIntoView(true);", entriesBox);
        String actualText = (String) js.executeScript("return arguments[0].innerText;", entriesBox);
        Assert.assertNotNull(actualText);
        Assert.assertTrue(actualText.contains(Constants.ENTRIES));
    }

    @Test
    public void scrollTest(){
        driver.get(Constants.SCROLL_TEST_URL);

        wait.until(d -> Objects.equals(js.executeScript("return document.readyState"), "complete"));
        List<WebElement> codeSections = driver.findElements(By.xpath("//div[pre]"));
        Map<String, String> codeMap = new LinkedHashMap<>();

        for (WebElement codeBlock : codeSections) {
            js.executeScript("arguments[0].scrollIntoView(true);", codeBlock);
            wait.until(ExpectedConditions.visibilityOf(codeBlock));
            List<WebElement> h3 = codeBlock.findElements(By.xpath("preceding::h3[1]"));

            if (!h3.isEmpty()) {
                String sectionName = h3.getFirst().getText();
                String codeText = codeBlock.getText();
                codeMap.put(sectionName, codeText);
            }
        }
        Set<String> blocks = new HashSet<>(codeMap.values());
        Assert.assertEquals(codeMap.size(), blocks.size());

        WebElement popularTutorials = driver.findElement(By.xpath("//span[text()='Popular Tutorials']"));
        js.executeScript("arguments[0].scrollIntoView();", popularTutorials);
        wait.until(ExpectedConditions.visibilityOf(popularTutorials));

        Map<String, String> tutorialLinks = new LinkedHashMap<>();
        List<WebElement> tutorials = driver.findElements(By.xpath("//span[contains(text(),'Popular Tutorials')]/following::ul//a"));
        for (WebElement tutorial : tutorials) {
            wait.until(ExpectedConditions.visibilityOf(tutorial));
            String label = tutorial.getText().trim();
            String href = tutorial.getAttribute("href");
            tutorialLinks.put(label, href);
        }
        for (Map.Entry<String, String> entry : tutorialLinks.entrySet()) {
            Assert.assertNotNull(entry.getKey());
            Assert.assertFalse(entry.getKey().trim().isEmpty());

            Assert.assertNotNull(entry.getValue());
            Assert.assertFalse(entry.getValue().trim().isEmpty());
        }
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

    }


}
