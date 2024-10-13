package WebAutomationChallenge.WebAutomationChallenge;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class ProductListPage {

    WebDriver driver;

	@FindBy(css = "#sorter")
	public WebElement sorterDropdown;

	@FindBy(css = "#limiter")
	public WebElement limiterDropdown;

	// PageObject Constructor
    public ProductListPage(WebDriver driver) {
    	
        this.driver = driver;   
        
        // Initialise all elements of the page
        PageFactory.initElements(driver, this);
    }
	
	// Test-case methods:

    // Method to select an option from the sorter dropdown by visible text
    public ProductListPage selectSorterByVisibleText(String option) {

        Select select = new Select(sorterDropdown);
        select.selectByVisibleText(option);
        // Return the current ProductListPage object
        return this;
    }

    // Method to select an option from the sorter dropdown by index
    public ProductListPage selectSorterByIndex(int index) {

        Select select = new Select(sorterDropdown);
        select.selectByIndex(index);
        // Return the current ProductListPage object
        return this;
    }

    // Method to select an option from the limiter dropdown by visible text
    public ProductListPage selectLimiterByVisibleText(String option) {

        Select select = new Select(limiterDropdown);
        select.selectByVisibleText(option);
        // Return the current ProductListPage object
        return this;
    }

    // Method to select an option from the limiter dropdown by index
    public ProductListPage selectLimiterByIndex(int index) {

        Select select = new Select(limiterDropdown);
        select.selectByIndex(index);
        // Return the current ProductListPage object
        return this;
    }

    // Method to get the current selected option text from the sorter dropdown
    public String getCurrentSorterSelectionText() {

        Select sorterSelect = new Select(sorterDropdown);
        return sorterSelect.getFirstSelectedOption().getText();
    }

    // Method to get the index of the current selected option in the sorter dropdown
    public int getCurrentSorterSelectionIndex() {

        Select sorterSelect = new Select(sorterDropdown);
        WebElement selectedOption = sorterSelect.getFirstSelectedOption();
        return sorterSelect.getOptions().indexOf(selectedOption);
    }

    // Method to check if the page has loaded
    public boolean isLoaded() {

        try {

            WebDriverWait wait = new WebDriverWait(this.driver, 10);

            // Wait for the sorter dropdown to be visible
            wait.until(ExpectedConditions.visibilityOf(sorterDropdown));

            // If the element is visible, returns true
            return true;
        } catch (Exception e) {

            // If an exception occurs (element not found or timeout), returns false
            return false;
        }
    }
}