package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewsPage extends BasePage {

    private final By newArticleLocator = By.xpath("//div[@id='page_wrap']/div/div/div[@id='page_layout']/div[@id='page_body']"
            + "/div[@id='wrap3']/div/div/div[2]/div/div/div[@class='wall_wrap clear_fix']/div/div/div/div[@class='feed_row']"
            + "/div/div/div[@class='post_content']/div[@class='post_info']/div[1]/div[1]/div[@class='wall_post_text']");

    @FindBy(xpath = "//div[@id='page_layout']/div[@id='page_body']/div/div/div[1]/div[2]/div/div/div[1]/div/div[@class='post_field_wrap _emoji_field_wrap']/div[@id='post_field']")
    private WebElement articleElement;

    @FindBy(xpath = "//div[@id='page_layout']/div[@id='page_body']/div/div/div[1]/div[2]/div/div/div[1]/div/div[@id='submit_post']/div[1]/button")
    private WebElement submitArticleElement;

    @FindBy(xpath = "//div[@id='page_layout']/div[@id='page_body']/div/div/div[1]/div[2]/div/div/div[1]/div/div[@id='submit_post']/div[@id='page_add_media']/div[1]/div/a")
    private WebElement hoverToAttachFileElement;

    @FindBy(xpath = "//div[@id='page_layout']/div[@id='page_body']/div/div/div[1]/div[2]/div/div/div[1]/div/div[@id='submit_post']/div[@id='page_add_media']/div[1]/div/div/div/a[1]")
    private WebElement attachFileElement;

    @FindBy(xpath = "//div[@id='box_layer']/div[@class='popup_box_container']/div/div[@class='box_body box_no_buttons']/div/div[@id]/div[@id='docs_file_46623278_624062274']/div/span")
    private WebElement pickFileElement;

    public NewsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openArticleElement() {
        wait.until(ExpectedConditions.elementToBeClickable(articleElement)).click();
    }

    public void setArticleElement(String articleContent) {
        articleElement.clear();
        articleElement.sendKeys(articleContent);
    }

    public void submitArticleForm() {
        wait.until(ExpectedConditions.elementToBeClickable(submitArticleElement)).click();
    }

    public WebElement getNewPost() {
        try {
            return this.waitVisibilityAndFindElement(newArticleLocator);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Couldn't find current error element: " + e.getMessage());
        }
    }

    public void hoverToAttachFile() {
        Actions actions = new Actions(driver);
        // hover action
        actions.moveToElement(hoverToAttachFileElement).perform();
        actions.moveToElement(attachFileElement).click().perform();
    }

    public void attachFile() {
        pickFileElement.click();
    }
}
