package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdventureDetailsPage {
    RemoteWebDriver driver;
    @FindBy(xpath = "//input[@name='name']")
    private WebElement nameInput;
    @FindBy(xpath = "//input[@name='date']")
    private WebElement dateInput;
    @FindBy(xpath = "//input[@name='person']")
    private WebElement countInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitBtn;
    @FindBy(id = "reserved-banner")
    private WebElement confirmBtn;



    public AdventureDetailsPage(RemoteWebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public Boolean bookingAdventure(String GuestName, String date, String count)
        
            throws InterruptedException {
                Thread.sleep(3000);
        try {
            nameInput.sendKeys(GuestName);
            Thread.sleep(3000);
            dateInput.sendKeys(date);
            Thread.sleep(2000);
            countInput.clear();
            countInput.sendKeys(count);
            Thread.sleep(2000);
            submitBtn.click();
            Thread.sleep(2000);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
            // TODO: handle exception
        }


    }

    public Boolean isBookingSuccessfull() throws InterruptedException {
        Thread.sleep(3000);
        try {
            if (confirmBtn.isDisplayed()) {
                return true;
            }
            return true;


        } catch (Exception e) {
            e.printStackTrace();

            return false;
            // TODO: handle exception
        }
        
    }

}
