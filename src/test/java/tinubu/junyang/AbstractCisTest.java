package tinubu.junyang;

import com.google.common.collect.ImmutableMap;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.github.tahanima.factory.BasePageFactory;
import io.github.tahanima.ui.page.cisPage.BackLoginPage;
import io.github.tahanima.ui.page.demoPage.BasePage;
import io.github.tahanima.util.BrowserManager;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.nio.file.Paths;
import java.util.Optional;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static io.github.tahanima.config.ConfigurationManager.config;
import static org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * @author tahanima
 */
@TestInstance(Lifecycle.PER_CLASS)
public abstract class AbstractCisTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    protected BackLoginPage backLoginPage;

    @RegisterExtension
    AfterTestExecutionCallback callback =
            context -> {
                Optional<Throwable> exception = context.getExecutionException();

                if (exception.isPresent()) {
                    captureScreenshotOnFailure();
                }
            };

    protected abstract byte[] captureScreenshotOnFailure();

    protected <T extends BasePage> T createInstance(Class<T> basePage) {
        return BasePageFactory.createInstance(page, basePage);
    }

    @BeforeAll
    public void createPlaywrightAndBrowserInstancesAndSetupAllureEnvironment() {
        playwright = Playwright.create();
        browser = BrowserManager.getBrowser(playwright);

        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Platform", System.getProperty("os.name"))
                        .put("Version", System.getProperty("os.version"))
                        .put("Browser", StringUtils.capitalize(config().browser()))
                        .put("Context URL", config().gramsUrl())
                        .build(),
                config().allureResultsDir() + "/");
    }

    @BeforeEach
    public void createBrowserContextAndPageAndLoginPageInstances(TestInfo testInfo) {
        String testMethodName =
                (testInfo.getTestMethod().isPresent())
                        ? testInfo.getTestMethod().get().getName()
                        : "";

        if (config().video()) {
            browserContext =
                    browser.newContext(
                            new Browser.NewContextOptions()
                                    .setRecordVideoDir(
                                            Paths.get(
                                                    config().baseTestVideoPath()
                                                            + "/"
                                                            + testMethodName)));
        } else {
            browserContext = browser.newContext();
        }

        page = browserContext.newPage();
        backLoginPage = createInstance(BackLoginPage.class);
    }

    @AfterAll
    public void closeBrowserAndPlaywrightSessions() {
        browser.close();
        playwright.close();
    }
}
