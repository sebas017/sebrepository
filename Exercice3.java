package acial.selenium.exercices;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Exercice3 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @DataProvider(name = "salaries")
  public Object[][] dataProviderMethod() {
	  return new Object[][] { 	{ "Guy","Jean","1239" }, 
		  						{ "Jean","Michel","4579" } };
  }
  
  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver",  "C:\\Formation\\chromedriver.exe");driver= new ChromeDriver();
    
    baseUrl = "http://www.universitedutest.com/OrangeHRM";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
    @Test(priority=1)
  public void testLogin() throws Exception {
    driver.get("http://www.universitedutest.com/OrangeHRM");
    driver.findElement(By.id("txtUsername")).clear();
    driver.findElement(By.id("txtUsername")).sendKeys("Admin");
    driver.findElement(By.id("txtPassword")).clear();
    driver.findElement(By.id("txtPassword")).sendKeys("Nantes$2020");
    driver.findElement(By.id("btnLogin")).click();
    try {
      assertEquals(driver.findElement(By.id("welcome")).getText(), "Welcome Admin");
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // ERROR: Caught exception [unknown command []]
    
  }
  @Test(priority=99)
  public void testDeconnexion() throws Exception {
    driver.findElement(By.linkText("Welcome Admin")).click();
    driver.findElement(By.xpath("//div[@id='welcome-menu']/ul/li[3]/a")).click();
  }
  @Test(dataProvider = "salaries", priority=2)
  public void testAjouteEmploye(String nom, String prenom, String Id) throws Exception {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]")).click();
    driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
    driver.findElement(By.id("btnAdd")).click();
    driver.findElement(By.id("firstName")).clear();
    driver.findElement(By.id("firstName")).sendKeys(prenom);
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys(nom);
    driver.findElement(By.id("employeeId")).clear();
    driver.findElement(By.id("employeeId")).sendKeys(Id);
    driver.findElement(By.id("btnSave")).click();
    driver.findElement(By.id("personal_optGender_1")).click();
    driver.findElement(By.id("btnSave")).click();
    new Select(driver.findElement(By.id("personal_cmbNation"))).selectByValue("64");
    new Select(driver.findElement(By.id("personal_cmbMarital"))).selectByValue("Single");
    driver.findElement(By.id("personal_DOB")).clear();
    driver.findElement(By.id("personal_DOB")).sendKeys("1970-12-12");
    driver.findElement(By.id("btnSave")).click();
   
  }
  @Test(priority=3)
  public void testSupprimerEmploye() throws Exception {
    driver.get("http://www.universitedutest.com/OrangeHRM/symfony/web/index.php/pim/viewEmployeeList");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Social Media Authentication'])[1]/following::b[1]")).click();
    driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
    driver.findElement(By.id("empsearch_id")).clear();
    driver.findElement(By.id("empsearch_id")).sendKeys("3805");
    driver.findElement(By.id("searchBtn")).click();
    driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td/input")).click();
    driver.findElement(By.id("btnDelete")).click();
    driver.findElement(By.id("dialogDeleteBtn")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown1() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent1(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent1() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText1() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
