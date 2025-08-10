package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.base.BaseTest;
import org.testng.annotations.Test;

public class CookiesTestPOM extends BaseTest {

    @Test(description = "Verify cookie filtering")
    public void filterCookies() {
        cookiesSteps
                .navigateToFilterCookiesUrl()
                .verifyMatchingCookies();
    }

    @Test(description = "Verify cookie injection and deletion")
    public void injectCookie() {
        cookiesSteps
                .navigateToInjectCookiesUrl()
                .injectAndVerifyCookies();
    }
}
