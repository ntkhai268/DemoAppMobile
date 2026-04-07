# Demo WebView

Bài demo này xây dựng một ứng dụng giống như một trình duyệt web mini thu nhỏ, sử dụng component `WebView` trong Android để tải và hiển thị trang web trực tiếp trên màn hình.

## Cấu trúc Giao diện (UI)

* Layout chính được sử dụng là `LinearLayout` với chiều hiển thị dọc (`android:orientation="vertical"`).
* Các thành phần chạy từ trên xuống dưới theo thứ tự bao gồm:
  1. **EditText:** Thanh nhập địa chỉ URL (Ví dụ: `google.com`).
  2. **LinearLayout (Chiều ngang):** Đóng vai trò làm thanh công cụ điều hướng, bọc các Button điều khiển bên trong gồm: *Khởi hành (Go/Load)*, *Quay lại (Back)*, *Tiến tới (Forward)*, và *Tải lại (Reload)*.
  3. **WebView:** Component trọng tâm, chiếm phần lớn không gian màn hình ở phía dưới cùng để phục vụ render và hiển thị nội dung web.

## Luồng hoạt động

1. Người dùng tiến hành nhập địa chỉ trang web muốn truy cập vào trong khu vực `EditText`.
2. Khi người dùng click chọn Button để truy cập, hệ thống trích xuất văn bản từ EditText. Đoạn URL này có thể được *tiền xử lý* (tự động gắn thêm tiền tố `https://` nếu người dùng lỡ nhập thiếu) nhằm đảm bảo hệ thống hiểu đúng cấu trúc. Sau đó gọi hàm `webView.loadUrl(url)` để load trang web đó.
3. Khi load trang, hệ thống âm thầm lưu URL vào một lịch sử nội bộ chuyên nghiệp. Việc này dọn đường hỗ trợ cho các tính năng Forward (Tiến tới) hoặc Back (Quay lui) về sau.

## Cơ chế lịch sử hoạt động (Back & Forward) - Đọc thêm thôi

Nhiều người nghĩ hệ thống Lịch sử là một Ngăn Xếp (Stack) tĩnh. Nhưng trên thực tế trong Android, nó lưu trữ theo dưới dạng `WebBackForwardList` - tương đương một **Danh sách/Mảng (List) kèm theo một Con trỏ vị trí (Index Pointer)**:

* Khi bạn lướt từ A `->` B `->` C, danh sách lưu là `[A, B, C]` và con trỏ tịnh tiến nằm ở vị trí trang hiển tại là `C`.
* **Quay lại (Back):** Check điều kiện bằng `webView.canGoBack()`. Nếu thỏa mãn, `webView.goBack()` được kích hoạt, con trỏ lặng lẽ di chuyển lùi xuống vị trí `B`. Note: Bước này không hoàn toàn xóa (pop/destroy) trang `C` khỏi bộ nhớ.
* **Tiến tới (Forward):** Nhờ cơ chế không bị xóa sạch phía trên, check `webView.canGoForward()` sẽ hợp lệ. Lúc này `webView.goForward()` điều hướng con trỏ tiến lên và tải lại trang `C` dễ dàng.
* **Đặc tính ghi đè (Hoạt động như Stack):** Giả sử bạn Back về trang `B`, và không ấn Forward mà tự nhập một URL mới toanh `D`. Lúc này, WebView sẽ ứng xử giống như Stack, tiêu hủy mọi lịch sử ngã rẽ cũ về phía trước (Xóa trang `C`), sau đó ghi đè thành con đường tịnh tiến mới `[A, B, D]`.

## Lưu ý Cài đặt nền tảng

Trong quá trình thực tế cài đặt Webview, nhằm tránh hiện tượng báo lỗi hoặc trang giấy trắng, lập trình viên cần lưu ý 2 thứ bắt buộc:

1. App luôn phải được cung cấp quyền mở cửa giao tiếp mạng thông qua thẻ `<uses-permission android:name="android.permission.INTERNET" />` nơi `AndroidManifest.xml`.
2. Bản thân các UI/UX siêu việt hay Web hiện đại đều cần chạy bộ xử lý **JavaScript**. Vậy nên cần kích hoạt `webView.getSettings().setJavaScriptEnabled(true);` từ phía backend Code Java để trang render thành công.
