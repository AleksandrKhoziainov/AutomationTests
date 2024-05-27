package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuestionnairePage extends Page {
    //Кнопки
    @FindBy(css = "button#dataSend")
    public WebElement addButton;

    //Ошибки
    private By emailFormatErrorLocator = By.cssSelector("#dataAlertsHolder #emailFormatError");
    private By emptyNameErrorLocator = By.cssSelector("#dataAlertsHolder #blankNameError");


    //Поля ввода
    public By emailTextFieldLocatorOnQuestionnaire = By.xpath(
            "//label[@for='dataEmail']/following-sibling::input");
    public By nameTextFieldLocator = By.xpath(
            "//label[@for='dataName']/following-sibling::input");
    @FindBy(css = "select#dataGender")
    public WebElement genderSelect;
    @FindBy(xpath = "//input[@id='dataCheck11']")
    public WebElement checkBoxChoiceOne;
    @FindBy(xpath = "//input[@id='dataCheck12']")
    public WebElement checkBoxChoiceTwo;
    @FindBy(xpath = "//*[@id='dataSelect21'][contains(@type,'radio')]")
    public WebElement radioButtonChoiceOne;
    @FindBy(xpath = "//*[@id='dataSelect22'][contains(@type,'radio')]")
    public WebElement radioButtonChoiceTwo;
    @FindBy(xpath = "//*[@id='dataSelect23'][contains(@type,'radio')]")
    public WebElement radioButtonChoiceThree;
    //Модальное окно
    private By popupLocator = By.cssSelector(".uk-modal-dialog");
    @FindBy(xpath = "//button[@class='uk-button uk-button-primary uk-modal-close']")
    public WebElement popupButton;


    //Таблица
    //ряд1
    public By emailFirstRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[1]//td[1]");
    public By nameFirstRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[1]//td[2]");
    public By genderFirstRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[1]//td[3]");
    public By choice1FirstRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[1]//td[4]");
    public By choice2FirstRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[1]//td[5]");
    //ряд2
    public By emailSecondRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[2]//td[1]");
    public By nameSecondRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[2]//td[2]");
    public By genderSecondRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[2]//td[3]");
    public By choice1SecondRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[2]//td[4]");
    public By choice2SecondRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[2]//td[5]");
    //ряд3
    public By emailThirdRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[3]//td[1]");
    public By nameThirdRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[3]//td[2]");
    public By genderThirdRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[3]//td[3]");
    public By choice1ThirdRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[3]//td[4]");
    public By choice2ThirdRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[3]//td[5]");
    //ряд4
    public By emailFourthRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[4]//td[1]");
    public By nameFourthRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[4]//td[2]");
    public By genderFourthRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[4]//td[3]");
    public By choice1FourthRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[4]//td[4]");
    public By choice2FourthRowLocator = By.xpath("//table[@id='dataTable']//tbody//tr[4]//td[5]");
    //Переменные
    public String expectedNameError = "Поле имя не может быть пустым";
    public String expectedEmailErrorText = "Неверный формат E-Mail";
    public String expectedPopupTextAfterAddingData = "Данные добавлены.";
    public String defaultGender = "Мужской";
    public String emptyChoice1 = "Нет";
    public String bothSelectedChoice1 = "1.1, 1.2";
    public String firstSelectedChoice1 = "1.1";
    public String secondSelectedChoice1 = "1.2";
    public String emptyChoice2 = "";
    public String selectedFirstChoice2 = "2.1";
    public String selectedSecondChoice2 = "2.2";
    public String selectedThirdChoice2 = "2.3";
    public String correctName = "Александр";
    public String correctFemaleName = "Mary";
    public String correctEmail = "test@protei.ru";
    public String correctEmailForFemale = "testFemale@protei.com";
    public String femaleGender = "Женский";
    public String maleGender = "Мужской";


    public QuestionnairePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public String getGender() {
        var gender = new Select(genderSelect);
        return gender.getFirstSelectedOption().getText();
    }

    public void setGender(int index) {
        var gender = new Select(genderSelect);
        gender.selectByIndex(index);
    }

    public void assertSelectedGenderInTable(String gender, By nameLocator) {
        Assert.assertEquals(
                "Не корректный пол в таблице",
                gender,
                driver.findElement(nameLocator).getText());
    }

    public void inputText(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public void waitPopupLocatorIsDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(popupLocator));
    }


    public void assertEmailFormatError() {
        Assert.assertEquals(
                "Не отобразилась ошибка \"Неверный формат E-Mail\"",
                expectedEmailErrorText,
                driver.findElement(emailFormatErrorLocator).getText());
    }

    public void assertNameError() {
        Assert.assertEquals(
                "Не отобразилась ошибка \"Поле имя не может быть пустым\"",
                expectedNameError,
                driver.findElement(emptyNameErrorLocator).getText());
    }

    public void assertEmailInTable(String email, By emailLocator) {
        Assert.assertEquals(
                "Не корректный email в таблице",
                email,
                driver.findElement(emailLocator).getText());
    }

    public void assertNameInTable(String name, By nameLocator) {
        Assert.assertEquals(
                "Не корректное имя в таблице",
                name,
                driver.findElement(nameLocator).getText());
    }

    public void assertGenderInTable(String gender, By genderLocator) {
        Assert.assertEquals(
                "Не корректный пол в таблице",
                gender,
                driver.findElement(genderLocator).getText());
    }

    public void assertChoice1InTable(String choice, By choiceLocator) {
        Assert.assertEquals(
                "Не корректный Выбор1 в таблице",
                choice,
                driver.findElement(choiceLocator).getText());
    }

    public void assertChoice2InTable(String choice, By choiceLocator) {
        Assert.assertEquals(
                "Не корректный Выбор2 в таблице",
                choice,
                driver.findElement(choiceLocator).getText());
    }

    public void clearEmailTextField() {
        driver.findElement(emailTextFieldLocatorOnQuestionnaire).clear();
    }

    public void clearNameTextField() {
        driver.findElement(nameTextFieldLocator).clear();
    }
}
