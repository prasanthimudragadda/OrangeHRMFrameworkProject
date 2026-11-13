package driver;

import config.ConfigReader;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static void initDriver(String browser){
        boolean remote = Boolean.parseBoolean(ConfigReader.get("remote"));
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
        if(remote){
            driver.set(createRemoteDriver(browser));
        }
        else if (browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            if(headless){
                options.addArguments("--headless", "--window-size=1920,1080");
            }
            driver.set(new ChromeDriver(options));
        }
        else if (browser.equalsIgnoreCase("firefox")){
            FirefoxOptions options = new FirefoxOptions();
            if(headless){
                options.addArguments("--headless");
            }
            driver.set(new FirefoxDriver(options));
        }
        else if(browser.equalsIgnoreCase("edge")){
            EdgeOptions options = new EdgeOptions();
            if(headless){
                options.addArguments("--headless");
            }
            driver.set(new EdgeDriver(options));
        }
        else{
            throw new RuntimeException("Browser not supported: "+browser);
        }
       getDriver().manage().window().maximize();
        }

    private static WebDriver createRemoteDriver(String browser) {
        try {
            URL gridUrl = new URL(ConfigReader.get("gridUrl"));
            if (browser.equalsIgnoreCase("chrome")) {
                return new RemoteWebDriver(gridUrl, new ChromeOptions());
            }
            else if (browser.equalsIgnoreCase("firefox")) {
                return new RemoteWebDriver(gridUrl, new FirefoxOptions());
            }
            throw new RuntimeException("Browser not supported on Grid: " + browser);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Wrong gridUrl in config.properties", e);
        }
    }

    public static WebDriver getDriver(){
        return driver.get();
    }
    public static void quitDriver(){
        if(getDriver()!=null){
            getDriver().quit();
            driver.remove();
        }
    }

}
