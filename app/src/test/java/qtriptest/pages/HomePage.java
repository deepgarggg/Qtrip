package qtriptest.pages;

import java.rmi.Remote;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
   private String url = "https://qtripdynamic-qa-frontend.vercel.app/";
   RemoteWebDriver driver;


   public void HomePage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
   }

   public void navigateToHomePage(){
    if(!this.driver.getCurrentUrl().equals(this.url)){
        driver.get(this.url);
    }
   }

}
