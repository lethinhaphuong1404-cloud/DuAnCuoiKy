
package vn.cybersoft.framework.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import vn.cybersoft.framework.config.ConfigReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            try {
                String reportDirectory = ConfigReader.get(
                        "report.path", "target/extent-report");

                Files.createDirectories(Path.of(reportDirectory));

                String reportFile = Path.of(
                        reportDirectory, "index.html").toString();

                ExtentSparkReporter spark =
                        new ExtentSparkReporter(reportFile);

                spark.config().setDocumentTitle(
                        "Cybersoft Movie Testing Report");

                spark.config().setReportName(
                        "Báo cáo kiểm thử tự động");

                spark.config().setTheme(Theme.STANDARD);

                extent = new ExtentReports();
                extent.attachReporter(spark);

                extent.setSystemInfo("Project",
                        "Cybersoft Movie Booking");

                extent.setSystemInfo("Framework",
                        "Selenium + Java + TestNG");

                extent.setSystemInfo("Design Pattern",
                        "Page Object Model");

                extent.setSystemInfo("Browser",
                        ConfigReader.get("browser", "chrome"));

                extent.setSystemInfo("Base URL",
                        ConfigReader.get("base.url",
                                "https://demo1.cybersoft.edu.vn/"));

            } catch (IOException e) {
                throw new IllegalStateException(
                        "Không thể tạo thư mục báo cáo", e);
            }
        }

        return extent;
    }

    public static synchronized void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}