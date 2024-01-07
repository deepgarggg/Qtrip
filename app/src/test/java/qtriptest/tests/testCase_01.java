package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import com.beust.jcommander.Parameter;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class testCase_01 {
    static RemoteWebDriver driver;

    public static String lastUseUserName; 
    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        // Launch Browser using Zalenium
        driver = DriverSingleton.getWebDriverInstance();
    }
    @Test(dataProvider = "data-provider", dataProviderClass = DP.class, enabled = true, priority = 1,groups = {"Login Flow"})
    // @Parameters({"username" , "password"})
    public void TestCase01(String UserName,String password) throws InterruptedException{
        System.out.println("TestCase01 Start");
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
        status = loginPage.performLogoutFunction();
        assertion.assertTrue(status,"fail to logout");
        


        

    }
    @AfterSuite
    public static void quitDriver() {
        System.out.println("quit()");
        driver.quit();
    }

}