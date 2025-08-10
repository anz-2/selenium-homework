package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.base.BaseStep;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.JiraPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class JiraSteps extends BaseStep {
    private final JiraPage jiraPage;

    public JiraSteps(WebDriver driver) {
        super(driver);
        this.jiraPage = new JiraPage();
    }

    public JiraSteps navigateToW3Url() {
        driver.get(Constants.W3_URL);
        return this;
    }

    public JiraSteps searchHtmlTutorial() {
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(jiraPage.searchInput));
        searchInput.click();
        searchInput.sendKeys("HTML Tutorial");
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public JiraSteps verifyHtmlTutorialTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(jiraPage.title));
        String titleText = title.getText();
        Assert.assertTrue(titleText.contains("HTML Tutorial"));
        return this;
    }

    public JiraSteps clickJavaButton() {
        driver.findElement(jiraPage.javaButton).click();
        return this;
    }

    public JiraSteps clickHomeButton() {
        driver.findElement(jiraPage.homeButton).click();
        return this;
    }

    public JiraSteps verifyW3Url() {
        Assert.assertEquals(driver.getCurrentUrl(), Constants.W3_URL);
        return this;
    }

    public JiraSteps scrollToPythonTitle() {
        WebElement pythonTitle = driver.findElement(jiraPage.pythonTitle);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pythonTitle);
        wait.until(ExpectedConditions.visibilityOf(pythonTitle));
        Assert.assertTrue(pythonTitle.isDisplayed());
        return this;
    }

    public JiraSteps clickCssButton() {
        driver.findElement(jiraPage.cssButton).click();
        return this;
    }

    public JiraSteps verifyCssUrlAndTitle() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/css/"));
        WebElement cssTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(jiraPage.title));
        Assert.assertEquals(cssTitle.getText(), "CSS Tutorial");
        return this;
    }

    public JiraSteps measureHomePageLoadTime() {
        long start = System.currentTimeMillis();
        driver.get(Constants.W3_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(jiraPage.body));
        long end = System.currentTimeMillis();
        long duration = end - start;
        Assert.assertTrue(duration < 3000);
        return this;
    }
}
