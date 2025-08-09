package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.base.BaseTest;
import org.testng.annotations.Test;

public class WaitsTestPOM extends BaseTest {
    @Test(description = "Verify wait for disappearance functionality")
    public void waitForDisappearance() {
        waitsSteps
                .navigateToWaitForDisappearancePage()
                .clickEnableButton()
                .waitForMessageVisibility()
                .verifyDisabledButtonText()
                .fillAndClearInputField();
    }

    @Test(description = "Verify wait for text functionality")
    public void waitForText() {
        waitsSteps
                .navigateToWaitForTextPage()
                .clickStartButton()
                .waitForProgressBarCompletion()
                .verifyResetButtonText();
    }
}
