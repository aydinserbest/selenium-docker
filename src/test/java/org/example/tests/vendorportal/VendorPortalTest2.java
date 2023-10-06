package org.example.tests.vendorportal;

import org.example.pages.vendorportal.DashboardPage;
import org.example.pages.vendorportal.LoginPage;
import org.example.tests.AbstractTest;
import org.example.tests.AbstractTest2;
import org.example.tests.vendorportal.model.VendorPortalTestData;
import org.example.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest2 extends AbstractTest2 {
    /*
    !-- asagida 3 test var
 user-sam,   user-mike,  user-john isminde,
 normalde pom.xml den browser type 'i degistiriyoruz,
  ordaki type'a gore testimiz chrome ya da diger browser cesitlerinde calisiyor,
  3 testi ordaki type'a gore o browserda run ediyoruz,
  ama asagidaki yontemle her testi farkli browser'da run edebiliriz
 pom.xml dosyasinda   <systemPropertyVariables>
                        <browser>chrome</browser>
                        <selenium.grid.enabled>true</selenium.grid.enabled>
                    </systemPropertyVariables>
 kismindaki
  1-<browser>chrome</browser>  satirini kapatip
  2-verdor-portal2.xml file'inda yer alan  her 3 teste parametre ile farkli browser type'i ekledik,
  3-abstratctTest2 isimli base test icin kullandigimiz testi olusturduk,
  cunku orda metot iclerinde burdan parametre alicak sekilde degisiklik yaptik
  4-normalde eski standart yontemde vendorportaltest classini kullaniyoruz
  ve o class da abstracttest classini extend ediyordu,
  onu da 2 olarak degistirdim ve abstracttest2 classini extend ediyor ki
  ustteki uygulamayi gorebilelim

  bu 3 test arka arkaya run edilince,
  localhost:4444 de goruluyor ki, once firefox sonra chrome'da aciyor
  -->
     */
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath,VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());

        loginPage.login(testData.username(), testData.password());
    }
    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());

        //finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());
        //...
        //order history search
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultCount(),testData.searchResultsCount());

        //logout
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
}
