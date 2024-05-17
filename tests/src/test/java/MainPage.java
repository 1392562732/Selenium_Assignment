import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;

public class MainPage extends PageBase {

    private By ajsSiteOpenButtonLocator = By.xpath("//li[contains(@class, 'price_footer')]//ancestor::a[contains(text(), 'AngularJS Site')]");
    private By cookieConsentButtonLocator = By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/div[2]/button[1]/p");
    private By freeEbooksLocator = By.xpath("//li/a[contains(text(), 'Free Ebooks')]");
    private By freeMachineLearningEbooksLocator = By.xpath("//*[@id='menu-item-7132']");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.globalsqa.com/demo-site/");
        try {
            waitAndReturnElement(cookieConsentButtonLocator).click();//bypass cookie consent
            Thread.sleep(1000);
            RemoveAds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AjsPage openAjsSite() {
        RemoveAds();
        waitAndReturnElement(ajsSiteOpenButtonLocator).click();
        try {
            waitAndReturnElement(ajsSiteOpenButtonLocator).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjsPage(this.driver);
    }

    public void clickButtonByText(String ButtonName) {
        By locator = By.xpath("//li[contains(@class, 'price_footer')]/a[contains(text(), '" + ButtonName + "')]");     
        RemoveAds();
        waitAndReturnElement(locator).click();
        try {
            waitAndReturnElement(locator).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void Hover_Test() {
        RemoveAds();
        Actions actions = new Actions(driver);
        actions.moveToElement(waitAndReturnElement(freeEbooksLocator)).perform();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.moveToElement(waitAndReturnElement(freeMachineLearningEbooksLocator)).perform();
        actions.click().build().perform();
    }
}