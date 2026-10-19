
package vn.cybersoft.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.PageLoadStrategy;

import java.time.Duration;

import vn.cybersoft.framework.config.ConfigReader;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void initDriver() {
        String browser = ConfigReader.get("browser", "chrome");
        boolean headless = ConfigReader.getBoolean("headless", false);

        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                if (headless) {
                    options.addArguments("--headless=new");
                }

                options.addArguments("--window-size=1440,1000");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");

                driver = new ChromeDriver(options);
            }

            case "edge" -> {
                WebDriverManager.edgedriver().setup();

                EdgeOptions options = new EdgeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                if (headless) {
                    options.addArguments("--headless=new");
                }

                options.addArguments("--window-size=1440,1000");
                driver = new EdgeDriver(options);
            }

            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions options = new FirefoxOptions();

                if (headless) {
                    options.addArguments("-headless");
                }

                driver = new FirefoxDriver(options);
            }

            default -> throw new IllegalArgumentException(
                    "Trình duyệt chưa được hỗ trợ: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ZERO);

        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(
                        ConfigReader.getInt("page.load.timeout", 30)));

        driver.manage().window().maximize();

        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();

        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver chưa được khởi tạo. "
                    + "Hãy gọi DriverFactory.initDriver() trước.");
        }

        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();

        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            DRIVER.remove();
        }
    }
}
