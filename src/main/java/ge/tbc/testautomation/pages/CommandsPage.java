package ge.tbc.testautomation.pages;

import org.openqa.selenium.By;

public class CommandsPage {
    public By enableButton = By.cssSelector("#input-example > button");
    public By message = By.cssSelector("#message");
    public By inputField = By.cssSelector("#input-example > input[type=text]");
    public By mainHeading = By.cssSelector("#content > div.example > h4:nth-child(1)");
    public By description = By.cssSelector("#content > div.example > p");
}
