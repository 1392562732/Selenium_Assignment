import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

public class LoginPage extends PageBase {
    private By ButtonLocator = By.xpath("//div[contains(@class, 'form-actions')]//button[contains(@type, 'submit')]");
    private By RegisterSiteLocator = By.xpath("//div[contains(@class, 'form-actions')]//a[contains(text(), 'Register')]");
    private By usernameBoxLocator = By.xpath("//div[contains(@class, 'form-group')]//input[@name='username']");
    private By passwordLocator = By.xpath("//div[contains(@class, 'form-group')]//input[@name='password']");
    private By firstnameBoxLocator = By.xpath("//div[contains(@class, 'form-group')]//input[@name='firstName']");
    private By lastnameBoxLocator = By.xpath("//div[contains(@class, 'form-group')]//input[@name='lastName']");
    private By LoginTextLocator = By.xpath("//h2[contains(text(), 'Login')]");
    private By WelcomeTextLocator = By.xpath("//p[contains(text(), 'logged in!!')]");
    private By DeleteButtonLocator = By.xpath("//li[@class]//ancestor::a[contains(text(), 'Delete')]");
    private By LogoutButtonLocator = By.xpath("//p[contains(@class, 'ng-scope')]//ancestor::a[contains(text(), 'Logout')]");

    public LoginPage(WebDriver driver) {
        super(driver);
        waitAndReturnElement(bodyLocator);
    }

    public void Process_Register(String first_name, String last_name, String username, String password) {
        waitAndReturnElement(RegisterSiteLocator).click();//Open the Register Site

        waitAndReturnElement(firstnameBoxLocator).sendKeys(first_name);
        waitAndReturnElement(lastnameBoxLocator).sendKeys(last_name);
        waitAndReturnElement(usernameBoxLocator).sendKeys(username);
        waitAndReturnElement(passwordLocator).sendKeys(password);
        waitAndReturnElement(ButtonLocator).click();

        waitAndReturnElement(LoginTextLocator);//Ensure back to the login page
    }

    public void Process_Login(String first_name, String last_name, String username, String password) {
        waitAndReturnElement(usernameBoxLocator).sendKeys(username);
        waitAndReturnElement(passwordLocator).sendKeys(password);
        waitAndReturnElement(ButtonLocator).click();

        waitAndReturnElement(WelcomeTextLocator);
    }

    public void Process_Delete_and_Logout() {
        waitAndReturnElement(DeleteButtonLocator).click();
        waitAndReturnElement(LogoutButtonLocator).click();
        
        waitAndReturnElement(LoginTextLocator);//Ensure back to the login page

        driver.navigate().to("https://www.globalsqa.com/angularjs-protractor-practice-site/");
    }
}
