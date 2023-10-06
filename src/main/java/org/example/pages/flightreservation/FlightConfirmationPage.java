package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends AbstractPage {
    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);
    //@FindBy(css = "flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    @FindBy(xpath = "//div[normalize-space()='Flight Confirmation #']")
    private WebElement flightConfirmationElement;
    @FindBy(xpath = "(//form[@class='row g-3']//div[@class='col'])[5]")
    private WebElement totalPrice;
    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(flightConfirmationElement));
        return flightConfirmationElement.isDisplayed();
    }
    public String getPrice(){
        String confirmation = flightConfirmationElement.getText();
        String price = totalPrice.getText();
        log.info("Flight confirmation# : {}", confirmation);
        log.info("Total price : {}", price);
        return price;
    }
}
