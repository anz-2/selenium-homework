package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.base.BaseStep;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.WaitsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class WaitsSteps extends BaseStep {
    private final WaitsPage waitsPage;

    public WaitsSteps(WebDriver driver) {
        super(driver);
        this.waitsPage = new WaitsPage();
    }

    public WaitsSteps navigateToWaitForDisappearancePage() {
        driver.get(Constants.WAIT_FOR_DISAPPEARANCE_URL);
        return this;
    }

    public WaitsSteps navigateToWaitForTextPage() {
        driver.get(Constants.WAIT_FOR_TEXT_URL);
        return this;
    }

    public WaitsSteps clickEnableButton() {
        driver.findElement(waitsPage.enableButton).click();
        return this;
    }

    public WaitsSteps waitForMessageVisibility() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(waitsPage.message));
        return this;
    }

    public WaitsSteps verifyDisabledButtonText() {
        WebElement disabledBtn = driver.findElement(waitsPage.enableButton);
        Assert.assertEquals(disabledBtn.getText(), Constants.DISABLE_BTN_TXT);
        return this;
    }

    public WaitsSteps fillAndClearInputField() {
        WebElement inputField = driver.findElement(waitsPage.inputField);
        inputField.sendKeys(Constants.INPUT);
        inputField.clear();
        return this;
    }

    public WaitsSteps clickStartButton() {
        driver.findElement(waitsPage.startButton).click();
        return this;
    }

    public WaitsSteps waitForProgressBarCompletion() {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(waitsPage.progressBar, "100%"));
        return this;
    }

    public WaitsSteps verifyResetButtonText() {
        WebElement resetBtn = driver.findElement(waitsPage.resetButton);
        Assert.assertEquals(resetBtn, Constants.RESET_BTN_TXT);
        return this;
    }
}
