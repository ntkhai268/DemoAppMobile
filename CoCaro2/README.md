# Demo Cờ Ca Rô (Tic Tac Toe)

Đây là một project Android demo đơn giản nhằm hướng dẫn cách xây dựng trò chơi cờ ca rô (Tic Tac Toe) 3x3 cơ bản với 2 người chơi (Player 1 và Player 2).

## Cấu trúc Giao diện (UI)

- **Lưới bàn cờ (9 Buttons)**: Sử dụng các `Button` (`bt1` đến `bt9`) để tạo thành một lưới 3x3. Mỗi nút đại diện cho một ô trên bàn cờ, cho phép người chơi click vào để đánh dấu "X" hoặc "O".
- **Button Bắt đầu / Chơi lại**: Nút (`btPlayAgain`) dùng để kiểm soát trạng thái trò chơi, cho phép người dùng ấn "Bắt đầu" để vào game hoặc "Chơi lại" để xóa bàn cờ và đánh lại ván mới.
- **TextView Kết quả**: Một `TextView` (`txtShowresult`) ban đầu được ẩn đi (`INVISIBLE`), dùng để hiển thị thông báo kết quả cuối cùng (Player 1 thắng, Player 2 thắng, hoặc Hòa).

## Luồng Hoạt động (Logic)

1. **Khởi tạo và ánh xạ View**: Sử dụng phương thức `AnhXa()` để liên kết các thành phần giao diện từ file XML sang code Java bằng `findViewById(...)`.
2. **Quản lý trạng thái trò chơi (Start / Reset)**: 
   - Gắn sự kiện lắng nghe cho nút `btPlayAgain`.
   - Dùng biến `startGame` để kiểm tra. Khi bắt đầu (`startGame = 1`), bật chế độ chơi. Khi reset lại (`PlayAgain()`), xóa dữ liệu của 2 người chơi (`Player1.clear()`, `Player2.clear()`), làm trống các nút và đặt lại màu nền mặc định.
3. **Thao tác đánh cờ**:
   - Các nút trên bàn cờ sẽ dùng chung một hàm lắng nghe sự kiện click `btClick(View view)`.
   - Khi một ô được chọn, lấy ra `CellID` (từ 1 đến 9). Nếu trò chơi đang chạy và chưa có ai thắng (`Winer == -1 && startGame == 1`), hàm `PlayGame(...)` được tiến hành.
4. **Xử lý lượt đánh (`PlayGame`)**:
   - Dùng biến `ActivePlayer` để xác định lượt. 
     - Lượt chẵn/lẻ sẽ quyết định là cờ "X" (xanh lá text đỏ) hay cờ "O" (xanh dương text trắng).
     - Lưu lại vị trí ô vừa đánh vào `ArrayList` tương ứng của người chơi (ví dụ: `Player1` hoặc `Player2`).
     - Đảo sang lượt của người còn lại.
5. **Kiểm tra Thắng / Thua / Hòa (`CheckWiner`)**:
   - Dựa trên danh sách các ô mà mỗi người chơi đã đánh, kiểm tra với các trường hợp thắng đã định sẵn: 3 dòng ngang, 3 cột dọc và 2 đường chéo.
   - Nếu điều kiện khớp, cập nhật biến `Winer` thành `1` hoặc `2`. 
   - Nếu tổng số ô đã đánh bằng 9 mà `Winer` vẫn là `-1`, cập nhật `Winer` thành `0` (Hòa).
   - Hiển thị kết quả công khai bằng `txtShowresult`.
