package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class LocatorsTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver =new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void unorderedListTest(){
        driver.get(Constants.SLIDER_URL);

        WebElement asideWithEffects = driver.findElement(
                By.xpath("//aside[h3[text()='Effects' and contains(@class, 'widget-title')]]")
        );

        List<WebElement> allLiElements = asideWithEffects.findElements(By.cssSelector("ul > li"));

        List<WebElement> filteredList = allLiElements.stream()
                .filter(e -> e.getText().toLowerCase().contains("o"))
                .collect(Collectors.toList());

        filteredList.parallelStream().forEach(li -> {
            WebElement anchor = li.findElement(By.cssSelector(":scope > a"));
            String href = anchor.getAttribute("href");

            if (!href.contains("animate")) {
                System.out.println(href);
            }
        });
    }

    @Test
    public void buttonsTes(){
        driver.get(Constants.ADD_REMOVE_URL);

        WebElement AddButton = driver.findElement(By.cssSelector("#content > div > button"));
        for (int i = 0; i < 3; i++) {
            AddButton.click();
        }

        WebElement deleteButton = driver.findElement(By.xpath("(//button[text()='Delete'])[last()]"));
        String lastClass = deleteButton.getAttribute("class");
        Assert.assertEquals(lastClass, "added-manually");

        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("button[onclick^='deleteElement']"));
        String lastButtonClass = deleteButtons.getLast().getAttribute("onclick");
        Assert.assertEquals(lastButtonClass, "deleteElement()");
    }

    @Test
    public void challengingDomTest(){
        driver.get(Constants.CHALLENGING_DOM_URL);
        List<WebElement> headers = driver.findElements(By.xpath("//table/thead/tr/th"));
        int ipsumIndex = -1;
        int loremIndex = -1;

        for (int i = 0; i < headers.size(); i++) {
            String headerText = headers.get(i).getText().trim();
            if (headerText.equals("Ipsum")) {
                ipsumIndex = i + 1;
            } else if (headerText.equals("Lorem")) {
                loremIndex = i + 1;
            }

            if (ipsumIndex != -1 && loremIndex != -1) {
                break;
            }
        }
        if (ipsumIndex == -1 || loremIndex == -1) {
            throw new RuntimeException("Could not find required columns");
        }

        String xpath = String.format("//table/tbody/tr[td[%d]='Apeirian9']/td[%d]", ipsumIndex, loremIndex);
        WebElement loremCell = driver.findElement(By.xpath(xpath));
        String value = loremCell.getText();
        System.out.println(" Lorem value of element, that has 'Apeirian9' as Ipsum value: " + value);

    }

}
