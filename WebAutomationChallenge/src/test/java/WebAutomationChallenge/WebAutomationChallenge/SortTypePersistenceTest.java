package WebAutomationChallenge.WebAutomationChallenge;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import static org.testng.AssertJUnit.assertTrue;

public class SortTypePersistenceTest {

	WebDriver driver;
	HomePage homePage;
	ProductListPage productListPage;

  @Test(description="This method validates the persistence of the chosen sorter option dropdown after changing the pagination.")
  public void checkPersistenceAfterPagination() {

      ExtentTest test = null;
      try {
          test = ReportManager.startTest("SortTypePersistenceAfterPaginationTest");
          test.info("Starting the test case checkPersistenceAfterPagination");

          homePage = new HomePage(this.driver);
          homePage.clickOnTheAllowCookiesButton();

          //User enters home page, no login required
          Assert.assertTrue(homePage.checkText("Please select your Delivery Preference"));

          //Clicks on the "ProductListButton" and navigates to "ProductListPage"
          productListPage = homePage.clickOnTheCoffeePageButton();
          Assert.assertTrue(productListPage.isLoaded(), "Product List Page did not load successfully.");

          //Verifies the "sorter" dropdown is selected with "Default"
          String initialSorterSelection = productListPage.getCurrentSorterSelectionText();
          Assert.assertEquals(initialSorterSelection, "Default", "Initial sorter selection is not 'Default'.");

          //Changes the "sorter" dropdown to the 2nd element
          productListPage.selectSorterByIndex(1);
          String newSorterSelection = productListPage.getCurrentSorterSelectionText();
          Assert.assertNotEquals(newSorterSelection, "Default", "Sorter selection did not change.");
          Assert.assertEquals(newSorterSelection, "New Arrivals", "Sorter selection did not update to the expected option.");

          //Changes the pagination through the "limiter" dropdown to the 2nd element
          productListPage.selectLimiterByIndex(1);

          //Verifies that the "sorter" dropdown has persisted its selection with the 2nd element
          String secondSortOption = productListPage.getCurrentSorterSelectionText();
          Assert.assertEquals(secondSortOption, "New Arrivals", "The sorter dropdown did not persist the 2nd option.");
		  test.pass("Test Passed");

      } catch (Exception e) {
          assert test != null;
          test.fail("Test failed");
      }

  }
  
  @BeforeClass
  public void testSetup() {

	  ReportManager.initReport(); // Initialize the Test Report
	  
	  System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
	  driver = new ChromeDriver();
	  //System.setProperty("webdriver.gecko.driver", "/opt/homebrew/bin/geckodriver");
	  //driver = new FirefoxDriver();

	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  // Initiates the driver with the home page URL:
	  this.driver.get("https://highlifeshop.com/");
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

	  ReportManager.endReport(); // End the Test Report

	  driver.quit();
  }
}