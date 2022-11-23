package daisy.test;
import com.beust.jcommander.Parameter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    public static Logger log = LogManager.getLogger();


    @BeforeMethod
    @Parameters("browserName")
    public void beforeTest(String browserName) {
        setupWebDriver(browserName);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout( 30, TimeUnit.SECONDS);

    }

    @BeforeMethod(alwaysRun = true)
    public void launchAndLogin() throws InterruptedException {
        driver.get("https://www.webstaurantstore.com");
        log.info("Application Lanched Successfully!");
    }
    @AfterClass
    public void tearDownMethod()
    {
        driver.quit();
    }

    @AfterTest
    public void afterTest() {
         driver.close();
    }
    public void setupWebDriver(String browserName) {
        if(browserName.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")){

            WebDriverManager.chromedriver().setup();
            driver = new FirefoxDriver();
        }else {

            WebDriverManager.chromedriver().setup();
        }
    }

}
