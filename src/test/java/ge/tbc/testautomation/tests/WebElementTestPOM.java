package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.base.BaseTest;
import org.testng.annotations.Test;

public class WebElementTestPOM extends BaseTest {

    @Test(description = "Verify column positions and draggable attributes")
    public void figureTest() {
        webElementSteps
                .navigateToDragAndDropPage()
                .verifyColumnsPosition()
                .verifyColumnsDraggable()
                .verifyElementalSeleniumLink();
    }

    @Test(description = "Verify Elemental Selenium link")
    public void linkTest() {
        webElementSteps
                .navigateToDragAndDropPage()
                .verifyElementalSeleniumLink();
    }
}