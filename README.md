# DemoImageView
Đây là một project Android demo đơn giản nhằm hướng dẫn cách thao tác và tùy chỉnh các kiểu hiển thị hình ảnh (`ScaleType`) đối với thành phần cơ bản: `ImageView`, `Button` và `TextView`.

### Cấu trúc Giao diện (UI)
* **Layout chính:** Sử dụng `LinearLayout` với thuộc tính `android:orientation="horizontal"` và `android:layout_weight` để chia tỷ lệ màn hình thành 2 phần bằng nhau (trái và phải).
* **Phần bên trái (LinearLayout dọc):**
  * **TextView:** Nơi hiển thị tên của kiểu ScaleType hiện đang được áp dụng, giúp người dùng dễ nhận biết.
  * **Các Button:** Tập hợp 7 nút nhấn (Center, centerCrop, centerInside, fitCenter, fitEnd, fitStart, fitXY) tương ứng với các kiểu ScaleType.
* **Phần bên phải:**
  * **ImageView:** Thành phần chính để hiển thị hình ảnh (`@drawable/vitdonnald`), nó sẽ tự động thay đổi cách hiển thị dựa trên kích thước và tỷ lệ khi người dùng ấn vào các nút bên trái.

### Luồng Hoạt động (Logic)
* **Khởi tạo và ánh xạ view:** Ánh xạ các view từ file XML sang code Java bằng phương thức `findViewById(R.id...)`. Đối với các nút, sử dụng một mảng chứa ID của chúng (`listButtonID`) và vòng lặp `for` để ánh xạ nhanh gọn.
* **Gắn sự kiện:** Lớp `MainActivity` đã `implements View.OnClickListener` nên có thể gắn sự kiện lắng nghe tương tác cho tất cả các nút thông qua `btnTemp.setOnClickListener(this)`.
* **Khi người dùng bấm Button, hàm `onClick` sẽ được gọi:**
  * Lấy ID của nút được nhấn thông qua `view.getId()`.
  * Dùng khối lệnh `if - else if` để kiểm tra và đối chiếu ID của nút đó.
  * Tùy thuộc vào nút được bấm, gọi hàm `imageView2.setScaleType(...)` với tham số tương ứng (ví dụ: `ImageView.ScaleType.CENTER_CROP`, `ImageView.ScaleType.FIT_XY`,...) để thay đổi kiểu hiển thị của hình ảnh.
  * Dùng `textView.setText(...)` để cập nhật tên của kiểu ScaleType đó lên màn hình.

### Chi tiết các tham số ScaleType
* **`CENTER`**: Đặt hình ảnh ở chính giữa ImageView nhưng tuyệt đối không thu phóng kích thước gốc của hình. Nếu hình lớn hơn ImageView thì các viền ngoài sẽ bị cắt bớt.
* **`CENTER_CROP`**: Thu phóng hình ảnh (vẫn giữ nguyên tỷ lệ) sao cho lấp đầy hoàn toàn diện tích của ImageView. Phần hình bị thừa ra ngoài (nếu có) sẽ bị cắt đi. (Thường được sử dụng nhiều nhất).
* **`CENTER_INSIDE`**: Đặt hình ảnh ở chính giữa. Nếu hình lớn hơn ImageView thì sẽ bị thu nhỏ lại (giữ nguyên tỷ lệ) để vừa gọn trong view. Nếu hình nhỏ hơn thì giữ nguyên gốc, không phóng to lên.
* **`FIT_CENTER`**: Thu phóng hình ảnh (giữ nguyên tỷ lệ) sao cho nó lớn nhất có thể mà toàn bộ bức hình vẫn nằm gọn trong mặt hiển thị của ImageView. Sau đó, hình được căn vào chính giữa.
* **`FIT_END`**: Tương tự như `FIT_CENTER` (có thu phóng giữ nguyên tỷ lệ nằm gọn trong view), nhưng bức hình được kéo sát về phía cuối (gọ́c dưới hoặc góc phải) của ImageView.
* **`FIT_START`**: Tương tự như `FIT_CENTER`, nhưng bức hình được đẩy sát về phía đầu (góc trên hoặc góc trái) của ImageView.
* **`FIT_XY`**: Kéo dãn hoặc thu nhỏ chiều rộng và chiều cao của hình ảnh một cách độc lập sao cho lấp đầy khít toàn bộ ImageView. (Lưu ý: Ảnh có thể bị méo đi do không giữ tỷ lệ gốc của ảnh).
