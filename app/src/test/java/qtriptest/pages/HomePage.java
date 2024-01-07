package qtriptest.pages;

import java.rmi.Remote;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private String url = "https://qtripdynamic-qa-frontend.vercel.app/";
    RemoteWebDriver driver;
    @FindBy(xpath = "//input[@placeholder='Search a City ']")
    private WebElement searchBox;
    // @FindBy(xpath = "//h5[text()='No City found']")
    // private WebElement NoCityFoundError;
    @FindBy(xpath = "//ul[@id='results']//a")
    private WebElement result;
    @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    private List<WebElement> parentElements;


    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        if (!driver.getCurrentUrl().equals(this.url)) {
            driver.get(this.url);
        }
    }



    public Boolean SearchCity(String CityName) throws InterruptedException {

        try {
            Thread.sleep(2000);
            List<WebElement> parents =
                    driver.findElements(By.xpath("//div[@class='col-6 col-lg-3 mb-4']"));
            for (WebElement child : parents) {
    
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", child);
                Thread.sleep(2000);
                WebElement text = child.findElement(By.xpath(".//h5"));
                if (text.getText().equalsIgnoreCase(CityName)) {
    
    
    
                    child.findElement(By.xpath(".//a")).click();
                    return true;
    
    
                }
    
            }
            
        } catch (Exception e) {
            return false;
            //TODO: handle exception
        }
        return false;
       
        



    }
}


