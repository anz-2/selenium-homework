package ge.tbc.testautomation.data.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HelperFunc {
    public static <T> void universalSelector(T element, String visibleText){
        if (element instanceof Select) {
            Select select = (Select) element;
            select.selectByVisibleText(visibleText);
        }else if(element instanceof WebElement) {
            WebElement webElement = (WebElement) element;
            List<WebElement> options = webElement.findElements(By.cssSelector("*"));

            boolean found = false;
            for (WebElement option : options) {
                if (option.getText().equals(visibleText)) {
                    option.click();
                    found = true;
                    break;
                }
            }
            if (!found) throw new IllegalStateException(visibleText + "element is not found");

        } else {
            throw new IllegalStateException("wrong element type: " + element.getClass().getSimpleName());
        }

        System.out.println("I AM A CHANGE MEANWHILE");
    }
}
