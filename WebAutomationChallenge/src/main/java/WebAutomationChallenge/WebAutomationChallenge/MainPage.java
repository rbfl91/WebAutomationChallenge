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

public class MainPage { 
	
	WebDriver driver; 
	
	//Elements	 
	@FindBy(css = "#login")
	public WebElement loginButton;
    
	@FindBy(css = "#searchBox")
	public WebElement searchBox; 
	
	@FindBy(css = "#basic-addon2")
	public WebElement searchButton;
	
	@FindBy(css = "#userName-value")
	public WebElement userNameField;
	
	@FindBy(css = ".books-wrapper .rt-table .rt-tbody")
	public WebElement searchTable;
	
	
	
	// PageObject Constructor
    public MainPage(WebDriver driver) {
    	
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
    
    public MainPage writeOnTheSearchBox(String text) {   
    	
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.visibilityOf(searchBox));
    	wait.until(ExpectedConditions.elementToBeClickable(searchBox));
    	
    	this.searchBox.click();
    	this.searchBox.sendKeys(text);
    	System.out.println(text + " has been written on the text field"); 
    	return this;
    }
    
    public MainPage clickOnTheSearchButton() {    
    	
    	//Waits until the button is clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(searchButton));
    	
    	this.searchButton.click();
    	System.out.println("The search button has been clicked");
    	return this;
    } 
    
    public LoginPage clickOnTheLoginButton() {    
    	
    	//Waits until the button is clickable
    	WebDriverWait wait = new WebDriverWait(this.driver, 10);
    	wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    	
    	this.loginButton.click();
    	System.out.println("The login button has been clicked");
    	return new LoginPage (this.driver);
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
    
    public Book isBookAvailable(String wantedBook) {
    	
    	Boolean bookIsAvailable = false;
    	Book book = null;
    	
    	List <WebElement> allBookOptions = this.searchTable.findElements(By.cssSelector(".rt-tr-group")); 
    	
        for ( WebElement bookElement: allBookOptions) { 
        	
        	if (bookElement.getText().contains(wantedBook)) { 
        		
        		bookIsAvailable = true;
        		
        		List <WebElement> bookInfo = bookElement.findElements(By.cssSelector(".rt-tr .rt-td")); 
        		
        		book = new Book (wantedBook, bookInfo.get(2).getText(), bookInfo.get(3).getText());
        	}
        	
        } 
        
        return book;
    }
    
    

}
