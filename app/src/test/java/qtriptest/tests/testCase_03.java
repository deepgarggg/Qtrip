package qtriptest.tests;


import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;


public class testCase_03 {
    private String lastUseUserName;
    RemoteWebDriver driver;
    static ReportSingleton reportInstance;
    @BeforeTest(alwaysRun = true)
    public void  createDriver() throws MalformedURLException{
        driver = DriverSingleton.getWebDriverInstance();
        reportInstance = ReportSingleton.getExtentReportInstance();
    }
    @Test(dataProvider = "data-provider",dataProviderClass = DP.class , enabled = true,priority = 3,groups = "Booking and Cancellation Flow")
    public void TestCase03(String UserName,String password,String SearchCity,String AdventureName,String GuestName,String Date,String count) throws InterruptedException{
        System.out.println("TestCase03 Start");
        Boolean status;
        System.out.println("User registration start");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegisterPage();
        status =registerPage.registorNewuser(UserName, password, true);
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Registration Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Registration Unsuccess");
            
        }
        Assertion assertion = new Assertion();
        assertion.assertTrue(status,"new user registration Fail");
        lastUseUserName= registerPage.lastUseUserName; 
        System.out.println("new user successfully register and now login funtion perform");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        status=loginPage.performLogin(lastUseUserName, password);
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Login Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Login Unsuccess");
            
        }
        assertion.assertTrue(status,"login fail");
        System.out.println("Login successfull");
        System.out.println("Lets verify serching working :");
        HomePage homePage = new HomePage(driver);
        status = homePage.SearchCity(SearchCity);
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Search CIty Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Search City Unsuccess");
            
        }
        assertion.assertTrue(status,"Serching not working");
        AdventurePage adventurePage = new AdventurePage(driver);
        status =adventurePage.selectAdventure(AdventureName);
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Search Adventure Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Search Adventure Unsuccess");
            
        }
        assertion.assertTrue(status,"unable to select adventure Name");
        System.out.println("Successfully Search Cityadventure");


        AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);
        
        status = adventureDetailsPage.bookingAdventure(GuestName, Date, count);
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Booking Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Booking Unsuccess");
            
        }

        assertion.assertTrue(status,"unable to fill details");
        System.out.println("Detail Fill successfully");

        status =adventureDetailsPage.isBookingSuccessfull();
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Verify Booking Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Verify Booking Unsuccess");
            
        }
        assertion.assertTrue(status,"Booking not successful");
        System.out.println("Booking successFully");
        HistoryPage historyPage = new HistoryPage(driver);
        historyPage.getReservation();
        
        String getIdNo =historyPage.getTransactionId();
        System.out.println(getIdNo);
        status =historyPage.cancelResevation();
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Cancel Booking Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Cancel Booking Unsuccess");
            
        }
        assertion.assertTrue(status,"fail to cancel");
        System.out.println("all good working testcse03");
        


    }


}
