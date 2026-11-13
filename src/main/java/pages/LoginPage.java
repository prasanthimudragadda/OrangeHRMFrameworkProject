package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

        public LoginPage(WebDriver driver) {
            super(driver);
        }
        private final By usernameField = By.xpath("//input[@placeholder='Username']");
        private final By passwordField = By.xpath("//input[@placeholder='Password']");
        private final By loginButton = By.cssSelector("button[type='submit']");
        private final By errorMessage = By.cssSelector(".oxd-alert-content-text");

    public void login(String username, String password){
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);

    }
    public String getErrorMessage(){
        return getText(errorMessage);
    }
}
