package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.base.BaseStep;
import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CookiesSteps extends BaseStep {

    public CookiesSteps(WebDriver driver) {
        super(driver);
    }

    public CookiesSteps navigateToFilterCookiesUrl() {
        driver.get(Constants.FILTER_COOKIES_URL);
        return this;
    }

    public CookiesSteps verifyMatchingCookies() {
        Set<Cookie> cookies = driver.manage().getCookies();
        List<Cookie> matchingCookies = cookies.stream()
                .filter(cookie -> cookie.getName().contains(Constants.COOKIE_NAME) && cookie.getValue().contains(Constants.COOKIE_VALUE))
                .toList();
        Assert.assertFalse(matchingCookies.isEmpty());
        return this;
    }

    public CookiesSteps navigateToInjectCookiesUrl() {
        driver.get(Constants.INJECT_COOKIE_URL);
        return this;
    }

    public CookiesSteps injectAndVerifyCookies() {
        List<Cookie> myCookies = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Cookie cookie = new Cookie("cookie" + i, "value" + i);
            driver.manage().addCookie(cookie);
            myCookies.add(cookie);
        }

        for (Cookie cookie : myCookies) {
            Cookie myCookieCount = driver.manage().getCookieNamed(cookie.getName());
            Assert.assertNotNull(myCookieCount);
            System.out.println(myCookieCount.getName() + " : " + myCookieCount.getValue());
        }
        Assert.assertEquals(myCookies.size(), 10);

        for (Cookie cookie : myCookies) {
            driver.manage().deleteCookieNamed(cookie.getName());
        }

        for (Cookie cookie : myCookies) {
            Cookie deletedCookies = driver.manage().getCookieNamed(cookie.getName());
            Assert.assertNull(deletedCookies);
        }
        return this;
    }
}