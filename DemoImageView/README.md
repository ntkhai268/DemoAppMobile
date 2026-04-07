# Demo Image Button

Dự án này là một bài thực hành Android cơ bản giúp tìm hiểu về cách xây dựng giao diện và xử lý sự kiện tương tác của người dùng để thay đổi hình ảnh động.

## Cấu trúc giao diện (UI)

* **Layout chính (`activity_main.xml`)**: Cấu trúc UI được tổ chức bởi một `LinearLayout` (hướng `vertical`) làm khung bao ngoài lớn nhất.
* **Các thành phần con**:
  * Một `LinearLayout` khác (hướng `horizontal`) được đưa vào nằm trên cùng, chứa hai nút bấm điều khiển (Button Facebook và Button Twitter).
  * Một `ImageView` được sắp xếp ở nửa dưới để hiển thị hình ảnh (`resource`).

## Logic hoạt động (`MainActivity.java`)

1. **Gắn sự kiện**: Chức năng được kích hoạt dựa trên thuộc tính `android:onClick` trên các Button.
2. **Nút Facebook**: Khi được bấm, sự kiện gọi hàm `showFacebook(View view)`. Hàm tiến hành gọi `imageView.setImageResource(R.drawable.facebook)` để cập nhật hình ảnh trên `ImageView` thành hình ảnh Facebook.
3. **Nút Twitter**: Tương tự, khi bấm nút Twitter, hàm `showTwitter(View view)` được gọi và thay đổi hiển thị thành ảnh Twitter (`R.drawable.twitter`).

Bài tập biểu diễn rõ nét nguyên lý tương tác sự kiện và kiểm soát view trong Android.
