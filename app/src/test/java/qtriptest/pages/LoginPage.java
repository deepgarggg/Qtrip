package qtriptest.pages;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";
    RemoteWebDriver driver;
    @FindBy(xpath = "//input[@placeholder='name@example.com']")
    private WebElement userInput;
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passInput;
    @FindBy(xpath = "//button[text()='Login to QTrip']")
    private WebElement loginBtn;
    @FindBy(xpath = "//li[@class='nav-item auth']")
    private WebElement logOutBtn;


    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage() {
        if (!driver.getCurrentUrl().equals(this.url)) {
            driver.get(this.url);
        }
    }

    public Boolean performLogin(String userName, String password) throws InterruptedException {
        try { userInput.sendKeys(userName);
            passInput.sendKeys(password);
            Thread.sleep(2000);
            loginBtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.or(
                    ExpectedConditions
                            .urlToBe("https://qtripdynamic-qa-frontend.vercel.app/"),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='nav-item auth']"))));
            
    
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
       
    }
    public Boolean performLogoutFunction(){
        try {
            logOutBtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Login Here']")));


        return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        
        
    }
}
