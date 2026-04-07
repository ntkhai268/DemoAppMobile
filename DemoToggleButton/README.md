# Demo ToggleButton trong Android

Đây là một dự án nhỏ gọn nhằm mục đích demo cách sử dụng `ToggleButton` trong Android.

## ToggleButton là gì?

`ToggleButton` về cơ bản giống như một Button thông thường, nhưng nó mang trong nó đặc tính của một công tắc. Nó lưu trữ hai trạng thái: **Bật (Checked = True)** và **Tắt (Checked = False)**.

## Logic hoạt động của Demo

ứng dụng này minh họa cách lắng nghe sự thay đổi trạng thái của `ToggleButton` và phản hồi lại bằng cách thay đổi giao diện (cụ thể là màu nền):

1.  **Bắt sự kiện chuyển trạng thái:** Sử dụng phương thức `setOnCheckedChangeListener` để theo dõi mỗi khi người dùng thao tác tương tác với nút.
2.  **Xử lý Logic (`onCheckedChanged`):**
    *   Khi nút ở trạng thái **Bật (`isChecked` = true)**: Đổi màu nền của màn hình (LinearLayout) sang màu **Xanh lá (Green)**.
    *   Khi nút ở trạng thái **Tắt (`isChecked` = false)**: Đổi màu nền của màn hình sang màu **Đỏ (Red)**.

## Đoạn code cốt lõi

```java
toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            // Xử lý khi công tắc được bật
            linearLayout.setBackgroundColor(Color.GREEN);
        } else {
            // Xử lý khi công tắc bị tắt
            linearLayout.setBackgroundColor(Color.RED);
        }
    }
});
```

Qua ví dụ trực quan này, bạn có thể dễ dàng nắm bắt được cách điều khiển các luồng logic (như bật/tắt cài đặt, thay đổi giao diện, v.v...) dựa vào sự kiện của `ToggleButton`.
