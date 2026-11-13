package base;

import config.ConfigReader;
import driver.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser){
        DriverFactory.initDriver(browser);
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
