
package vn.cybersoft.framework.tests;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import vn.cybersoft.framework.config.ConfigReader;
import vn.cybersoft.framework.pages.SeatSelectionPage;

public class SeatSelectionTest extends BaseTest {

    @Test(
        description = "AUTO-004 - Kiểm tra giao diện chọn ghế"
    )
    public void verifySeatSelectionPage() {
        String url = ConfigReader.get("seat.selection.url", "");

        if (url.isBlank()) {
            throw new SkipException(
                    "Chưa cấu hình seat.selection.url. "
                    + "Hãy mở trang chủ, chọn phim và suất chiếu, "
                    + "sau đó cấu hình URL chọn ghế đã xác minh.");
        }

        driver.get(url);

        SeatSelectionPage seatPage =
                new SeatSelectionPage(driver);

        Assert.assertTrue(
                seatPage.isSeatSelectionDisplayed(),
                "Không phát hiện nội dung đặc trưng "
                + "của giao diện chọn ghế.");

        log.info("AUTO-004: Giao diện chọn ghế đã được kiểm tra.");
    }
}