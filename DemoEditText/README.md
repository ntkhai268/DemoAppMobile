# DemoEditText

Đây là một project Android demo đơn giản nhằm hướng dẫn cách thao tác lấy và hiển thị dữ liệu văn bản với các thành phần cơ bản: `EditText`, `Button` và `TextView`.

## Cấu trúc Giao diện (UI)

- **Layout chính**: Sử dụng `LinearLayout` với thuộc tính `android:orientation="vertical"` để sắp xếp các view hiển thị dọc từ trên xuống dưới.
- **EditText**: Cho phép người dùng nhập tên. Có sử dụng padding, hint (`@string/enter_your_name`) và một icon phía trước đoạn text thông qua thuộc tính `android:drawableStart="@drawable/user_24"`.
- **Button**: Nút căn giữa (nhờ `android:layout_gravity="center"`).
- **TextView**: Nơi hiển thị kết quả sau khi người dùng ấn nút.

## Luồng Hoạt động (Logic)

1. Khởi tạo và ánh xạ các view từ file XML sang code Java bằng phương thức `findViewById(R.id...)`.
2. Gắn sự kiện lắng nghe người dùng tương tác với nút thông qua `button.setOnClickListener(...)`.
3. Khi người dùng bấm **Button**, hàm `onClick` sẽ được gọi:
   - Dùng `editText.getText().toString()` để lấy chuỗi văn bản (tên) mà người dùng vừa nhập.
   - Thêm tiền tố `"Welcome "` vào trước chuỗi văn bản đó.
   - Dùng `textView.setText(...)` để cập nhật hiển thị ra giao diện kết quả cuối cùng.
