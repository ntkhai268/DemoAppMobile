# Demo SeekBar

## Kiến trúc và Luồng hoạt động

- **Layout chính**: Có thể là `LinearLayout`, hoặc `ConstraintLayout` (tùy người làm).
- **Luồng hoạt động**:
  - Khi người dùng kéo thẻ `SeekBar`, `SeekBar` đó sẽ lắng nghe sự kiện `onProgressChanged`.
  - Sau đó, giá trị hiện tại (progress) sẽ được cập nhật vào `TextView` để hiển thị.
