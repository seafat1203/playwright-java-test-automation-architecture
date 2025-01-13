package io.github.tahanima.util;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
  private static final String SCREENSHOT_DIR = "build/screenshots/";

  /**
   * Captures a screenshot of the given page and saves it to a file.
   *
   * @param page      the Playwright Page object to capture a screenshot of.
   * @param className the name of the class (used in the filename).
   * @return the path where the screenshot is saved.
   * @throws IOException if there is an error saving the screenshot.
   */
  @Step("-Take a screenshot")
  public static String captureAndSaveScreenshot(Page page, String className) throws IOException {
    if (page == null) {
      throw new IllegalArgumentException("Page object cannot be null.");
    }

    // Generate a timestamp-based filename
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String screenshotPath = String.format("%s%s_%s.png", SCREENSHOT_DIR, className, timestamp);

    // Ensure the directory exists
    Path path = Paths.get(screenshotPath);
    Files.createDirectories(path.getParent());

    // Capture and save the screenshot
    byte[] screenshot = page.screenshot();
    Files.write(path, screenshot);

    System.out.println("Screenshot saved at: " + screenshotPath);
    return screenshotPath;
  }
}
