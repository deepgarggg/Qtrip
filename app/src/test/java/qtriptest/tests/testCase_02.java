package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class testCase_02 {
    RemoteWebDriver driver;
    static ReportSingleton reportInstance;
    @BeforeTest(alwaysRun = true)
    public void  createDriver() throws MalformedURLException{
        driver = DriverSingleton.getWebDriverInstance();
        reportInstance= ReportSingleton.getExtentReportInstance();

    }
    
    @Test(dataProvider = "data-provider",dataProviderClass = DP.class , enabled = true, priority = 2,groups = {"Search and Filter flow"})
    public void TestCase02(String CityName,String Category_Filter,String DurationFilter,String ExpectedFilteredResults,String ExpectedUnFilteredResults) throws InterruptedException{
        HomePage homePage = new HomePage(driver);
        Boolean status;
        System.out.println("Start TestCase02 verify Filters working");
        homePage.navigateToHomePage();
        status =homePage.SearchCity(CityName);
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Search CIty Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Search City Unsuccess");
            
        }
        Assertion assertion = new Assertion();
        assertion.assertTrue(status,"Searching not Working");
        AdventurePage adventurePage = new AdventurePage(driver);
        
        status = adventurePage.countAll(ExpectedUnFilteredResults);
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Count all CIty Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Count City Unsuccess");
            
        }
        
        assertion.assertTrue(status,"somthing wrong with unFilter result");
        System.out.println("yes all city package is available");
        status = adventurePage.setFilterValue(Category_Filter, DurationFilter);
        assertion.assertTrue(status,"unable to set filter");
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Set Filter Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Set Filter Unsuccess");
            
        }
        status = adventurePage.countFiltered(ExpectedFilteredResults);
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Search Filter result Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Search Filter result Unsuccess");
            
        }
        assertion.assertTrue(status,"something with filter result");


        System.out.println("TestCase02 pass");

        

        

    }






}
