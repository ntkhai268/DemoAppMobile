# Demo SeekBar

## Kiến trúc và Luồng hoạt động

- **Layout chính**: Có thể là `LinearLayout`, hoặc `ConstraintLayout` (tùy người làm).
- **Luồng hoạt động**:
  - Khi người dùng kéo thẻ `SeekBar`, `SeekBar` đó sẽ lắng nghe sự kiện `onProgressChanged`.
  - Sau đó, giá trị hiện tại (progress) sẽ được cập nhật vào `TextView` để hiển thị.

## Chi tiết Code

Để bắt sự kiện của `SeekBar`, chúng ta sử dụng interface `SeekBar.OnSeekBarChangeListener`. Interface này yêu cầu ghi đè (override) 3 phương thức, trong đó `onProgressChanged` là phương thức chính được dùng để cập nhật giao diện trong ví dụ này:

```java
seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // Cập nhật giá trị vào TextView mỗi khi giá trị trên SeekBar thay đổi
        textView.setText("Value: " + progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Được gọi khi người dùng bắt đầu chạm/kéo SeekBar
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Được gọi khi người dùng thả tay, kết thúc việc kéo SeekBar
    }
});
```

*🔥 Lưu ý: Bạn có thể quy định giá trị tối đa của SeekBar trực tiếp bên phần layout XML thông qua thuộc tính `android:max` (ví dụ `android:max="100"`).*
