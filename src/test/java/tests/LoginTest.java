package tests;

import base.BaseTest;
import config.ConfigReader;
import driver.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ExcelUtil;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest(){

    LoginPage lp = new LoginPage(DriverFactory.getDriver());
    lp.login(ConfigReader.get("username"), ConfigReader.get("password"));

        DashboardPage dp = new DashboardPage(DriverFactory.getDriver());
        Assert.assertEquals(dp.getHeaderText(), "Dashboard");
}

@DataProvider(name="invalidData")
public Object[][] getInvalidData() throws IOException {
        return ExcelUtil.getData("src/test/resources/testdata/LoginData.xlsx", "InvalidLogin");
}

    @Test(dataProvider = "invalidData")
    public void invalidLoginTest(String username, String password) {
        LoginPage lp = new LoginPage(DriverFactory.getDriver());
        lp.login(username, password);

        Assert.assertEquals(lp.getErrorMessage(), "Invalid credentials");
    }
}
