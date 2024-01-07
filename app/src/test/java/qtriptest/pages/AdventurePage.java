package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {
    RemoteWebDriver driver;
    @FindBy(xpath = "//select[@id='category-select']")
    private WebElement addCategory;
    @FindBy(id = "duration-select" )
    private WebElement durationSelect;
    @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    private List<WebElement> listAll;
    @FindBy(xpath = "//div[contains(text(),'Clear')]")
    private List<WebElement> clearsBtn;
    @FindBy(xpath = "//input[@id='search-adventures']")
    private WebElement searchPackage;


    public AdventurePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void resetFilter() {
        for (WebElement clear : clearsBtn) {
            clear.click();
        }
    }

    public Boolean setFilterValue(String Category_Filter, String DurationFilter)
            throws InterruptedException {
        try {
            resetFilter();

            Select select = new Select(durationSelect);
            select.selectByVisibleText(DurationFilter);


            Select select2 = new Select(addCategory);
            select2.selectByVisibleText(Category_Filter);

            Thread.sleep(2000);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean countAll(String expectedUnFilteredResults) throws InterruptedException {

        try {
            resetFilter();
            // Convert the expected count to an integer
            int intExpectedUnFilteredResults = Integer.parseInt(expectedUnFilteredResults);
            
            Thread.sleep(3000);
            // List<WebElement> list =
            //         driver.findElements(By.xpath("//div[@class='col-6 col-lg-3 mb-4']"));

            


            // Compare the size of listAll with the expected count
            return listAll.size() == intExpectedUnFilteredResults;
        } catch (NumberFormatException e) {
            // Handle the case where expectedUnFilteredResults is not a valid integer
            System.err.println("Error: Invalid integer format for expectedUnFilteredResults");
            return false;
        }
    }

    public Boolean countFiltered(String ExpectedFilteredResults) {
        try {
            // Convert the expected count to an integer
            int intExpectedUnFilteredResults = Integer.parseInt(ExpectedFilteredResults);
            // List<WebElement> list =
                    // driver.findElements(By.xpath("//div[@class='col-6 col-lg-3 mb-4']"));


            // Compare the size of listAll with the expected count
            return listAll.size() == intExpectedUnFilteredResults;
        } catch (NumberFormatException e) {
            // Handle the case where expectedUnFilteredResults is not a valid integer
            System.err.println("Error: Invalid integer format for expectedUnFilteredResults");
            return false;
        }
    }
    ////div[@class='col-6 col-lg-3 mb-4']//div[@class='d-block d-md-flex justify-content-between flex-wrap pl-3 pr-3'][1]//h5

    public Boolean selectAdventure(String adventureName) throws InterruptedException{
        Thread.sleep(2000);
        resetFilter();
        try {
            searchPackage.sendKeys(adventureName);
            Thread.sleep(3000);
            driver.findElement(By.xpath("//div[@class='col-6 col-lg-3 mb-4']//a")).click();
            return true;

                
            }
            
         catch (Exception e) {
            //TODO: handle exception
            return false;
        }

        


        

    }

}
