
package vn.cybersoft.framework.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import vn.cybersoft.framework.driver.DriverFactory;

public abstract class BaseTest {

    protected WebDriver driver;

    protected final Logger log =
            LogManager.getLogger(getClass());

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        log.info("Khởi tạo trình duyệt cho {}",
                getClass().getSimpleName());

        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Đóng trình duyệt cho {}",
                getClass().getSimpleName());

        DriverFactory.quitDriver();
        driver = null;
    }
}