import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

public class UploadImagePage extends PageBase {
    private By UploadButtonLocator = By.xpath("//div//input[contains(@type, 'file')]");
    private By ProgressBarLocator = By.xpath("//div/progress");

    public UploadImagePage(WebDriver driver) {
        super(driver);
        waitAndReturnElement(bodyLocator);
    }

    public String getProgressBarValue() {
        WebElement progressBar = waitAndReturnElement(ProgressBarLocator);

        return progressBar.getAttribute("value");
    }

    public void Process_Upload(String dir) {
        waitAndReturnElement(UploadButtonLocator).sendKeys(dir);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void Process_Exit() {
        driver.navigate().to("https://www.globalsqa.com/angularjs-protractor-practice-site/");
    }
}
