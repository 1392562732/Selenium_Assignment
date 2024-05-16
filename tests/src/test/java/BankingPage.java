import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

public class BankingPage extends PageBase {
    private By CustomerLoginButtonLocator = By.xpath("//div/button[contains(text(), 'Customer')]");
    //private By BankManagerLoginButtonLocator = By.xpath("//div/button[contains(text(), 'Bank Manager')]");
    //private By HomeButtonLocator = By.xpath("//div/button[contains(text(), 'Home')]");
    private By UserNameDropDownButtonLocator = By.xpath("//div/select[contains(@name, 'userSelect')]");
    private By LoginButtonLocator = By.xpath("//div//button[contains(text(), 'Login')]");
    private By DepositFunctionButtonLocator = By.xpath("//div//button[contains(@ng-click, 'deposit()')]");
    private By WithdrawlFunctionButtonLocator = By.xpath("//div//button[contains(@ng-click, 'withdrawl()')]");
    //private By LogoutButtonLocator = By.xpath("//div//button[contains(text(), 'Logout')]");
    private By DepositAmountBoxLocator = By.xpath("//div/form[contains(@ng-submit, 'deposit()')]//input[contains(text(), amount)]");
    private By DepositButtonLocator = By.xpath("//div/form/button[contains(text(), 'Deposit')]");
    private By WithdrawlButtonLocator = By.xpath("//div/form/button[contains(text(), 'Withdraw')]");
    private By WithdrawlAmountBoxLocator = By.xpath("//div/form[contains(@ng-submit, 'withdrawl()')]//input[contains(text(), amount)]");
    private By PromtLocator = By.xpath("//div/span[contains(@class, 'error')]");

    public BankingPage(WebDriver driver) {
        super(driver);
        waitAndReturnElement(bodyLocator);
    }

    public void History_Test() {
        waitAndReturnElement(CustomerLoginButtonLocator).click();
        waitAndReturnElement(UserNameDropDownButtonLocator);
        driver.navigate().back();
        waitAndReturnElement(CustomerLoginButtonLocator);
    }
    
    public void Process_Login(String username) {
        waitAndReturnElement(CustomerLoginButtonLocator).click();

        Select sel = new Select(waitAndReturnElement(UserNameDropDownButtonLocator));
        sel.selectByVisibleText(username);
        waitAndReturnElement(LoginButtonLocator).click();
        waitAndReturnElement(DepositFunctionButtonLocator);
    }

    public void Process_Deposit(String amount) {
        waitAndReturnElement(DepositFunctionButtonLocator).click();
        waitAndReturnElement(DepositAmountBoxLocator).sendKeys(amount);
        waitAndReturnElement(DepositButtonLocator).click();
        waitAndReturnElement(PromtLocator);
    }

    public void Process_Withdrawl(String amount) {
        waitAndReturnElement(WithdrawlFunctionButtonLocator).click();
        waitAndReturnElement(WithdrawlAmountBoxLocator).sendKeys(amount);
        waitAndReturnElement(WithdrawlButtonLocator).click();
        waitAndReturnElement(PromtLocator);
    }

    public void Process_Exit() {
        driver.navigate().to("https://www.globalsqa.com/demo-site/");
    }
}
