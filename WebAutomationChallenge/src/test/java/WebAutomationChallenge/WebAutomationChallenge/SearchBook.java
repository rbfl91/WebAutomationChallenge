package WebAutomationChallenge.WebAutomationChallenge;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchBook {

	WebDriver driver; 
	
	MainPage mainPage;
	LoginPage loginPage;
	RegisterPage registerPage; 
	ProfilePage profilePage;
	
	File scrFile;
	
	Scanner input = new Scanner(System.in); 
	
	String alertMessage = null;
	
	String userName = "User11";
	String password = "Teste123$";
	String searchTerm = "JavaScript"; 
	String wantedBook = "Speaking JavaScript";
	
  @Test(description="This method validates the book searching functionality")
  public void searchBook() { 
	  
	  mainPage = new MainPage (driver);
	  
	  loginPage = mainPage.clickOnTheLoginButton();  
	  loginPage.writeOnTheUserNameBox(userName); 
	  loginPage.writeOnThePasswordBox(password);
	  
	  mainPage = loginPage.clickOnTheLogInButton();
	  
	  //Asserts through the user name text on the page
	  assertTrue (mainPage.returnUserName().equals(userName)); 
	  
	  mainPage.writeOnTheSearchBox(searchTerm); 
	  mainPage.clickOnTheSearchButton();
	  
	  System.out.println("A screenshot of the search results has been taken. \n The screenshot file path is: " + mainPage.captureScreen());
	  
	  if (mainPage.isBookAvailable(this.wantedBook)) { 
		  
		  System.out.println("The book " + this.wantedBook + " is available on the search results.");
	  }
	  
	  //TODO 
	  /* 
	    - Data Provider 
		- library report 
		- log register 
		- API gateway (for optional part)

		- How to know if used structure would be scalable? Many tests in sequence are possible? 
		- Make the tests scalable for dynamic data?
		- ProfilePage could inherit from MainPage? 
	   */
	  
  }
  
  @BeforeClass
  public void testSetup() {
	  
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\rbfli\\Documents\\chromedriver_win32\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  // Initiates the driver with ToolsQA's form page
	  this.driver.get("https://demoqa.com/books");
	  this.driver.manage().window().maximize();
	 
  }
  
  @BeforeMethod
  public void checkCurrentPagePre() { 
	  
	  System.out.println("We are currently on the following page:" + driver.getCurrentUrl());
  }

  @AfterMethod
  public void checkCurrentPagePost() {
	  
	  System.out.println("We are currently on the following page" + driver.getCurrentUrl()); 
  }


  @AfterClass
  public void afterClass() { 
	  
	  driver.quit();
  }

}
