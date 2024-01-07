
package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoryPage {
    RemoteWebDriver driver;
    private String url= "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html";
    @FindBy(xpath = "//button[text()='Cancel']")
    private WebElement cancelBtn;
    @FindBy(xpath = "//tbody//th[1]")
    private WebElement transId;


    public HistoryPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void getReservation(){
        if(!driver.getCurrentUrl().equals(this.url)){
            driver.get(this.url);
        }
    }
    public Boolean cancelResevation() throws InterruptedException{
        Thread.sleep(3000);
        try {
            cancelBtn.click();
            Thread.sleep(2000);
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
            //TODO: handle exception
        }
    
        

    }

    public String getTransactionId(){
        try {
            return transId.getText();

            
        } catch (Exception e) {
            return "No id available";
            //TODO: handle exception
        }


    }
    




}