package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class DriverSingleton {
    private static RemoteWebDriver driver;
    
    private DriverSingleton() throws MalformedURLException {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        driver.manage().window().maximize();
        System.out.println("createDriver()");
    }

    public static RemoteWebDriver getWebDriverInstance() throws MalformedURLException {
        if (driver == null) {
            synchronized (DriverSingleton.class) {
                if (driver == null) {
                    new DriverSingleton();
                }
            }
        }
        return driver;
    }
}