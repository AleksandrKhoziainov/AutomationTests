package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorizationPage extends Page {

    //экран анкеты
    private By questionnaireFormLocator = By.cssSelector("#inputsPage form.uk-form.uk-form-stacked");

    //Кнопки
    @FindBy(css = "#emailFormatError .uk-alert-close.uk-close")
    public WebElement closeEmailError;

    @FindBy(css = "#invalidEmailPassword .uk-alert-close.uk-close")
    public WebElement closePasswordError;

    //Ошибки
    private By emailError = By.cssSelector("#emailFormatError");
    private By passwordOrEmailError = By.cssSelector("#invalidEmailPassword");

    //Тестовые данные
    public String incorrectLogin = "tes@protei.ru";
    public String incorrectPassword = "12345";
    public String incorrectFormatEmail = "test@protei";
    public String expectedEmailErrorText = "Неверный формат E-Mail";
    public String expectedPasswordOrEmailErrorText = "Неверный E-Mail или пароль";

    public AuthorizationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void waitEmailErrorIsInvisible() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(emailError));
    }

    public void inputText(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public void waitPasswordErrorIsInvisible() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(passwordOrEmailError));
    }

    public void assertEmailErrorText() {
        Assert.assertEquals(
                "Не отобразилась ошибка \"Неверный формат E-Mail\"",
                expectedEmailErrorText,
                driver.findElement(emailError).getText());
    }

    public void assertPasswordOrEmailErrorText() {
        Assert.assertEquals(
                "Не отобразилась ошибка \"Неверный E-Mail или пароль\"",
                expectedPasswordOrEmailErrorText,
                driver.findElement(passwordOrEmailError).getText());
    }

    public void assertQuestionnaireFormIsDisplayed() {
        Assert.assertTrue(
                "Не отобразилась страница с Анкетой",
                driver.findElement(questionnaireFormLocator).isDisplayed());
    }
}
