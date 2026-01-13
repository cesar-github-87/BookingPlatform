package driverfactory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory
{
    private static String OS = System.getProperty("os.name").toLowerCase();


    public WebDriver create() {
        if(System.getenv("BROWSER") != null){
            if(System.getenv("BROWSER").equals("chrome")){
                return prepareChromeDriver();

            } else if (System.getenv("BROWSER").equals("remote")){
                return prepareRemoteDriver();
            } else {
                System.out.println("WARN: Browser option '" + System.getenv("browser") + "' not recognised. Falling back to ChromeDriver");
                return prepareChromeDriver();
            }
        }

        System.out.println("WARN: No browser option detected. Defaulting to ChromeDriver but if you want to use a different browser please assign a browser to the env var 'browser'.");

        return prepareChromeDriver();
    }

    private WebDriver prepareChromeDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        return new ChromeDriver(options);
    }

    private WebDriver prepareRemoteDriver(){
        if(System.getenv("SAUCE_USERNAME") == null){
            throw new RuntimeException("To use remote driver a Sauce lab account is required. Please assign your Sauce labs account name to the environmental variable 'sauce_username'");
        }

        if(System.getenv("SAUCE_ACCESS_KEY") == null){
            throw new RuntimeException("To use remote driver a Sauce lab account is required. Please assign your Sauce labs access key to the environmental variable 'sauce_access_key'");
        }

        String URL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("---headless=new");
        chromeOptions.setPlatformName("Windows 10");
        chromeOptions.setBrowserVersion("latest");

        MutableCapabilities sauceCaps = new MutableCapabilities();
        sauceCaps.setCapability("username", System.getenv("SAUCE_USERNAME"));
        sauceCaps.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
        sauceCaps.setCapability("name", "Restful-booker-platform");
        sauceCaps.setCapability("extendedDebugging", true);
        chromeOptions.setCapability("sauce:options", sauceCaps);

        try {
            return new RemoteWebDriver(new URL(URL), chromeOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException("WARN: An error occurred attempting to create a remote driver connection. See the following error: " + e);
        }
    }
}
