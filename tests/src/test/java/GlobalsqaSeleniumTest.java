import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Properties;

public class GlobalsqaSeleniumTest {
    private WebDriver driver;
    private String test_firstname, test_lastname, test_username, test_password;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }

    @Test
    public void testSelenium() {
        MainPage mainPage = new MainPage(this.driver);
        System.out.println(mainPage.getTitleName());

        AjsPage ajsPage = mainPage.openAjsSite();       
        
        ajsPage.clickButtonByText("RegistrationLogin");
        Assert.assertTrue(ajsPage.getBodyText().contains("AngularJS User Registration and Login Example"));
        test_firstname = PageBase.random_num_generator(8);
        test_lastname = PageBase.random_num_generator(8);
        test_username = PageBase.random_num_generator(8);
        test_password = PageBase.random_num_generator(8);
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.Process_Register(test_firstname, test_lastname, test_username, test_password);
        Assert.assertTrue(loginPage.getBodyText().contains("Registration successful"));
        loginPage.Process_Login(test_firstname, test_lastname, test_username, test_password);
        Assert.assertTrue(loginPage.getBodyText().contains("You're logged in!!"));
        loginPage.Process_Delete_and_Logout();
        Assert.assertTrue(ajsPage.getBodyText().contains("ANGULARJS WEBSITE & ITS COMPONENTS"));

        ajsPage.clickButtonByText("Upload Image");
        UploadImagePage uploadImagePage = new UploadImagePage(this.driver);
        uploadImagePage.Process_Upload("/tmp/Parliament.png");
        Assert.assertEquals("1", uploadImagePage.getProgressBarValue());
        uploadImagePage.Process_Exit();
        Assert.assertTrue(ajsPage.getBodyText().contains("ANGULARJS WEBSITE & ITS COMPONENTS"));

        ajsPage.clickButtonByText("Banking");
        BankingPage bankingPage = new BankingPage(this.driver);
        bankingPage.History_Test();
        Assert.assertTrue(bankingPage.getBodyText().contains("Customer Login"));
        bankingPage.Process_Login("Harry Potter");
        Assert.assertTrue(bankingPage.getBodyText().contains("Welcome Harry Potter"));
        bankingPage.Process_Deposit("1000");
        Assert.assertTrue(bankingPage.getBodyText().contains("Deposit Successful"));
        bankingPage.Process_Withdrawl("1000");
        Assert.assertTrue(bankingPage.getBodyText().contains("Transaction successful"));
        bankingPage.Process_Exit();
        Assert.assertTrue(mainPage.getBodyText().contains("PRACTICE WEBSITE & ITS COMPONENTS"));

        mainPage.clickButtonByText("SamplePage");
        ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile("config.properties");
        Properties properties = readPropertiesFile.getProperties();
        SamplePage samplePage = new SamplePage(this.driver, properties);
        samplePage.Process_Fill_Forms();
        Assert.assertTrue(samplePage.getBodyText().contains("Message Sent"));
        samplePage.Process_Exit();
        Assert.assertTrue(mainPage.getBodyText().contains("PRACTICE WEBSITE & ITS COMPONENTS"));

        mainPage.Hover_Test();
        Assert.assertTrue(mainPage.getBodyText().contains("A Course in Machine Learning"));
    }

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }
}
