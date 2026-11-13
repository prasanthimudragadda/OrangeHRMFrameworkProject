package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage{


    public DashboardPage(WebDriver driver){
        super(driver);
    }
    private final By header = By.cssSelector(".oxd-topbar-header-breadcrumb h6");
    private final By userDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private final By logoutLink = By.xpath("//a[text()='Logout']");

    public String getHeaderText(){
        return getText(header);
    }

    public void logout(){
        click(userDropdown);
        click(logoutLink);
    }
}
