
package vn.cybersoft.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingHistoryPage extends BasePage {

    private final By body = By.tagName("body");

    public BookingHistoryPage(WebDriver driver) {
        super(driver);
    }

    public String getPageText() {
        return getText(body);
    }

    public boolean isHistoryPageDisplayed() {
        String text = getPageText().toLowerCase();

        return text.contains("lịch sử")
                || text.contains("ngày đặt");
    }

    public boolean containsBookingHistoryFields() {
        String text = getPageText().toLowerCase();

        boolean hasDate = text.contains("ngày đặt");
        boolean hasMovie = text.contains("phút");
        boolean hasPrice = text.contains("vnd");
        boolean hasCinema = text.contains("rạp");

        return hasDate && hasMovie && hasPrice && hasCinema;
    }

    public boolean containsMovieName(String movieName) {
        return getPageText()
                .toLowerCase()
                .contains(movieName.toLowerCase());
    }

    public boolean containsSeatNumbers(String seatNumbers) {
        return getPageText().contains(seatNumbers);
    }
}