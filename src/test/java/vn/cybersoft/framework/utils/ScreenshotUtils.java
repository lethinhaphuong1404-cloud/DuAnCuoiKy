
package vn.cybersoft.framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import vn.cybersoft.framework.config.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private ScreenshotUtils() {
    }

    public static String capture(WebDriver driver, String testName) {
        if (driver == null) {
            return null;
        }

        try {
            String directory = ConfigReader.get(
                    "screenshot.path", "target/screenshots");

            Path folder = Path.of(directory);
            Files.createDirectories(folder);

            String safeName = testName.replaceAll(
                    "[^a-zA-Z0-9._-]", "_");

            String fileName = safeName + "_"
                    + LocalDateTime.now().format(FORMATTER)
                    + ".png";

            Path destination = folder.resolve(fileName);

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Files.copy(
                    source.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING);

            return destination.toAbsolutePath().toString();

        } catch (IOException | RuntimeException e) {
            return null;
        }
    }
}
