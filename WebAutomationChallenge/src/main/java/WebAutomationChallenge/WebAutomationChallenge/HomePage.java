package WebAutomationChallenge.WebAutomationChallenge;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HomePage {

	WebDriver driver;

	//Page Object Elements
	@FindBy(css = "#html-body > div.page-wrapper > header > div.panel.wrapper > div > div.welcome > a > i")
	public WebElement signInButton;

	@FindBy(partialLinkText = "HIGH LIFE CAFÉ")
	public WebElement coffeePageButton;

	@FindBy(css = "#search")
	public WebElement searchBox;

	@FindBy(xpath = "//*[@id=\"search_mini_form\"]/div[2]/button")
	public WebElement searchButton;

	@FindBy(xpath = "//*[@id=\"amgdprcookie-form\"]/div[2]/div[2]/button[3]")
	public WebElement allowCookiesButton;


	// PageObject Constructor
    public HomePage(WebDriver driver) {
    	
        this.driver = driver;
        // Initialise all elements of the page
        PageFactory.initElements(driver, this);
    }
	
	// Test Case Methods
    public Boolean checkText(String text) { 
    	
    	Boolean toReturn = false; 
    	
    	if(this.driver.getPageSource().contains(text)) {
    	    
    		toReturn = true;
    	} 
    	return toReturn;
    }
    
    public HomePage writeOnTheSearchBox(String text) {

		//Explicit wait for the search box to be displayed
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(searchBox));
    	wait.until(ExpectedConditions.elementToBeClickable(searchBox));
    	
    	this.searchBox.click();
    	this.searchBox.sendKeys(text);
    	System.out.println(text + " has been written on the search box field");
    	return this;
    }
    
    public HomePage clickOnTheSearchButton() {
    	
    	//Explicit wait for the button to be clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(searchButton));
    	
    	this.searchButton.click();
    	System.out.println("The Search button has been clicked");
    	return this;
    } 
    
    public LoginPage clickOnTheSignInButton() {
    	
    	//Explicit wait for the button to be clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(signInButton));
    	
    	this.signInButton.click();
    	System.out.println("The Sign In button has been clicked");
    	return new LoginPage (this.driver);
    }

	public ProductListPage clickOnTheCoffeePageButton() {

		//Explicit wait for the button to be clickable
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(coffeePageButton));

		Actions actions = new Actions(this.driver);
		actions.moveToElement(coffeePageButton).click();

		Action action = actions.build();
		action.perform();

		//this.coffeePageButton.click();
		System.out.println("The Coffee Page button has been clicked");
		return new ProductListPage (this.driver);
	}

	public HomePage clickOnTheAllowCookiesButton() {

		//Explicit wait for the button to be clickable
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(allowCookiesButton));

		this.allowCookiesButton.click();
		System.out.println("The Allow Cookies button has been clicked");
		return this;
	}
}