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

public class RegisterPage { 
	
	WebDriver driver; 
	
	@FindBy(css = "#firstname")
	public WebElement firstNameBox;   
	
	@FindBy(css = "#lastname")
	public WebElement lastNameBox; 
	
	@FindBy(css = "#userName")
	public WebElement userNameBox; 
	
	@FindBy(css = "#password")
	public WebElement passwordBox; 
	
	@FindBy(css = "#gotologin")
	public WebElement backToLoginButton;
	
	@FindBy(css = "#register")
	public WebElement registerButton;
	
	@FindBy(css = "#recaptcha-anchor")
	public WebElement captchaCheckBox;
    
	// PageObject Constructor
    public RegisterPage(WebDriver driver) {
    	
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
    
    public RegisterPage writeOnTheFirstNameBox(String text) {   
    	
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(firstNameBox));
    	
    	this.firstNameBox.sendKeys(text);
    	System.out.println(text + " has been written on the text field"); 
    	return this;
    }   
    
    public RegisterPage writeOnTheLastNameBox(String text) {   
    	
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(lastNameBox));
    	
    	this.lastNameBox.sendKeys(text);
    	System.out.println(text + " has been written on the text field"); 
    	return this;
    }
    
    public RegisterPage writeOnTheUserNameBox(String text) {   
    	
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(userNameBox));
    	
    	this.userNameBox.sendKeys(text);
    	System.out.println(text + " has been written on the text field"); 
    	return this;
    }
    
    public RegisterPage writeOnThePasswordBox(String text) {   
    	
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(passwordBox));
    	
    	this.passwordBox.sendKeys(text);
    	System.out.println(text + " has been written on the text field"); 
    	return this;
    }
    
    public RegisterPage clickOnTheCaptchaCheckBox() {    
    	
    	//Waits until the checkbox is clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(captchaCheckBox));
    	
    	this.captchaCheckBox.click();
    	System.out.println("The Captcha has been clicked");
    	return this;
    } 
    
    public RegisterPage clickOnTheRegisterButton() {    
    	
    	//Waits until the button is clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(registerButton));
    	
    	this.registerButton.click();
    	System.out.println("The Register button has been clicked");
    	return this;
    } 
    
    public LoginPage clickOnTheBackToLoginButton() {    
    	
    	//Waits until the button is clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(backToLoginButton));
    	
    	this.backToLoginButton.click();
    	System.out.println("The Back to Login button has been clicked");
    	return new LoginPage (this.driver);
    } 
  
}
