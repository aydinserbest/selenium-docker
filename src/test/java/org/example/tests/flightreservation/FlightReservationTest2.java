package org.example.tests.flightreservation;

import org.example.pages.flightreservation.*;
import org.example.tests.AbstractTest;
import org.example.tests.flightreservation.model.FlightReservationTestData;
import org.example.util.Config;
import org.example.util.Constants;
import org.example.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest2 extends AbstractTest {
    private FlightReservationTestData testData;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath){
       this.testData= JsonUtil.getTestData(testDataPath,FlightReservationTestData.class);
    }
    @Test
    public void userRegistration(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterUserAddress(testData.street(), testData.city(), testData.zip());
        registrationPage.register();
    }
    @Test(dependsOnMethods = "userRegistration")
    public void registrationConfirmation(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
        // we want to ensure that we are on that page before we take any action
        registrationConfirmationPage.goToFlightsSearchButton();
    }
    @Test(dependsOnMethods = "registrationConfirmation")
    public void flightSearchTest(){
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt());
        flightsSearchPage.setPassengerSelect(testData.passengersCount());
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
        Assert.assertEquals(flightConfirmationPage.getPrice(),testData.expectedPrice());
    }

}
