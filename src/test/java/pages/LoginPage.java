package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By myProfileLocator = By.xpath("//div[@id='page_wrap']/div/div/div[@id='page_layout']/div[@id='side_bar']/div/div[@class='side_bar_nav_wrap']/div/div/nav/ol/li[@id='l_pr']");
    private final By errorLocator = By.xpath("//div[@id='root']/div/div/div/div/div[2]/div/div/div/form/div[1]/div[@class='vkc__Password__Wrapper']/div[1]/div/div[@class='vkc__TextField__errorMessage']");
    private final By headerPopupLocator = By.xpath("//div[@id='page_wrap']/div/div/div[@id='page_header_cont']/div/header/ul/li[@class='HeaderNav__item']/a");

    @FindBy(xpath = "//div[@id='page_body']/div[@id='wrap3']/div/div[@class='IndexPageContent']/div[@id='index_rcolumn']/div[@id='index_login']/div/form/input[@type='text']")
    private WebElement phoneOrEmailElement;

    @FindBy(xpath = "//div[@id='page_body']/div[@id='wrap3']/div/div[@class='IndexPageContent']/div[@id='index_rcolumn']/div[@id='index_login']/div/form/button")
    private WebElement submitLoginElement;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div[1]/div[2]/div/div/div/form/div[1]/div[3]/div[1]/div[1]/input")
    private WebElement passwordElement;

    @FindBy(xpath = "//div[@id='root']/div/div/div/div[1]/div[2]/div/div/div/form/div[@class='vkc__EnterPasswordNoUserInfo__buttonWrap']/button")
    private WebElement submitPasswordElement;

    @FindBy(xpath = "//div[@id='page_wrap']/div/div/div[@id='page_header_cont']/div/header/ul/li[@class='HeaderNav__item']/div[2]/a[@id='top_logout_link']")
    private WebElement logoutElement;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getPhoneOrEmailElement() {
        return phoneOrEmailElement;
    }

    public void setPhoneOrEmailElement(String phoneOrEmail) {
        phoneOrEmailElement.clear();
        phoneOrEmailElement.sendKeys(phoneOrEmail);
    }

    public void setPasswordElement(String password) {
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void submitLoginForm() {
        wait.until(ExpectedConditions.elementToBeClickable(submitLoginElement)).click();
    }

    public void submitPasswordForm() {
        wait.until(ExpectedConditions.elementToBeClickable(submitPasswordElement)).click();
    }

    public WebElement getMyProfileElement() throws NoSuchElementException {
        try {
            return this.waitVisibilityAndFindElement(myProfileLocator);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find current profile element: " + e.getMessage());
        }
    }

    public WebElement getError() {
        try {
            return this.waitVisibilityAndFindElement(errorLocator);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find current error element: " + e.getMessage());
        }
    }

    public void openHeaderPopup() {
        try {
            WebElement el = this.waitVisibilityAndFindElement(headerPopupLocator);
            wait.until(ExpectedConditions.elementToBeClickable(el)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutElement)).click();
    }
}
