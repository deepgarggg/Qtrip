package qtriptest.tests;


import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;


public class testCase_03 {
    private String lastUseUserName;
    RemoteWebDriver driver;
    @BeforeTest(alwaysRun = true)
    public void  createDriver() throws MalformedURLException{
        driver = DriverSingleton.getWebDriverInstance();
    }
    @Test(dataProvider = "data-provider",dataProviderClass = DP.class , enabled = true,priority = 3,groups = "Booking and Cancellation Flow")
    public void TestCase03(String UserName,String password,String SearchCity,String AdventureName,String GuestName,String Date,String count) throws InterruptedException{
        System.out.println("TestCase03 Start");
        Boolean status;
        System.out.println("User registration start");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegisterPage();
        status =registerPage.registorNewuser(UserName, password, true);
        Assertion assertion = new Assertion();
        assertion.assertTrue(status,"new user registration Fail");
        lastUseUserName= registerPage.lastUseUserName; 
        System.out.println("new user successfully register and now login funtion perform");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        status=loginPage.performLogin(lastUseUserName, password);
        assertion.assertTrue(status,"login fail");
        System.out.println("Login successfull");
        System.out.println("Lets verify serching working :");
        HomePage homePage = new HomePage(driver);
        status = homePage.SearchCity(SearchCity);
        assertion.assertTrue(status,"Serching not working");
        AdventurePage adventurePage = new AdventurePage(driver);
        status =adventurePage.selectAdventure(AdventureName);
        assertion.assertTrue(status,"unable to select adventure Name");
        System.out.println("Successfully Search Cityadventure");


        AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);
        
        status = adventureDetailsPage.bookingAdventure(GuestName, Date, count);

        assertion.assertTrue(status,"unable to fill details");
        System.out.println("Detail Fill successfully");

        status =adventureDetailsPage.isBookingSuccessfull();
        assertion.assertTrue(status,"Booking not successful");
        System.out.println("Booking successFully");
        HistoryPage historyPage = new HistoryPage(driver);
        historyPage.getReservation();
        
        String getIdNo =historyPage.getTransactionId();
        System.out.println(getIdNo);
        status =historyPage.cancelResevation();
        assertion.assertTrue(status,"fail to cancel");
        System.out.println("all good working testcse03");
        


    }


}
