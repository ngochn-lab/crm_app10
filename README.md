# CRM Application

## Mô tả
Ứng dụng CRM (Customer Relationship Management) để quản lý dự án, công việc và người dùng.

## Cấu trúc Database

### Bảng `roles`
- Quản lý các vai trò trong hệ thống (Admin, Manager, User)

### Bảng `users`
- Quản lý thông tin người dùng
- Liên kết với bảng `roles` qua `role_id`

### Bảng `status`
- Quản lý trạng thái công việc (Chưa thực hiện, Đang thực hiện, Đã hoàn thành)

### Bảng `projects`
- Quản lý thông tin dự án
- Liên kết với bảng `users` qua `user_id` (người quản lý dự án)

### Bảng `tasks`
- Quản lý công việc
- Liên kết với:
  - `users` qua `user_id` (người thực hiện)
  - `projects` qua `project_id` (dự án)
  - `status` qua `status_id` (trạng thái)

## Hướng dẫn cài đặt Database

### 1. Yêu cầu
- MySQL Server 5.7 trở lên
- MySQL Workbench hoặc command line client

### 2. Tạo Database

#### Cách 1: Sử dụng MySQL Command Line
```bash
mysql -u root -p < src/main/resources/crm_app_schema.sql
```

#### Cách 2: Sử dụng MySQL Workbench
1. Mở MySQL Workbench
2. Kết nối với MySQL Server
3. Mở file `src/main/resources/crm_app_schema.sql`
4. Execute script (Ctrl+Shift+Enter)

### 3. Cấu hình kết nối
Chỉnh sửa file `src/main/resources/database.properties`:
- `db.username`: Tên đăng nhập MySQL (mặc định: root)
- `db.password`: Mật khẩu MySQL của bạn

## Các Entity Classes

### 1. Roles.java
- Đại diện cho bảng `roles`
- Quản lý vai trò người dùng

### 2. Users.java
- Đại diện cho bảng `users`
- Quản lý thông tin người dùng

### 3. Status.java
- Đại diện cho bảng `status`
- Quản lý trạng thái công việc

### 4. Projects.java
- Đại diện cho bảng `projects`
- Quản lý dự án

### 5. Tasks.java
- Đại diện cho bảng `tasks`
- Quản lý công việc

## Lưu ý quan trọng

### Sửa lỗi trong SQL gốc:
1. **Duplicate entry trong status**: Đã sửa từ 3 dòng "Đang thực hiện" trùng lặp thành 3 trạng thái khác nhau
2. **DROP TABLE projects**: Đã di chuyển lệnh DROP TABLE lên đầu file và thực hiện theo thứ tự đúng
3. **Foreign key constraints**: Đã tạo foreign key trực tiếp trong CREATE TABLE thay vì ALTER TABLE
4. **Bảng jobs**: Đã thay thế bằng bảng `projects` phù hợp với cấu trúc CRM

## Các trang web trong ứng dụng
- **index.html**: Dashboard chính
- **user-table.html**: Quản lý thành viên
- **role-table.html**: Quản lý quyền
- **groupwork.html**: Quản lý dự án
- **task.html**: Quản lý công việc
- **profile.html**: Thông tin cá nhân

## Testing
Để test database sau khi tạo:
```sql
-- Kiểm tra dữ liệu roles
SELECT * FROM roles;

-- Kiểm tra dữ liệu status
SELECT * FROM status;

-- Test login query
SELECT * FROM users 
WHERE email = 'admin@gmail.com' AND password = '123456';
```
