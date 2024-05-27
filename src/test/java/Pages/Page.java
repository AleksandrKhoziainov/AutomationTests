package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#authButton")
    public WebElement loginButton;
    //Поля ввода
    public By emailTextFieldLocator = By.xpath("//label[@for='loginEmail']/following-sibling::input");
    public By passwordTextFieldLocator = By.xpath("//input[@type='password']");
    public String correctEmail = "test@protei.ru";
    public String correctPassword = "test";
    public String htmlLocation = "C://Users//Khozi//qa-test.html";

    public void inputCorrectEmail() {
        driver.findElement(emailTextFieldLocator).sendKeys(correctEmail);
    }

    public void inputCorrectPassword() {
        driver.findElement(passwordTextFieldLocator).sendKeys(correctPassword);
    }

    public void autorization() {
        inputCorrectEmail();
        inputCorrectPassword();
        loginButton.click();
    }

    public void open() {
        driver.get(htmlLocation);
    }
}
