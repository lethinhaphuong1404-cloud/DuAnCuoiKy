# CybersoftMovieTesting

Dự án kiểm thử tự động website đặt vé xem phim Cybersoft bằng Selenium WebDriver, Java, TestNG và Maven.

## 1. Mục tiêu dự án

Dự án được xây dựng nhằm tự động hóa quá trình kiểm thử các chức năng cơ bản của website đặt vé xem phim [Cybersoft Movie](https://demo1.cybersoft.edu.vn/).

Các mục tiêu chính:

- Kiểm tra khả năng truy cập và hiển thị trang chủ.
- Kiểm tra hình ảnh poster phim trên trang chủ.
- Kiểm tra chức năng mua vé khi người dùng chưa chọn phim.
- Kiểm tra giao diện lựa chọn ghế.
- Kiểm tra chức năng xem lịch sử đặt vé.
- Tự động thực hiện các test case và ghi nhận kết quả kiểm thử.
- Hỗ trợ chụp ảnh màn hình khi kiểm thử thất bại và xuất báo cáo kiểm thử.

## 2. Công nghệ sử dụng

| Công nghệ | Mục đích |
|---|---|
| Java 21 | Ngôn ngữ lập trình |
| Selenium WebDriver 4.27.0 | Tự động hóa thao tác trên trình duyệt |
| TestNG 7.10.2 | Quản lý và thực thi test case |
| Maven | Quản lý thư viện và chạy dự án |
| WebDriverManager 5.9.3 | Quản lý WebDriver |
| Log4j2 2.24.3 | Ghi log quá trình kiểm thử |
| ExtentReports 5.1.2 | Tạo báo cáo kiểm thử HTML |
| Google Chrome | Trình duyệt thực hiện kiểm thử |
| Visual Studio Code | Môi trường phát triển |

## 3. Cấu trúc thư mục

```text
CybersoftMovieTesting/
│
├── src/
│   ├── main/
│   │
│   └── test/
│       ├── java/
│       │   └── vn/
│       │       └── cybersoft/
│       │           └── framework/
│       │               ├── config/
│       │               │   └── ConfigReader.java
│       │               │
│       │               ├── pages/
│       │               │   ├── BasePage.java
│       │               │   ├── HomePage.java
│       │               │   ├── SeatSelectionPage.java
│       │               │   └── ...
│       │               │
│       │               ├── tests/
│       │               │   ├── BaseTest.java
│       │               │   ├── HomePageTest.java
│       │               │   ├── MovieBookingValidationTest.java
│       │               │   ├── SeatSelectionTest.java
│       │               │   └── BookingHistoryTest.java
│       │               │
│       │               └── utils/
│       │                   └── ...
│       │
│       └── resources/
│           ├── config.properties
│           └── log4j2.xml
│
├── testng.xml
├── pom.xml
├── README.md
│
├── logs/
│   └── automation.log
│
└── test-output/
    └── ExtentReport.html
```

*Lưu ý: Cấu trúc trên mô tả các thư mục và file chính theo dự án; tên file thực tế trong thư mục `utils`, `pages` và vị trí báo cáo có thể thay đổi theo cấu hình hiện tại.*

## 4. Hướng dẫn cài đặt và chạy kiểm thử

### 4.1. Yêu cầu môi trường

Trước khi chạy dự án, cần chuẩn bị:

- JDK 21 trở lên.
- Apache Maven.
- Google Chrome.
- Visual Studio Code hoặc IDE Java tương đương.
- Kết nối Internet để truy cập website và tải các thành phần cần thiết.

Kiểm tra Java:

```bash
java -version
```

Kiểm tra Maven:

```bash
mvn -version
```

### 4.2. Tải mã nguồn

Clone dự án hoặc mở thư mục dự án trên máy tính:

```bash
git clone <repository-url>
```

Di chuyển vào thư mục dự án:

```bash
cd CybersoftMovieTesting
```

### 4.3. Cài đặt thư viện

Chạy lệnh:

```bash
mvn clean install -DskipTests
```

Maven sẽ tải các thư viện được khai báo trong `pom.xml`.

### 4.4. Chạy toàn bộ kiểm thử

Chạy kiểm thử với giao diện trình duyệt:

```bash
mvn clean test
```

Chạy kiểm thử ở chế độ Headless (không hiển thị cửa sổ trình duyệt):

```bash
mvn clean test -Dheadless=true
```

**Lưu ý:** Tham số `-Dheadless=true` cần được cấu hình trong `BaseTest.java` để WebDriver đọc thuộc tính `headless` và thêm tùy chọn `--headless` cho Chrome. Nếu chưa cấu hình, tham số này không tự động chuyển Chrome sang chế độ Headless.

### 4.5. Xem kết quả kiểm thử

Sau khi chạy Maven, kết quả được lưu trong thư mục báo cáo của dự án.

- Báo cáo TestNG: `target/surefire-reports/`
- File log: `logs/automation.log` (nếu cấu hình Log4j2 sử dụng đường dẫn này).
- Báo cáo ExtentReports: vị trí phụ thuộc vào cấu hình trong dự án.

## 5. Kết quả kiểm thử

Dự án hiện có các nhóm kiểm thử chính:

| Mã test | Nội dung kiểm thử | Trạng thái gần nhất |
|---|---|---|
| AUTO-001 | Mở trang chủ thành công | Thành công theo log |
| AUTO-002 | Kiểm tra hình ảnh poster | Thành công theo log |
| AUTO-003 | Không cho mua vé khi chưa chọn phim | Thất bại |
| AUTO-004 | Kiểm tra giao diện chọn ghế | Bỏ qua |
| AUTO-005 | Kiểm tra lịch sử đặt vé | Bỏ qua |

### Kết quả lần chạy gần nhất

Lệnh thực thi:

```bash
mvn clean test
```

Kết quả:

| Chỉ tiêu | Số lượng |
|---|---:|
| Tổng số test | 5 |
| Thành công | 2 |
| Thất bại | 1 |
| Bỏ qua | 2 |

**Nhận xét:**

- Hai test case kiểm tra trang chủ và hình ảnh poster đã ghi nhận log thành công.
- AUTO-003 chưa tìm thấy nút "MUA VÉ NGAY" theo selector hiện tại.
- AUTO-004 và AUTO-005 bị bỏ qua do chưa hoàn thiện cấu hình URL tương ứng.
- Cần tiếp tục xác minh selector và hoàn thiện dữ liệu cấu hình để nâng cao tỷ lệ kiểm thử thành công.

---
