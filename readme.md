Nếu truy cập vào danh sách chi tiết thuốc được cấu hình `FetchType.LAZY` sau khi Session (hoặc EntityManager) đã đóng,
hệ thống sẽ phát sinh lỗi `LazyInitializationException`.

Kịch bản lỗi xảy ra như sau:

- Entity cha (ví dụ `Prescription`) được truy vấn trong service layer.
- Session hoặc transaction kết thúc (đóng persistence context).
- Sau đó ở tầng controller hoặc view, chương trình cố truy cập vào danh sách `medicines` (được load LAZY).
- Hibernate cố gắng load dữ liệu nhưng không còn session để thực hiện truy vấn → phát sinh
  `LazyInitializationException: could not initialize proxy - no Session`.

Hậu quả:

- Ứng dụng bị lỗi runtime khi truy cập dữ liệu liên quan.
- API có thể trả về lỗi 500 hoặc dữ liệu thiếu.

Cách khắc phục:

1. Dùng `JOIN FETCH` trong HQL/JPQL:
    - Lấy luôn dữ liệu con ngay từ đầu khi query entity cha.
    - Ví dụ: `SELECT p FROM Prescription p JOIN FETCH p.medicines`

2. Mở session trong phạm vi cần thiết (Open Session in View):
    - Giữ session mở đến khi render view (thường dùng trong web MVC).
    - Tuy nhiên dễ gây vấn đề hiệu năng nếu lạm dụng.

3. Fetch dữ liệu trong service layer (khuyến nghị):
    - Chuyển entity sang DTO trước khi trả về controller.
    - Đảm bảo toàn bộ dữ liệu cần thiết được load trong transaction.

4. Chuyển sang `FetchType.EAGER` (ít khuyến khích):
    - Load luôn dữ liệu con khi truy vấn cha.
    - Có thể gây thừa dữ liệu và giảm hiệu năng nếu quan hệ lớn.

=> Cách tối ưu nhất thường là dùng `JOIN FETCH` kết hợp DTO để kiểm soát dữ liệu rõ ràng và tránh lỗi
LazyInitializationException.