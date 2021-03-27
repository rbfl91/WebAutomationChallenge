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

public class SignUpTest {

	WebDriver driver; 
	
	MainPage mainPage;
	LoginPage loginPage;
	RegisterPage registerPage; 
	ProfilePage profilePage;
	
	File scrFile;
	
	Scanner input = new Scanner(System.in); 
	
	String alertMessage = null;
	
	String firstName = "Name6";
	String lastName = "Last6";
	String userName = "User11";
	String password = "Teste123$";
	
  @Test(description="This method validates the sign up functionality")
  public void signUp() { 
	  
	  mainPage = new MainPage (driver);
	  
	  loginPage = mainPage.clickOnTheLoginButton(); 
	  
	  registerPage = loginPage.clickOnTheNewUserButton(); 
	  
	  registerPage.writeOnTheFirstNameBox(firstName);
	  registerPage.writeOnTheLastNameBox(lastName); 
	  registerPage.writeOnTheUserNameBox(userName);
	  registerPage.writeOnThePasswordBox(password); 
	  
	  System.out.println("\n\n\nPlease, complete Google's reCapatcha manually now");
	  System.out.println("Type 'OK' when Google's reCaptcha is completed");
	  while (!input.nextLine().contentEquals("OK")) { 
		  
		  System.out.println("Type 'OK' when Google's reCaptcha is completed");
	  }
	  
	  registerPage.clickOnTheRegisterButton(); 
	  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  wait.until(ExpectedConditions.alertIsPresent());
	  
	  alertMessage = driver.switchTo().alert().getText();
	  System.out.println(alertMessage);
	  
	  //Asserts through the pop-up alert message
	  assertTrue (alertMessage.equals("User Register Successfully.")); 
	  
	  driver.switchTo().alert().accept(); 
	  
	  loginPage = registerPage.clickOnTheBackToLoginButton(); 
	  
	  loginPage.writeOnTheUserNameBox(userName); 
	  loginPage.writeOnThePasswordBox(password);
	  
	  profilePage = loginPage.clickOnTheLogInButtonAfterRegister();
	  
	  System.out.println("The screenshot file path is: " + profilePage.captureScreen());
	  
	  //Asserts through the user name text on the page
	  assertTrue (profilePage.returnUserName().equals(userName));
	  
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
	  
	  //driver.quit();
  }

}
