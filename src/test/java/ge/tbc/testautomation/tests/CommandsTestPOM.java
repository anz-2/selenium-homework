package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.base.BaseTest;
import org.testng.annotations.Test;

public class CommandsTestPOM extends BaseTest {

    @Test(description = "Verify button functionality")
    public void buttonTest() {
        commandsSteps
                .navigateToDynamicControlsPage()
                .clickEnableButton()
                .waitForMessage()
                .verifyButtonText()
                .verifyInputEnabledAndFill();
    }

    @Test(description = "Verify labels")
    public void labelsTest() {
        commandsSteps
                .navigateToDynamicControlsPage()
                .verifyHeading()
                .verifyDescription();
    }
}
