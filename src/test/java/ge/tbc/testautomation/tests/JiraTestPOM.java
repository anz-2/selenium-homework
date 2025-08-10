package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.base.BaseTest;
import org.testng.annotations.Test;

public class JiraTestPOM extends BaseTest {

    @Test(description = "საძიებო ველის ფუნქციონალის ტესტი[TA-T14]" )
    public void testSearchHTML() {
        jiraSteps
                .navigateToW3Url()
                .searchHtmlTutorial()
                .verifyHtmlTutorialTitle();
    }

    @Test(description = "მთავარ გვერდზე დაბრუნება[TA-T6]")
    public void testHomeButton() {
        jiraSteps
                .navigateToW3Url()
                .clickJavaButton()
                .clickHomeButton()
                .verifyW3Url();
    }

    @Test(description = "Python ელემენტის გამოჩენა Scroll-ით[TA-T13")
    public void testScrollToPythonText() {
        jiraSteps
                .navigateToW3Url()
                .scrollToPythonTitle();
    }

    @Test(description = "ნავიგაცია CSS გაკვეთილებზე[TA-T12]")
    public void cssLinkNavigatorTest() {
        jiraSteps
                .navigateToW3Url()
                .clickCssButton()
                .verifyCssUrlAndTitle();
    }

    @Test(description = "მთავარი გვერდის ჩატვირთვის დრო[TA-T8]")
    public void homePageLoadTimeTest() {
        jiraSteps
                .measureHomePageLoadTime();
    }
}
