import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;


public class AjsPage extends PageBase {
    
    private By DismissButtonLocator = By.xpath("//*[@id='dismiss-button']");

    public AjsPage(WebDriver driver) {
        super(driver);
        try {
            waitAndReturnElement(bodyLocator);
            RemoveAds();
        } catch (Exception e) {
            e.printStackTrace();
        }       
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
}
