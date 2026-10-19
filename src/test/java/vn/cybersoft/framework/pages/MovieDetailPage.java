
package vn.cybersoft.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MovieDetailPage extends BasePage {

    private final By body = By.tagName("body");

    private final By showtimeElements = By.xpath(
            "//*[self::button or self::a]"
            + "[contains(translate(normalize-space(.),"
            + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),"
            + "'suất')"
            + " or contains(translate(normalize-space(.),"
            + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),"
            + "'gio')"
            + " or contains(normalize-space(.),' : ')]");

    public MovieDetailPage(WebDriver driver) {
        super(driver);
    }

    public String getPageText() {
        return getText(body);
    }

    public boolean isMovieDetailDisplayed() {
        String text = getPageText().toLowerCase();

        return text.contains("thời lượng")
                || text.contains("khởi chiếu")
                || text.contains("đặt vé")
                || text.contains("suất chiếu");
    }

    public int getPotentialShowtimeCount() {
        int count = 0;

        List<WebElement> elements = findElements(showtimeElements);

        for (WebElement element : elements) {
            try {
                if (element.isDisplayed() && element.isEnabled()) {
                    count++;
                }
            } catch (Exception ignored) {
                // Phần tử có thể đã biến mất khỏi DOM
            }
        }

        return count;
    }

    public void clickShowtime(By verifiedShowtimeLocator) {
        click(verifiedShowtimeLocator);
    }
}