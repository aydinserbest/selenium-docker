package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {
    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightsSearchButton;
    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firstNameElement;

    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }
    public void goToFlightsSearchButton(){
        goToFlightsSearchButton.click();
    }
    @Override
    public boolean isAt(){
        wait.until(ExpectedConditions.visibilityOf(goToFlightsSearchButton));
        return goToFlightsSearchButton.isDisplayed();
    }
    public String getFirstName(){
        return firstNameElement.getText();
    }
}
