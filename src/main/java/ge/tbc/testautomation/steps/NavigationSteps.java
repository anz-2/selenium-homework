package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.base.BaseStep;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.NavigationPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;

public class NavigationSteps extends BaseStep {
    private final NavigationPage navigationPage;

    public NavigationSteps(WebDriver driver) {
        super(driver);
        this.navigationPage = new NavigationPage();
    }

    public NavigationSteps navigateToBaseUrl() {
        driver.get(Constants.BASA_URL);
        return this;
    }

    public NavigationSteps clickServiceLink() {
        driver.findElement(navigationPage.serviceLink).click();
        return this;
    }

    public NavigationSteps verifyServicesUrl() {
        wait.until(d -> Objects.equals(d.getCurrentUrl(), Constants.SERVICES_URL));
        Assert.assertEquals(driver.getCurrentUrl(), Constants.SERVICES_URL);
        return this;
    }

    public NavigationSteps navigateBack() {
        driver.navigate().back();
        return this;
    }

    public NavigationSteps verifyBaseUrl() {
        wait.until(d -> Objects.equals(d.getCurrentUrl(), Constants.BASA_URL));
        Assert.assertEquals(driver.getCurrentUrl(), Constants.BASA_URL);
        return this;
    }
}
