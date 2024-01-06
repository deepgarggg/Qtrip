package qtriptest.pages;

import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    RemoteWebDriver driver;
    @FindBy(id = "floatingInput") private WebElement userInput;
    @FindBy(xpath = "//input[@placeholder='Type to create account password']") private WebElement passInput;
    @FindBy(xpath = "//input[@placeholder='Retype Password to Confirm']") private WebElement retypePass;
    @FindBy(xpath = "//button[text()='Register Now']")private WebElement registerBtn;
    private String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public String lastUseUserName;
    public RegisterPage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void navigateToRegisterPage(){
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(this.url);

        }
    }

    public Boolean registorNewuser(String userName, String password,Boolean generateRandomUsername ) throws InterruptedException{
        try {
            if(generateRandomUsername){
                userName = userName+UUID.randomUUID().toString();
            }else{
                userName = userName;
            }
            this.lastUseUserName = userName;
            userInput.sendKeys(userName);
            passInput.sendKeys(password);
            retypePass.sendKeys(password);
            // Thread.sleep(2000);
            registerBtn.click();
            WebDriverWait wait= new WebDriverWait(driver, 5);
            wait.until(
                ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login")
                
            );
            
            return true;
            
            
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return false;
        }
       
        


       
    }
    
    


}
