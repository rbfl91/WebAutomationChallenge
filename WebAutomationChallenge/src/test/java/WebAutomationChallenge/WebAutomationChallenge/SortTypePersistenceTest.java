package WebAutomationChallenge.WebAutomationChallenge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

	  // Step 1: User enters home page, no login required
	  Assert.assertTrue(homePage.checkText("BRITISH AIRWAYS"));

	  // Step 2: Click on the "ProductListButton" and navigate to "ProductListPage"
	  productListPage = homePage.clickOnTheCoffeePageButton();
	  Assert.assertTrue(productListPage.isLoaded(), "Product List Page did not load successfully.");

	  // Step 3: Verify the "sorter" dropdown is selected with "Default"
	  String initialSorterSelection = productListPage.getCurrentSorterSelectionText();
	  Assert.assertEquals(initialSorterSelection, "Default", "Initial sorter selection is not 'Default'.");

	  // Step 4: Change the "sorter" dropdown to the 2nd element
	  productListPage.selectSorterByIndex(1);
	  String newSorterSelection = productListPage.getCurrentSorterSelectionText();
	  Assert.assertNotEquals(newSorterSelection, "Default", "Sorter selection did not change.");
	  Assert.assertEquals(newSorterSelection, "New Arrivals", "Sorter selection did not update to the expected option.");

	  // Step 5: Change the pagination through the "limiter" dropdown to the 2nd element
	  productListPage.selectLimiterByIndex(1);

	  // Step 6: Verify that the "sorter" dropdown has persisted its selection with the 2nd element
	  String secondSortOption = productListPage.getCurrentSorterSelectionText();
	  Assert.assertEquals(secondSortOption, "                    New Arrivals                ", "The sorter dropdown did not persist the 2nd option.");
  }
  
  @BeforeClass
  public void testSetup() {
	  
	  System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
	  driver = new ChromeDriver();
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
	  
	  driver.quit();
  }
}