import Pages.AuthorizationPage;
import Pages.Page;
import org.junit.Test;

public class AuthorizationTests extends TestBase {
    @Test
    public void autorizationPage_emptyFieldsLogin_errorIsDisplayed() {
        //arrange
        var page = new AuthorizationPage(driver, wait);
        //act
        page.open();
        page.loginButton.click();
        //assert
        page.assertEmailErrorText();
    }

    @Test
    public void autorizationPage_emptyEmailField_errorIsDisplayed() {
        //arrange
        var page = new AuthorizationPage(driver, wait);
        //act
        page.open();
        page.inputCorrectPassword();
        page.loginButton.click();
        //assert
        page.assertEmailErrorText();
    }

    @Test
    public void autorizationPage_emptyPasswordField_errorIsDisplayed() {
        //arrange
        var page = new AuthorizationPage(driver, wait);
        var pageCommon = new Page(driver, wait);
        //act
        page.open();
        page.inputCorrectEmail();
        page.loginButton.click();
        //assert
        page.assertPasswordOrEmailErrorText();
    }

    @Test
    public void autorizationPage_incorrectFields_errorIsDisplayed() {
        //arrange
        var page = new AuthorizationPage(driver, wait);
        //act
        page.open();
        page.inputText(page.emailTextFieldLocator, page.incorrectLogin);
        page.inputText(page.passwordTextFieldLocator, page.incorrectPassword);
        page.loginButton.click();
        //assert
        page.assertPasswordOrEmailErrorText();
    }

    @Test
    public void autorizationPage_incorrectFormatEmail_errorIsDisplayed() {
        //arrange
        var page = new AuthorizationPage(driver, wait);
        //act
        page.open();
        page.inputText(page.emailTextFieldLocator, page.incorrectFormatEmail);
        page.inputCorrectPassword();
        page.loginButton.click();
        //assert
        page.assertPasswordOrEmailErrorText();
    }

    @Test
    public void autorizationPage_goToTheQuestionnairePage_questionnairePageIsDisplayed() {
        //arrange
        var page = new AuthorizationPage(driver, wait);
        //act
        page.open();
        page.loginButton.click();
        page.closeEmailError.click();
        page.waitEmailErrorIsInvisible();

        page.inputCorrectEmail();
        page.loginButton.click();
        page.closePasswordError.click();
        page.waitPasswordErrorIsInvisible();

        page.inputCorrectPassword();
        page.loginButton.click();
        //assert
        page.assertQuestionnaireFormIsDisplayed();
    }
}
