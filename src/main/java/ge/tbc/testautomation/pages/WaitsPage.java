package ge.tbc.testautomation.pages;

import org.openqa.selenium.By;

public class WaitsPage {
    public By enableButton = By.cssSelector("#input-example > button");
    public By message = By.id("message");
    public By inputField = By.cssSelector("#input-example > input[type=text]");
    public By startButton = By.id("startStopButton");
    public By progressBar = By.cssSelector("#progressBar > div[role='progressbar']");
    public By resetButton = By.id("resetButton");
}
