
package vn.cybersoft.framework.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import vn.cybersoft.framework.driver.DriverFactory;
import vn.cybersoft.framework.report.ExtentManager;
import vn.cybersoft.framework.report.ExtentTestManager;
import vn.cybersoft.framework.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private static final Logger log =
            LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        log.info("Bắt đầu test suite: {}", context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        ExtentTest test = ExtentManager.getInstance()
                .createTest(testName);

        test.assignCategory(
                result.getTestClass().getRealClass().getSimpleName());

        ExtentTestManager.setTest(test);

        log.info("BẮT ĐẦU TEST: {}", testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        log.info("TEST PASSED: {}", testName);

        ExtentTest test = ExtentTestManager.getTest();

        if (test != null) {
            test.log(Status.PASS, "Test thực hiện thành công");
        }

        ExtentTestManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Throwable error = result.getThrowable();

        log.error("TEST FAILED: {}", testName, error);

        ExtentTest test = ExtentTestManager.getTest();

        if (test != null) {
            test.log(Status.FAIL, "Test thất bại");

            if (error != null) {
                test.log(Status.FAIL,
                        error.getClass().getSimpleName()
                        + ": " + error.getMessage());
            }
        }

        try {
            WebDriver driver = DriverFactory.getDriver();

            String screenshotPath =
                    ScreenshotUtils.capture(driver, testName);

            if (screenshotPath != null) {
                log.info("Đã lưu screenshot: {}", screenshotPath);

                if (test != null) {
                    test.addScreenCaptureFromPath(
                            screenshotPath,
                            "Ảnh chụp khi test thất bại");
                }
            } else {
                log.warn("Không thể chụp screenshot cho {}", testName);
            }

        } catch (Exception e) {
            log.warn("Không thể lấy WebDriver để chụp ảnh", e);
        }

        ExtentTestManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();

        log.warn("TEST SKIPPED: {}", testName);

        ExtentTest test = ExtentTestManager.getTest();

        if (test == null) {
            test = ExtentManager.getInstance()
                    .createTest(testName);
        }

        test.log(Status.SKIP, "Test bị bỏ qua");

        if (result.getThrowable() != null) {
            test.log(Status.SKIP,
                    result.getThrowable().getMessage());
        }

        ExtentTestManager.removeTest();
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Kết thúc test suite: {}", context.getName());
        ExtentManager.flushReport();
    }
}