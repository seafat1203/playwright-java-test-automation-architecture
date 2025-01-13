package io.github.tahanima.e2e;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

import static io.github.tahanima.config.ConfigurationManager.config;

import static org.junit.jupiter.api.TestInstance.*;

import com.google.common.collect.ImmutableMap;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.github.tahanima.factory.BasePageFactory;
import io.github.tahanima.ui.page.cisPage.BackLoginPage;
import io.github.tahanima.ui.page.demoPage.BasePage;
import io.github.tahanima.ui.page.demoPage.LoginPage;
import io.github.tahanima.util.BrowserManager;

import io.github.tahanima.util.ScreenshotUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * @author tahanima
 */
@TestInstance(Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    protected LoginPage loginPage;
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
                        .put("Context URL", config().baseUrl())
                        .build(),
                config().allureResultsDir() + "/");
    }

    @AfterAll
    public void closeBrowserAndPlaywrightSessions() {
        try {
            // 确保在关闭浏览器之前截取屏幕截图
            if (page != null) {
                // 调用 ScreenshotUtil 工具类进行截图
                ScreenshotUtil.captureAndSaveScreenshot(page, this.getClass().getSimpleName());
            }
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        } finally {
            // 确保即使截屏失败也能正常关闭浏览器和Playwright
            if (browser != null) {
                browser.close();
            }
            if (playwright != null) {
                playwright.close();
            }
        }
    }
}
