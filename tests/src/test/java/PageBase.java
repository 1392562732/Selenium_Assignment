import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import java.util.Random;


public class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static String ALLCHAR = "0123456789";

    protected By bodyLocator = By.tagName("body");
    
    /*private By usernameLocator = By.name("username");
    private By passwordLocator = By.name("password");
    private By signinButtonLocator = By.className("fa-sign-in");
    private By logoutButtonLocator = By.className("icon-signout");*/

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public String getTitleName() {
        return this.driver.getTitle();
    }
    
    public String getBodyText() {
        WebElement bodyElement = waitAndReturnElement(bodyLocator);
        return bodyElement.getText();
    }

    public static String random_num_generator(int len) {
        StringBuffer rnum = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
        rnum.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return rnum.toString();
    }

    public void RemoveAds() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("const elements = document.getElementsByClassName('google-auto-placed'); while (elements.length > 0) elements[0].remove()");
        js.executeScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove()");
    }
}
