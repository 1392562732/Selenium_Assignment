import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import java.util.Properties;

public class SamplePage extends PageBase {
    private Properties properties;
    
    private By UploadButtonLocator = By.xpath("//div//span/input[contains(@type, 'file')]");
    private By NameBoxLocator = By.xpath("//*[@id='g2599-name']");
    private By EmailBoxLocator = By.xpath("//*[@id='g2599-email']");
    private By WebsiteBoxLocator = By.xpath("//*[@id='g2599-website']");
    private By Experience_Locator = By.xpath("//*[@id='g2599-experienceinyears']");
    //private By Experience_0_1_Locator = By.xpath("//*[@id='g2599-experienceinyears']/option[@value='0-1']");
    //private By Experience_1_3_Locator = By.xpath("//*[@id='g2599-experienceinyears']/option[@value='1-3']");
    //private By Experience_3_5_Locator = By.xpath("//*[@id='g2599-experienceinyears']/option[@value='3-5']");
    //private By Experience_5_7_Locator = By.xpath("//*[@id='g2599-experienceinyears']/option[@value='5-7']");
    //private By Experience_7_10_Locator = By.xpath("//*[@id='g2599-experienceinyears']/option[@value='7-10']");
    //private By Experience_10_Locator = By.xpath("//*[@id='g2599-experienceinyears']/option[@value='10+']");
    //private By Expertise_Functional_Locator = By.xpath("//*[@name='g2599-expertise[]'][@value='Functional Testing']");
    //private By Expertise_Automation_Locator = By.xpath("//*[@name='g2599-expertise[]'][@value='Automation Testing']");
    //private By Expertise_Manual_Locator = By.xpath("//*[@name='g2599-expertise[]'][@value='Manual Testing']");
    //private By Education_Graduate_Locator = By.xpath("//*[@name='g2599-education'][@value='Graduate']");
    //private By Education_Post_Graduate_Locator = By.xpath("//*[@name='g2599-education'][@value='Post Graduate']");
    //private By Education_Other_Locator = By.xpath("//*[@name='g2599-education'][@value='Other']");
    private By Comment_Locator = By.xpath("//*[@id='contact-form-comment-g2599-comment']");
    private By SubmitButtonLocater = By.xpath("//p[@class='contact-submit']/button[contains(text(), 'Submit')]");

    private String Pic;
    private String Name;
    private String Email;
    private String Website;
    private String Experience_Years;
    private String Expertise1;
    private String Expertise2;
    private String Expertise3;
    private String Education;
    private String Comment;

    public SamplePage(WebDriver driver, Properties properties) {
        super(driver);
        this.properties = properties;
        this.Pic = properties.getProperty("Pic");
        this.Name = properties.getProperty("Name");
        this.Email = properties.getProperty("Email");
        this.Website = properties.getProperty("Website");
        this.Experience_Years = properties.getProperty("Experience_Years");
        this.Expertise1 = properties.getProperty("Expertise1");
        this.Expertise2 = properties.getProperty("Expertise2");
        this.Expertise3 = properties.getProperty("Expertise3");
        this.Education = properties.getProperty("Education");
        this.Comment = properties.getProperty("Comment");

        waitAndReturnElement(bodyLocator);
        RemoveAds();
    }

    public void Expertise_Selector() {
        for (int i = 1; i <= 3; i++) {
            String expertiseKey = "Expertise" + i;
            String expertise = properties.getProperty(expertiseKey);
            if (expertise != null && !expertise.isEmpty() && !expertise.equals("0")) {
                By locator = By.xpath("//*[@name='g2599-expertise[]'][@value='" + expertise + "']");
                waitAndReturnElement(locator).click();
            }
        }
    }

    public void Education_Selector() {
        By Education_Locator;
        switch (Education) {
            case "Graduate":
                Education_Locator = By.xpath("//*[@name='g2599-education'][@value='Graduate']");
                break;
            case "Post Graduate":
                Education_Locator = By.xpath("//*[@name='g2599-education'][@value='Post Graduate']");
                break;
            case "Other":
                Education_Locator = By.xpath("//*[@name='g2599-education'][@value='Other']");
                break;
            default:
                throw new IllegalArgumentException("Invalid education: " + Education);
        }
        waitAndReturnElement(Education_Locator).click();
    }

    public void Process_Fill_Forms() {
        waitAndReturnElement(UploadButtonLocator).sendKeys(Pic);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        waitAndReturnElement(NameBoxLocator).sendKeys(Name);
        waitAndReturnElement(EmailBoxLocator).sendKeys(Email);
        waitAndReturnElement(WebsiteBoxLocator).sendKeys(Website);
        
        Select sel = new Select(waitAndReturnElement(Experience_Locator));
        sel.selectByValue(Experience_Years);

        Expertise_Selector();

        Education_Selector();

        waitAndReturnElement(Comment_Locator).sendKeys(Comment);

        waitAndReturnElement(SubmitButtonLocater).click();
    }

    public void Process_Exit() {
        driver.navigate().to("https://www.globalsqa.com/demo-site/");
    }
}
