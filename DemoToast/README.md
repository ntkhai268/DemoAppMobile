# Demo Toast & OnClick Events trong Android

Project này cung cấp một ví dụ đơn giản và dễ hiểu về cách bắt sự kiện người dùng nhấn nút (click) trên giao diện Android, kết hợp sử dụng `Toast` để phản hồi hành động của người dùng.

## Logic Hoạt Động (Core Logic)

Logic cốt lõi của project này không phải là logic tính toán phức tạp, mà là hiểu về luồng xử lý và **3 phương pháp bắt sự kiện `onClick`** khác nhau trên màn hình Android. Cụ thể:

---

### 1. Khai báo thuộc tính `android:onClick` trên XML
Trong file giao diện (`activity_main.xml`), ta gán trực tiếp thuộc tính `android:onClick="showToast"` cho nút bấm.  
Trong lớp `MainActivity.java`, ta khởi tạo một hàm trùng tên, nhận một tham số là `View` để thực thi lệnh.
```java
public void showToast(View view) {
    Toast.makeText(this, "Toast qua thuộc tính android:onClick XML", Toast.LENGTH_LONG).show();
}
```

---

### 2. Dùng Interface (Implement `View.OnClickListener`)
Ta cho `MainActivity` trực tiếp `implements View.OnClickListener`. Lúc này, ta ánh xạ nút bằng `findViewById` và gán sự kiện bằng `button.setOnClickListener(this);`.
Mọi sự kiện Click trên các nút được gán `(this)` đều sẽ được chuyển hướng về duy nhất một phương thức `onClick` được hệ thống override lại. Ta dùng `.getId()` để phân loại xem nút nào vừa được bấm.

```java
@Override
public void onClick(View view){
    if (view.getId() == R.id.button2) {
        Toast.makeText(this, "Toast từ implements OnClickListener", Toast.LENGTH_LONG).show();
    }
}
```

---

### 3. Sử dụng Anonymous Class (Lớp nặc danh)
Sau khi ánh xạ giao diện qua `findViewById`, ta khởi tạo và lắng nghe sự kiện trực tiếp ngay tại khối lệnh của Button đó.
```java
button3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(MainActivity.this, "Toast từ Anonymous class", Toast.LENGTH_LONG).show();
    }
});
```

---

## Bổ sung: Về component `Toast`
`Toast` là một thông báo nhỏ dạng pop-up hiện lên trên màn hình rồi tự tắt trong một thời gian ngắn (`LENGTH_SHORT` hoặc `LENGTH_LONG`). `Toast` không cản trở hành động hiện tại của app và không nhấn vào được. Nhờ tính chất này, Toast rất hay được sử dụng để "báo cáo chớp nhoáng" về kết quả thực hiện hàm hoặc thông báo người dùng thao tác sai.
