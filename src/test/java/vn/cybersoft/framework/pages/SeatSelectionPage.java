
package vn.cybersoft.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeatSelectionPage extends BasePage {

    private final By body = By.tagName("body");

    public SeatSelectionPage(WebDriver driver) {
        super(driver);
    }

    public String getPageText() {
        return getText(body);
    }

    public boolean isSeatSelectionDisplayed() {
        String text = getPageText().toLowerCase();

        return text.contains("chọn ghế")
                || text.contains("đặt vé")
                || text.contains("ghế đang chọn")
                || text.contains("tổng tiền");
    }

    public boolean containsLoginRequiredMessage() {
        String text = getPageText().toLowerCase();

        return text.contains("bạn chưa đăng nhập");
    }

    /*
     * Chỉ sử dụng sau khi xác minh selector của nút đặt vé.
     */
    public void clickBookTicket(By verifiedBookButtonLocator) {
        click(verifiedBookButtonLocator);
    }

    /*
     * Chỉ sử dụng sau khi xác minh selector của nút xác nhận
     * trên hộp thoại đăng nhập.
     */
    public void clickDialogConfirm(By verifiedConfirmLocator) {
        click(verifiedConfirmLocator);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}