import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.UUID;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginTest {

    private WebDriver driver;
    private Properties properties;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        this.driver.get("https://vk.com/");

        // load properties
        properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            // Load the properties file
            fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the input stream
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setPhoneOrEmailElement(properties.getProperty("testlogin"));
        loginPage.submitLoginForm();

        loginPage.setPasswordElement(properties.getProperty("testpassword"));
        loginPage.submitPasswordForm();

        WebElement myProfile = loginPage.getMyProfileElement();

        assertEquals("Моя страница", myProfile.getText());
    }

    @Test
    public void testLoginWithRandomData() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setPhoneOrEmailElement(properties.getProperty("testlogin"));
        loginPage.submitLoginForm();

        loginPage.setPasswordElement(UUID.randomUUID().toString()); // random data
        loginPage.submitPasswordForm();

        WebElement error = loginPage.getError();

        assertEquals("Incorrect password", error.getText());
    }

    @Test
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setPhoneOrEmailElement(properties.getProperty("testlogin"));
        loginPage.submitLoginForm();

        loginPage.setPasswordElement(properties.getProperty("testpassword"));
        loginPage.submitPasswordForm();

        loginPage.openHeaderPopup();
        loginPage.logout();

        assertNotNull(loginPage.getPhoneOrEmailElement());
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
