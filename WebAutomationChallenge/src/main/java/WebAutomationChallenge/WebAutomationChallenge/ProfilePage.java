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
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage { 
	
	private static final String FileUtils = null;

	WebDriver driver; 
	
	@FindBy(css = "#userName-value")
	public WebElement userNameField;   
	
	@FindBy(xpath = "/html/body/div/div/div/div[2]/div[1]/div/div/div[6]/div/ul/li[2]/svg")
	public WebElement bookStoreButton; 
	
	// PageObject Constructor
    public ProfilePage(WebDriver driver) {
    	
        this.driver = driver;   
        
        // Initialise all elements of the page
        PageFactory.initElements(driver, this);
    }
	
	// Test-case methods  
    public Boolean checkText(String text) { 
    	
    	Boolean toReturn = false; 
    	
    	if(this.driver.getPageSource().contains(text)) {
    	    
    		toReturn = true;
    	} 
    	return toReturn;
    } 
    
    public String returnUserName () {   
    	
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(userNameField));
    	
    	System.out.println("The logged user name is: " + this.userNameField.getText());
    	
    	return this.userNameField.getText();
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
    
    public MainPage clickOnTheBookStoreButton() {    
    	
    	//Waits until the button is clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(bookStoreButton));
    	
    	this.bookStoreButton.click();
    	System.out.println("The Book Store button has been clicked");
    	return new MainPage (this.driver);
    } 
  
    
    
}