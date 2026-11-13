package tests;

import base.BaseTest;
import config.ConfigReader;
import driver.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LogoutTest extends BaseTest {
    @Test(groups = {"smoke"})
    public void logoutTest() {
        LoginPage lp = new LoginPage(DriverFactory.getDriver());
        lp.login(ConfigReader.get("username"), ConfigReader.get("password"));

        DashboardPage dp = new DashboardPage(DriverFactory.getDriver());
        dp.logout();

        Assert.assertTrue(lp.isLoginButtonDisplayed(), "Login page not shown after logout");
    }
}
