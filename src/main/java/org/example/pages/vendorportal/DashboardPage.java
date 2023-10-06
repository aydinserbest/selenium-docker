package org.example.pages.vendorportal;

import org.example.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {
    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);
    @FindBy(id = "monthly-earning")
    private WebElement monthlyEarningElement;
    @FindBy(id = "annual-earning")
    private WebElement annualEarningElement;
    @FindBy(id = "profit-margin")
    private WebElement profitmarginElement;
    @FindBy(id = "available-inventory")
    private WebElement availableInventoryElement;
    @FindBy(css = "#dataTable_filter input")
    private WebElement searchInput;
    @FindBy(id = "dataTable_info")
    private WebElement searchResultsCount;
    @FindBy(css = "img.img-profile")
    private WebElement userProfilePictureElement;
    @FindBy(linkText = "Logout")
    private WebElement logoutLink;
    @FindBy(css = "#logoutModal a")
    private WebElement modalLogoutButton;
    public  DashboardPage(WebDriver driver){
        super(driver);
    }
    @Override
    public boolean isAt(){
        wait.until(ExpectedConditions.visibilityOf(monthlyEarningElement));
        return monthlyEarningElement.isDisplayed();
    }
    public String getMonthlyEarning(){
        return monthlyEarningElement.getText();
    }
    public String getAnnualEarning(){
        return annualEarningElement.getText();
    }
    public String getProfitMargin(){
        return profitmarginElement.getText();
    }
    public String getAvailableInventory(){
        return availableInventoryElement.getText();
    }
    public void searchOrderHistoryBy(String input){
        searchInput.sendKeys(input);
    }
    public int getSearchResultCount(){
        String resultText = searchResultsCount.getText();
        String[] array = resultText.split(" ");
        int count = Integer.parseInt(array[5]);
        log.info("Results count: {}", count);
        return count;
    }
    public void logout(){
        userProfilePictureElement.click();
        wait.until(ExpectedConditions.visibilityOf(logoutLink)).click();

        //logoutLink.click();
        wait.until(ExpectedConditions.visibilityOf(modalLogoutButton)).click();

        //modalLogoutButton.click();
    }
}
