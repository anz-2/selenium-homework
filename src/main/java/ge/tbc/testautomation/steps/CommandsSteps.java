package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.base.BaseStep;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.CommandsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CommandsSteps extends BaseStep {
    private final CommandsPage commandsPage;

    public CommandsSteps(WebDriver driver) {
        super(driver);
        this.commandsPage = new CommandsPage();
    }

    public CommandsSteps navigateToDynamicControlsPage() {
        driver.get(Constants.NAVIGATE_TO_WEB);
        return this;
    }

    public CommandsSteps clickEnableButton() {
        driver.findElement(commandsPage.enableButton).click();
        return this;
    }

    public CommandsSteps waitForMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(commandsPage.message));
        WebElement message = driver.findElement(commandsPage.message);
        Assert.assertEquals(message.getText(), Constants.ENABLE_MESSAGE);
        return this;
    }

    public CommandsSteps verifyButtonText() {
        WebElement enableButton = driver.findElement(commandsPage.enableButton);
        Assert.assertEquals(enableButton.getText(), Constants.DISABLE_TEXT );
        return this;
    }

    public CommandsSteps verifyInputEnabledAndFill() {
        WebElement input = driver.findElement(commandsPage.inputField);
        Assert.assertTrue(input.isEnabled());
        input.sendKeys(Constants.INPUT_TEXT);
        input.clear();
        return this;
    }

    public CommandsSteps verifyHeading() {
        WebElement heading = driver.findElement(commandsPage.mainHeading);
        Assert.assertEquals(heading.getText(), Constants.MAIN_HEADING);
        return this;
    }

    public CommandsSteps verifyDescription() {
        WebElement description = driver.findElement(commandsPage.description);
        Assert.assertEquals(description.getText(), Constants.DESCRIPTION_TEXT);
        return this;
    }
}
