
package vn.cybersoft.framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import vn.cybersoft.framework.pages.HomePage;

public class MovieBookingValidationTest extends BaseTest {

    @Test(
        description = "AUTO-003 - Không cho mua vé khi chưa chọn phim"
    )
    public void verifyBuyTicketRequiresMovieSelection() {
        HomePage homePage = new HomePage(driver);

        homePage.open();

        Assert.assertTrue(
                homePage.hasBuyTicketButton(),
                "Không tìm thấy nút MUA VÉ NGAY.");

        homePage.clickBuyTicketWithoutSelectingMovie();

        Assert.assertTrue(
                homePage.isMovieNotSelectedMessageDisplayed(),
                "Website không hiển thị thông báo "
                + "'Bạn chưa chọn phim vui lòng chọn phim'.");

        log.info("AUTO-003: Đã kiểm tra thông báo chưa chọn phim.");
    }
}