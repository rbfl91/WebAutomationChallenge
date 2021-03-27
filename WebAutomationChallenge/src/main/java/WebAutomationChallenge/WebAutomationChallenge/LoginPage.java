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
	
	@FindBy(css = "#userName")
	public WebElement userBox;  
	
	@FindBy(css = "#password")
	public WebElement passwordBox;
	
	@FindBy(css = "#newUser")
	public WebElement newUserButton;
	
	@FindBy(css = "#login")
	public WebElement logInButton;
    
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
    
    public RegisterPage clickOnTheNewUserButton() {    
    	
    	//Waits until the button is clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(newUserButton));
    	
    	this.newUserButton.click();
    	System.out.println("The New User button has been clicked");
    	return new RegisterPage (this.driver);
    } 
    
    // To be called only after a new user is registered
    public ProfilePage clickOnTheLogInButtonAfterRegister() {    
    	
    	//Waits until the button is clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(logInButton));
    	
    	this.logInButton.click();
    	System.out.println("The Log In button has been clicked");
    	return new ProfilePage (this.driver);
    } 
    
    public MainPage clickOnTheLogInButton() {    
    	
    	//Waits until the button is clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(logInButton));
    	
    	this.logInButton.click();
    	System.out.println("The Log In button has been clicked");
    	return new MainPage (this.driver);
    } 
  
}
