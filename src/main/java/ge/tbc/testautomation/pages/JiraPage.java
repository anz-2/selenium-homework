package ge.tbc.testautomation.pages;

import org.openqa.selenium.By;

public class JiraPage {
    public By searchInput = By.id("tnb-google-search-input");
    public By title = By.xpath("//div[@id='main']/h1");
    public By javaButton = By.xpath("//div[@class='topnavcontainer']//a[text()='JAVA']");
    public By homeButton = By.id("w3-logo");
    public By pythonTitle = By.xpath("//div[@class='w3-content']//h1[text()='Python']");
    public By cssButton = By.xpath("//div[@class='topnavcontainer']//a[text()='CSS']");
    public By body = By.cssSelector("body");
}
