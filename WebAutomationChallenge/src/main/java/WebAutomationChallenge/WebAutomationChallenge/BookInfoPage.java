package WebAutomationChallenge.WebAutomationChallenge;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookInfoPage { 
	
	WebDriver driver; 
	
	//Elements	    
	@FindBy(css = ".text-left #addNewRecordButton")
	public WebElement backToStoreButton; 
	
	@FindBy(css = ".text-right #addNewRecordButton")
	public WebElement addToCollectionButton;
	
	// PageObject Constructor
    public BookInfoPage(WebDriver driver) {
    	
        this.driver = driver;   
     	
        // Initialise all elements of the page
        PageFactory.initElements(driver, this);
    }
    
    public MainPage clickOnBackToStoreButton() {    
    	
    	//Waits until the button is clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(backToStoreButton));
    	
    	this.backToStoreButton.click();
    	System.out.println("The Back to Store button has been clicked");
    	return new MainPage (driver);
    }  
    
    public BookInfoPage clickOnTheAddButton() {    
    	
    	//Waits until the button is clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(addToCollectionButton));
    	
    	this.addToCollectionButton.click();
    	System.out.println("The Add to Collection button has been clicked");
    	return this;
    }  
    
    public String captureScreen() {
    	
        String path;
        try {
           
            File source = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
            path = "C:\\Users\\rbfli\\eclipse-workspace\\WebAutomationChallenge\\test-output\\" + source.getName();
            FileHandler.copy(source, new File(path)); 
        }
        catch(IOException e) {
        	
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        return path;
    }

}
