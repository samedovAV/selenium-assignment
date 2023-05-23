import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.NewsPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class NewsTest  {

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
    public void testCreatePost() {
        LoginPage loginPage = new LoginPage(driver);
        login(loginPage);

        NewsPage newsPage = new NewsPage(driver);
        newsPage.openArticleElement();
        newsPage.setArticleElement("New Post!");

        newsPage.hoverToAttachFile();
        newsPage.attachFile();
        newsPage.submitArticleForm();

        WebElement newPost = newsPage.getNewPost();

        assertEquals("New Post!", newPost.getText());
    }

    private void login(LoginPage loginPage) {
        loginPage.setPhoneOrEmailElement(properties.getProperty("testlogin"));
        loginPage.submitLoginForm();
        loginPage.setPasswordElement(properties.getProperty("testpassword"));
        loginPage.submitPasswordForm();
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
