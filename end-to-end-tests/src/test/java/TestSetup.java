import driverfactory.DriverFactory;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.SECONDS;

public class TestSetup {

    WebDriver driver;

    @BeforeMethod
    public void SetUp(){
        driver = new DriverFactory().create();
        driver.manage().timeouts().implicitlyWait(Duration.of(2, SECONDS));
    }

    @AfterMethod
    public void TearDown(){
        driver.quit();
    }

    void navigateToApplication(){
        if(System.getenv("TARGET") != null && System.getenv("TARGET").equals("production")){
            // We load the production page up initially to gain access to the site before
            // adding in the cookie to disabled the welcome popup. We finally have to refresh
            // the page to ensure the cookie is read and the popup is disabled.
            driver.navigate().to("https://automationintesting.online/admin");
            driver.manage().addCookie(new Cookie("welcome", "true"));
            driver.navigate().refresh();
        } else {
            driver.navigate().to("http://localhost:3003/admin");
        }
    }

}
