import Pages.QuestionnairePage;
import org.junit.Test;

public class QuestionnaireTests extends TestBase {
    @Test
    public void QuestionnairePage_emptyFields_errorIsDisplayed() {
        //arrange
        QuestionnairePage page = new QuestionnairePage(driver, wait);
        page.open();
        page.autorization();
        //act
        page.addButton.click();
        //assert
        page.assertEmailFormatError();
    }

    @Test
    public void QuestionnairePage_emptyName_errorIsDisplayed() {
        //arrange
        QuestionnairePage page = new QuestionnairePage(driver, wait);
        page.open();
        page.autorization();
        //act
        page.inputText(page.emailTextFieldLocatorOnQuestionnaire, page.correctEmail);
        page.addButton.click();
        //assert
        page.assertNameError();
    }

    @Test
    public void QuestionnairePage_inputEmailName_checkResult() {
        //arrange
        QuestionnairePage page = new QuestionnairePage(driver, wait);
        page.open();
        page.autorization();
        //act
        page.inputText(page.emailTextFieldLocatorOnQuestionnaire, page.correctEmail);
        page.inputText(page.nameTextFieldLocator, page.correctName);
        page.addButton.click();
        page.waitPopupLocatorIsDisplayed();
        page.popupButton.click();
        //assert
        page.assertEmailInTable(page.correctEmail, page.emailFirstRowLocator);
        page.assertNameInTable(page.correctName, page.nameFirstRowLocator);
        page.assertGenderInTable(page.defaultGender, page.genderFirstRowLocator);
        page.assertChoice1InTable(page.emptyChoice1, page.choice1FirstRowLocator);
        page.assertChoice2InTable(page.emptyChoice2, page.choice2FirstRowLocator);
    }

    @Test
    public void QuestionnairePage_inputEmailNameFemaleGender_checkResult() {
        //arrange
        QuestionnairePage page = new QuestionnairePage(driver, wait);
        page.open();
        page.autorization();
        //act
        page.inputText(page.emailTextFieldLocatorOnQuestionnaire, page.correctEmail);
        page.inputText(page.nameTextFieldLocator, page.correctFemaleName);
        page.setGender(1);
        page.addButton.click();
        page.waitPopupLocatorIsDisplayed();
        page.popupButton.click();
        //assert
        page.assertEmailInTable(page.correctEmail, page.emailFirstRowLocator);
        page.assertNameInTable(page.correctFemaleName, page.nameFirstRowLocator);
        page.assertSelectedGenderInTable(page.femaleGender, page.genderFirstRowLocator);
        page.assertChoice1InTable(page.emptyChoice1, page.choice1FirstRowLocator);
        page.assertChoice2InTable(page.emptyChoice2, page.choice2FirstRowLocator);
    }

    @Test
    public void QuestionnairePage_inputEmailNameMaleGenderCheckBoxesSelectRadioButtonOne_checkResult() {
        //arrange
        QuestionnairePage page = new QuestionnairePage(driver, wait);
        page.open();
        page.autorization();
        //act
        page.inputText(page.emailTextFieldLocatorOnQuestionnaire, page.correctEmail);
        page.inputText(page.nameTextFieldLocator, page.correctName);
        page.setGender(0);
        page.checkBoxChoiceOne.click();
        page.checkBoxChoiceTwo.click();
        page.radioButtonChoiceOne.click();
        page.addButton.click();
        page.waitPopupLocatorIsDisplayed();
        page.popupButton.click();
        //assert
        page.assertEmailInTable(page.correctEmail, page.emailFirstRowLocator);
        page.assertNameInTable(page.correctName, page.nameFirstRowLocator);
        page.assertSelectedGenderInTable(page.maleGender, page.genderFirstRowLocator);
        page.assertChoice1InTable(page.bothSelectedChoice1, page.choice1FirstRowLocator);
        page.assertChoice2InTable(page.selectedFirstChoice2, page.choice2FirstRowLocator);
    }

