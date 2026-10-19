
package vn.cybersoft.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import vn.cybersoft.framework.config.ConfigReader;

import java.util.List;

public class HomePage extends BasePage {

    /*
     * Các selector bên dưới là selector dự phòng.
     * Cần xác minh lại bằng Inspect trên website thực tế.
     */
    private final By movieCards = By.cssSelector(
            "[class*='movie'], [class*='Movie'], "
            + "[class*='film'], [class*='Film']");

    private final By buyTicketButtons = By.xpath(
            "//button[contains(normalize-space(.),'MUA VÉ NGAY')]"
            + " | //a[contains(normalize-space(.),'MUA VÉ NGAY')]"
            + " | //button[contains(normalize-space(.),'Mua vé')]"
            + " | //a[contains(normalize-space(.),'Mua vé')]");

    private final By posterImages = By.cssSelector("img");

    private final By genericAlert = By.cssSelector(
            "[role='alert'], .swal2-popup, .ant-message-notice-content");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(ConfigReader.get(
                "base.url", "https://demo1.cybersoft.edu.vn/"));
        return this;
    }

    public boolean isHomePageLoaded() {
        return waitVisible(posterImages).isDisplayed();
    }

    public int getVisiblePosterCount() {
        int count = 0;

        for (WebElement image : findElements(posterImages)) {
            try {
                if (image.isDisplayed()
                        && image.getSize().getWidth() > 50
                        && image.getSize().getHeight() > 50) {
                    count++;
                }
            } catch (Exception ignored) {
                // Bỏ qua ảnh đã bị thay đổi hoặc không còn hiển thị
            }
        }

        return count;
    }

    public int getMovieCardCount() {
        return findElements(movieCards).size();
    }

   public boolean hasBuyTicketButton() {
        By buyTicketCandidates = By.xpath(
            "//*[self::button or self::a or self::span "
            + "or self::div or self::p]"
            + "[contains(translate(normalize-space(.),"
            + "'abcdefghijklmnopqrstuvwxyz',"
            + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'MUA VÉ')]"
        );

        for (WebElement element : findElements(buyTicketCandidates)) {
            try {
                if (element.isDisplayed()
                        && !element.getText().trim().isEmpty()) {
                    return true;
                }
            } catch (Exception ignored) {
                // Bỏ qua phần tử đã thay đổi hoặc không còn hiển thị.
            }
        }

        return false;
    }
    public void clickBuyTicketWithoutSelectingMovie() {
        click(buyTicketButtons);
    }

    public boolean isMovieNotSelectedMessageDisplayed() {
        String expected = "Bạn chưa chọn phim vui lòng chọn phim";

        try {
            return wait.until(d -> {
                String pageText = d.findElement(By.tagName("body"))
                        .getText();

                boolean hasExpectedText =
                        pageText.toLowerCase()
                                .contains(expected.toLowerCase());

                boolean hasAlert = false;

                for (WebElement element : findElements(genericAlert)) {
                    if (element.isDisplayed()
                            && element.getText().contains(
                                    "Bạn chưa chọn phim")) {
                        hasAlert = true;
                        break;
                    }
                }

                return hasExpectedText || hasAlert;
            });
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getPosterImages() {
        return findElements(posterImages);
    }
}