package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CookiesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void filterCookies() {
        driver.get(Constants.FILTER_COOKIES_URL);

        Set<Cookie> cookies = driver.manage().getCookies();
        List<Cookie> matchingCookies = cookies.stream().filter(cookie -> cookie.getName()
                .contains(Constants.COOKIE_NAME) && cookie.getValue().contains(Constants.COOKIE_VALUE)).toList();

        Assert.assertFalse(matchingCookies.isEmpty());
    }

    @Test
    public void injectCookie() {
        driver.get(Constants.INJECT_COOKIE_URL);

        List<Cookie> myCookies = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Cookie cookie = new Cookie("cookie" + i, "value" + i);
            driver.manage().addCookie(cookie);
            myCookies.add(cookie);
        }

        System.out.println("added cookies:");
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
    }



}
