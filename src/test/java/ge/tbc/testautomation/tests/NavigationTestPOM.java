package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.base.BaseTest;
import org.testng.annotations.Test;

public class NavigationTestPOM extends BaseTest {

    @Test(description = "Verify navigation to services and back")
    public void goToServicesAndBackTest() {
        navigationSteps
                .navigateToBaseUrl()
                .clickServiceLink()
                .verifyServicesUrl()
                .navigateBack()
                .verifyBaseUrl();
    }
}
