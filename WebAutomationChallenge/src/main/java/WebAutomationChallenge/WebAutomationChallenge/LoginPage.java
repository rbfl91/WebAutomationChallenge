package WebAutomationChallenge.WebAutomationChallenge;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage { 
	
	WebDriver driver; 
	
	@FindBy(css = "#email")
	public WebElement userBox;  
	
	@FindBy(css = "#pass")
	public WebElement passwordBox;

	@FindBy(css = "#send2")
	public WebElement signInButton;
    
	// PageObject Constructor
    public LoginPage(WebDriver driver) {
    	
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
    
    public LoginPage writeOnTheUserNameBox(String text) {   
    	
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(userBox));
    	
    	this.userBox.sendKeys(text);
    	System.out.println(text + " has been written on the text field"); 
    	return this;
    }   
    
    public LoginPage writeOnThePasswordBox(String text) {   
    	
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(passwordBox));
    	
    	this.passwordBox.sendKeys(text);
    	System.out.println(text + " has been written on the text field"); 
    	return this;
    }
    
    public HomePage clickOnTheSignInButton() {
    	
    	//Explicit wait for the search box to be displayed
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(signInButton));
    	
    	this.signInButton.click();
    	System.out.println("The Sign In button has been clicked");
    	return new HomePage (this.driver);
    } 
  
}
