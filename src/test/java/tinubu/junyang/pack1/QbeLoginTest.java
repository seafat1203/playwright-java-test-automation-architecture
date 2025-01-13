package tinubu.junyang.pack1;

import io.github.tahanima.annotation.Validation;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import tinubu.junyang.AbstractCisTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
@Feature("QBE Login Test")
public class QbeLoginTest extends AbstractCisTest {
    @Attachment(value = "Failed Test Case Screenshot", type = "image/png")
    protected byte[] captureScreenshotOnFailure() {
        return backLoginPage.captureScreenshot();    }


    @AfterEach
    public void closeBrowserContextSession() {
        browserContext.close();
    }


    @Test
    @Validation
    @Description("Wrong login test")
    void wrongLoginTest() {
        backLoginPage.open()
                .typeLogin("")
                .typePassword("")
                .clickOnLoginBtnButFail();
        assertThat(backLoginPage.errorMessage()).hasText("Invalid username or password.");
    }

    @Test
    @Validation
    @Description("Success login test")
    void successLoginTest() {
        backLoginPage.open()
                .typeLogin("admin")
                .typePassword("dev")
                .clickOnLoginBtn();
        assertThat(backLoginPage.errorMessage()).hasText("Invalid username or password.");
    }


}
