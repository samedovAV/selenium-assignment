package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//div[@id='page_body']/div[@id='wrap3']/div/div[@class='IndexPageContent']/div[@id='index_rcolumn']/div[@id='index_login']/div/form/input[@type='text']")
    private WebElement phoneOrEmailElement;

    @FindBy(xpath = "//div[@id='page_body']/div[@id='wrap3']/div/div[@class='IndexPageContent']/div[@id='index_rcolumn']/div[@id='index_login']/div/form/button")
    private WebElement submitElement;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setPhoneOrEmailElement(String phoneOrEmail) {
        phoneOrEmailElement.clear();
        phoneOrEmailElement.sendKeys(phoneOrEmail);
    }

    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(submitElement)).click();
    }
}
