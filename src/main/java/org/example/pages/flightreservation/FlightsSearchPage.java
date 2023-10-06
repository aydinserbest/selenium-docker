package org.example.pages.flightreservation;

import org.example.pages.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightsSearchPage extends AbstractPage {
    @FindBy(id = "passengers")
    private WebElement passengerSelect;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;
    public FlightsSearchPage(WebDriver driver) {
        super(driver);
    }
    /*
    ustte super ile gittigimiz parent constructor'i icindeki Pagefactory.... ile
   burda @Findby ile buldugumuz  elementleri initialize ediyoruz
     */

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(passengerSelect));  //bu sayfadan herhangi bir element seciyoruz
        return passengerSelect.isDisplayed();
    }
    /*
    sayfa ile etkilesime gecebilmek icin,
    sayfanin tam yuklenmesi lazim,
    bu ustteki metotla onu check ediyoruz
    it provides confirmation that page is loaded successfully
     */

    public void setPassengerSelect(String numberOfPassengers){
        Select passengers = new Select(passengerSelect);
        passengers.selectByValue(numberOfPassengers);
    }
    public void searchFlights(){
        //wait.until(ExpectedConditions.elementToBeClickable(searchFlightsButton)).click();
        //searchFlightsButton.click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", searchFlightsButton);
    }
}
