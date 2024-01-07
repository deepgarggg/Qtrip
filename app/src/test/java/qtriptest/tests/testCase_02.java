package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class testCase_02 {
    RemoteWebDriver driver;
    @BeforeTest(alwaysRun = true)
    public void  createDriver() throws MalformedURLException{
        driver = DriverSingleton.getWebDriverInstance();
    }
    
    @Test(dataProvider = "data-provider",dataProviderClass = DP.class , enabled = true, priority = 2,groups = {"Search and Filter flow"})
    public void TestCase02(String CityName,String Category_Filter,String DurationFilter,String ExpectedFilteredResults,String ExpectedUnFilteredResults) throws InterruptedException{
        HomePage homePage = new HomePage(driver);
        Boolean status;
        System.out.println("Start TestCase02 verify Filters working");
        homePage.navigateToHomePage();
        status =homePage.SearchCity(CityName);
        Assertion assertion = new Assertion();
        assertion.assertTrue(status,"Searching not Working");
        AdventurePage adventurePage = new AdventurePage(driver);
        
        status = adventurePage.countAll(ExpectedUnFilteredResults);
        assertion.assertTrue(status,"somthing wrong with unFilter result");
        System.out.println("yes all city package is available");
        status = adventurePage.setFilterValue(Category_Filter, DurationFilter);
        assertion.assertTrue(status,"unable to set filter");
        status = adventurePage.countFiltered(ExpectedFilteredResults);
        assertion.assertTrue(status,"something with filter result");

        System.out.println("TestCase02 pass");

        

        

    }






}
