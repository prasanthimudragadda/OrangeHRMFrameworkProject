package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage{


    public DashboardPage(WebDriver driver){
        super(driver);
    }
    private By header = By.cssSelector(".oxd-topbar-header-breadcrumb h6");

    public String getHeaderText(){
        return getText(header);
    }
}
