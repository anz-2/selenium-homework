package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.base.BaseStep;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.WebElementPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class WebElementSteps extends BaseStep {
    private final WebElementPage webElementPage;

    public WebElementSteps(WebDriver driver) {
        super(driver);
        this.webElementPage = new WebElementPage();
    }

    public WebElementSteps navigateToDragAndDropPage() {
        driver.get(Constants.DRAG_DROP_URL);
        return this;
    }

    public WebElementSteps verifyColumnsPosition() {
        WebElement colA = driver.findElement(webElementPage.columnA);
        WebElement colB = driver.findElement(webElementPage.columnB);
        int A = colA.getLocation().getY();
        int B = colB.getLocation().getY();
        Assert.assertEquals(A,B);
        return this;
    }

    public WebElementSteps verifyColumnsDraggable() {
        WebElement colA = driver.findElement(webElementPage.columnA);
        WebElement colB = driver.findElement(webElementPage.columnB);
        Assert.assertEquals(colB.getAttribute("draggable"),"true");
        Assert.assertEquals(colA.getAttribute("draggable"),"true");
        return this;
    }

    public WebElementSteps verifyElementalSeleniumLink() {
        WebElement link = driver.findElement(webElementPage.elementalSeleniumLink);
        Assert.assertEquals(link.getAttribute( "href"), Constants.ELEMENTAL_SELENIUM_LINK);
        return this;
    }
}