    @Test
    public void QuestionnairePage_inputEmailNameMaleGenderFirstCheckBoxRadioButtonTwo_checkResult() {
        //arrange
        QuestionnairePage page = new QuestionnairePage(driver, wait);
        page.open();
        page.autorization();
        //act
        page.inputText(page.emailTextFieldLocatorOnQuestionnaire, page.correctEmail);
        page.inputText(page.nameTextFieldLocator, page.correctName);
        page.setGender(0);
        page.checkBoxChoiceOne.click();
        page.radioButtonChoiceTwo.click();
        page.addButton.click();
        page.waitPopupLocatorIsDisplayed();
        page.popupButton.click();
        //assert
        page.assertEmailInTable(page.correctEmail, page.emailFirstRowLocator);
        page.assertNameInTable(page.correctName, page.nameFirstRowLocator);
        page.assertSelectedGenderInTable(page.maleGender, page.genderFirstRowLocator);
        page.assertChoice1InTable(page.firstSelectedChoice1, page.choice1FirstRowLocator);
        page.assertChoice2InTable(page.selectedSecondChoice2, page.choice2FirstRowLocator);
    }

    @Test
    public void QuestionnairePage_severalRowsAddToTable_checkResult() {
        //arrange
        QuestionnairePage page = new QuestionnairePage(driver, wait);
        page.open();
        page.autorization();
        //act
        //Ввод строки1: email1 + name1 + мужчина + чекбокс1 + радиобаттон3
        page.inputText(page.emailTextFieldLocatorOnQuestionnaire, page.correctEmail);
        page.inputText(page.nameTextFieldLocator, page.correctName);
        page.setGender(0);
        page.checkBoxChoiceOne.click();
        page.radioButtonChoiceThree.click();
        page.addButton.click();
        page.waitPopupLocatorIsDisplayed();
        page.popupButton.click();
        //Ввод строки2: Изменение email +Изменение имени + изменение пола + добавление чекбокса2 + изменение радиобаттон
        page.clearEmailTextField();
        page.inputText(page.emailTextFieldLocatorOnQuestionnaire, page.correctEmailForFemale);
        page.clearNameTextField();
        page.inputText(page.nameTextFieldLocator, page.correctFemaleName);
        page.setGender(1);
        page.checkBoxChoiceTwo.click();
        page.radioButtonChoiceTwo.click();
        page.addButton.click();
        page.waitPopupLocatorIsDisplayed();
        page.popupButton.click();
        //Ввод строки3: email тот же + имя то же + изменение пола + удаление чекбокса1 + изменение радиобаттон
        page.setGender(0);
        page.checkBoxChoiceOne.click();
        page.radioButtonChoiceOne.click();
        page.addButton.click();
        page.waitPopupLocatorIsDisplayed();
        page.popupButton.click();
        //Ввод строки4: email тот же + имя то же + пол тот же + удаление чекбокса2 + радиобаттон тот же
        page.checkBoxChoiceTwo.click();
        page.addButton.click();
        page.waitPopupLocatorIsDisplayed();
        page.popupButton.click();
        //assert строка1
        page.assertEmailInTable(page.correctEmail, page.emailFirstRowLocator);
        page.assertNameInTable(page.correctName, page.nameFirstRowLocator);
        page.assertSelectedGenderInTable(page.maleGender, page.genderFirstRowLocator);
        page.assertChoice1InTable(page.firstSelectedChoice1, page.choice1FirstRowLocator);
        page.assertChoice2InTable(page.selectedThirdChoice2, page.choice2FirstRowLocator);
        //assert строка2
        page.assertEmailInTable(page.correctEmailForFemale, page.emailSecondRowLocator);
        page.assertNameInTable(page.correctFemaleName, page.nameSecondRowLocator);
        page.assertSelectedGenderInTable(page.femaleGender, page.genderSecondRowLocator);
        page.assertChoice1InTable(page.bothSelectedChoice1, page.choice1SecondRowLocator);
        page.assertChoice2InTable(page.selectedSecondChoice2, page.choice2SecondRowLocator);
        //assert строка3
        page.assertEmailInTable(page.correctEmailForFemale, page.emailThirdRowLocator);
        page.assertNameInTable(page.correctFemaleName, page.nameThirdRowLocator);
        page.assertSelectedGenderInTable(page.maleGender, page.genderThirdRowLocator);
        page.assertChoice1InTable(page.secondSelectedChoice1, page.choice1ThirdRowLocator);
        page.assertChoice2InTable(page.selectedFirstChoice2, page.choice2ThirdRowLocator);
        //assert строка4
        page.assertEmailInTable(page.correctEmailForFemale, page.emailFourthRowLocator);
        page.assertNameInTable(page.correctFemaleName, page.nameFourthRowLocator);
        page.assertSelectedGenderInTable(page.maleGender, page.genderFourthRowLocator);
        page.assertChoice1InTable(page.emptyChoice1, page.choice1FourthRowLocator);
        page.assertChoice2InTable(page.selectedFirstChoice2, page.choice2FourthRowLocator);
    }
}
