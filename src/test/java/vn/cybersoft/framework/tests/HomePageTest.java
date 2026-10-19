
package vn.cybersoft.framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import vn.cybersoft.framework.pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test(description = "AUTO-001 - Mở trang chủ thành công")
    public void verifyHomePageCanOpen() {
        HomePage homePage = new HomePage(driver);

        homePage.open();

        Assert.assertTrue(
            homePage.isHomePageLoaded(),
            "Trang chủ chưa hiển thị hình ảnh."
        );

        log.info("AUTO-001: Đã mở trang chủ.");
    }

    @Test(description = "AUTO-002 - Trang chủ có hình ảnh poster")
    public void verifyHomePageHasPosters() {
        HomePage homePage = new HomePage(driver);

        homePage.open();

        Assert.assertTrue(
                homePage.getVisiblePosterCount() > 0,
                "Không tìm thấy hình ảnh đủ lớn đang hiển thị.");

        log.info("AUTO-002: Đã tìm thấy hình ảnh hiển thị.");
    }
}