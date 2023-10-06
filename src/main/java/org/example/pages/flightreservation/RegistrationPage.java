package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends AbstractPage {
    @FindBy(id = "firstName")
    private WebElement firstNameInput;
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(name = "street")
    private WebElement streetInput;
    @FindBy(id = "register-btn")
    private WebElement registerButton;
    @FindBy(name = "city")
    private WebElement cityInput;
    @FindBy(name = "zip")
    private WebElement zipInput;

    public RegistrationPage(WebDriver driver){
       super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        return firstNameInput.isDisplayed();
    }

    public void goTo(String url){
        driver.get(url);
    }
    public void enterUserDetails(String firstName, String lastName){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
    }
    public void enterUserAddress(String street, String city, String zip){
        streetInput.sendKeys(street);
        cityInput.sendKeys(city);
        zipInput.sendKeys(zip);
    }
    public void enterUserCredentials(String email, String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
    }
    public void register(){
        registerButton.click();
    }

}
