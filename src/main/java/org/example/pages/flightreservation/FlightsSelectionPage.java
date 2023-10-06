package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightsSelectionPage extends AbstractPage {

    // 1- @Findby ile we have identified elements

    @FindBy(name = "departure-flight")
    private List<WebElement> departureFlightsOptions;

    @FindBy(name = "arrival-flight")
    private List<WebElement> arrivalFlightsOptions;

    @FindBy(id = "confirm-flights")
    private WebElement confirmFlightsButton;

    // 2- we initialized elements above in the constructor by going parent's constructor
    //there PageFactory.initElements() is
    public FlightsSelectionPage(WebDriver driver){
        super(driver);
    }
    //3- we are checking whether the page is loaded successfully
    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(confirmFlightsButton));
        return confirmFlightsButton.isDisplayed();
    }
    public void selectFlights(){
        int random = ThreadLocalRandom.current().nextInt(0,departureFlightsOptions.size());
        departureFlightsOptions.get(random).click();
        arrivalFlightsOptions.get(random).click();
    }
    public void clickOnConfirmFlights(){
        //confirmFlightsButton.click();
        //wait.until(ExpectedConditions.visibilityOf(confirmFlightsButton));
//        JavascriptExecutor executor = (JavascriptExecutor)driver;
//        executor.executeScript("arguments[0].click();", confirmFlightsButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmFlightsButton);
        wait.until(ExpectedConditions.visibilityOf(confirmFlightsButton)).click();

    }
}
