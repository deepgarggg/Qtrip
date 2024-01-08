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


public class testCase_04 {
    String SearchCity = "";
    String AdventureName = "";
    String GuestName = "";
    String Date = "";
    String count = "";
    private String lastUseUserName;
    RemoteWebDriver driver;
    static ReportSingleton reportInstance;

    @BeforeTest(alwaysRun = true)
    public void createDriver() throws MalformedURLException {
        driver = DriverSingleton.getWebDriverInstance();
        reportInstance = ReportSingleton.getExtentReportInstance();
    }

    @Test(dataProvider = "data-provider", dataProviderClass = DP.class,priority = 4,groups={"Reliability Flow"})
    public void TestCase04(String UserName, String password, String dataset1, String dataset2,
            String dataset3) throws InterruptedException {
        System.out.println("TestCase04 Start");
        Boolean status;

        System.out.println("User registration start");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegisterPage();
        status = registerPage.registorNewuser(UserName, password, true);
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Registration Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Registration Unsuccess");
            
        }
        Assertion assertion = new Assertion();
        assertion.assertTrue(status, "new user registration Fail");
        lastUseUserName = registerPage.lastUseUserName;
        System.out.println("new user successfully register and now login funtion perform");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        status = loginPage.performLogin(lastUseUserName, password);
        if(status){
            reportInstance.test.log(LogStatus.PASS, "Login Success");
            
        }else{
            reportInstance.test.log(LogStatus.FAIL, "Login Unsuccess");
            
        }
        assertion.assertTrue(status, "login fail");
        System.out.println("Login successfull");
 

        for (int i = 0; i < 3; i++) {
            if(i == 0){
                String[] value = dataset1.split(";");
                extract(value);
            }
            if(i == 1){
                String[] value = dataset2.split(";");
                extract(value);


            }
            if(i == 2){
                String[] value = dataset3.split(";");
                extract(value);


            }
            System.out.println("Lets verify serching working :");
            HomePage homePage = new HomePage(driver);
            homePage.navigateToHomePage();
            status = homePage.SearchCity(SearchCity);
            if(status){
                reportInstance.test.log(LogStatus.PASS, "Search CIty Success");
                
            }else{
                reportInstance.test.log(LogStatus.FAIL, "Search City Unsuccess");
                
            }
            assertion.assertTrue(status, "Serching not working");
            AdventurePage adventurePage = new AdventurePage(driver);
            status = adventurePage.selectAdventure(AdventureName);
            if(status){
                reportInstance.test.log(LogStatus.PASS, "Select Adventure Success");
                
            }else{
                reportInstance.test.log(LogStatus.FAIL, "Select Adventure Unsuccess");
                
            }
            assertion.assertTrue(status, "unable to select adventure Name");
            System.out.println("Successfully Search Cityadventure");


            AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);

            status = adventureDetailsPage.bookingAdventure(GuestName, Date, count);
            if(status){
                reportInstance.test.log(LogStatus.PASS, "Booking Success");
                
            }else{
                reportInstance.test.log(LogStatus.FAIL, "Booking Unsuccess");
                
            }

            assertion.assertTrue(status, "unable to fill details");
            System.out.println("Detail Fill successfully");

            status = adventureDetailsPage.isBookingSuccessfull();
            if(status){
                reportInstance.test.log(LogStatus.PASS, "Verify Success");
                
            }else{
                reportInstance.test.log(LogStatus.FAIL, "Verify Unsuccess");
                
            }
            assertion.assertTrue(status, "Booking not successful");
            System.out.println("Booking successFully");
            HistoryPage historyPage = new HistoryPage(driver);
            historyPage.getReservation();
            System.out.println("all good working testcse04");

        }



    }

    private void extract(String[] value) {
        for (int i = 0; i < value.length; i++) {
            SearchCity = value[0];
            AdventureName = value[1];
            GuestName = value[2];
            Date = value[3];
            count = value[4];
        }
    }

}
