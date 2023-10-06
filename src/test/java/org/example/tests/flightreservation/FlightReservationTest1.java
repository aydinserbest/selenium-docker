package org.example.tests.flightreservation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.flightreservation.*;
import org.example.tests.AbstractTest;
import org.example.util.Config;
import org.example.util.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest1 extends AbstractTest {
    private String expectedPrice;
    private String numberOfPassengers;

    @BeforeTest
    @Parameters({"numberOfPassengers", "expectedPrice"})
    public void setParameters(String numberOfPassengers, String expectedPrice ){
        this.numberOfPassengers = numberOfPassengers;
        this.expectedPrice = expectedPrice;
    }
    @Test
    public void userRegistration(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails("selenium", "java");
        registrationPage.enterUserCredentials("selenium", "test");
        registrationPage.enterUserAddress("selenium", "java", "test");
        registrationPage.register();
    }
    @Test(dependsOnMethods = "userRegistration")
    public void registrationConfirmation(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        // we want to ensure that we are on that page before we take any action
        registrationConfirmationPage.goToFlightsSearchButton();
    }
    @Test(dependsOnMethods = "registrationConfirmation")
    public void flightSearchTest(){
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt());
        flightsSearchPage.setPassengerSelect(numberOfPassengers);
        flightsSearchPage.searchFlights();
    }
    @Test(dependsOnMethods = "flightSearchTest")
    public void flightsSelectionTest(){
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.clickOnConfirmFlights();
    }
    @Test(dependsOnMethods = "flightsSelectionTest")
    public  void flightReservationConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage  = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(),expectedPrice);
    }

}
