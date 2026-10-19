
package vn.cybersoft.framework.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import vn.cybersoft.framework.config.ConfigReader;
import vn.cybersoft.framework.pages.BookingHistoryPage;

public class BookingHistoryTest extends BaseTest {

    @Test(
        description = "AUTO-005 - Kiểm tra giao diện lịch sử đặt vé"
    )
    public void verifyBookingHistoryPage() {
        String url = ConfigReader.get("booking.history.url", "");

        if (url.isBlank()) {
            throw new SkipException(
                    "Chưa cấu hình booking.history.url. "
                    + "Cần đăng nhập bằng tài khoản kiểm thử "
                    + "được cho phép và xác minh URL lịch sử.");
        }

        driver.get(url);

        BookingHistoryPage historyPage =
                new BookingHistoryPage(driver);

        Assert.assertTrue(
                historyPage.isHistoryPageDisplayed(),
                "Không phát hiện giao diện lịch sử đặt vé.");

        log.info("AUTO-005: Đã kiểm tra trang lịch sử đặt vé.");
    }
}