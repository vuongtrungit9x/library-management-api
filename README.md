# Overview
project Spring Boot cho API service ứng dụng quản lý thư viện sách

## Database Schema & init Data

```
-- Table: books
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100), 
    publisher VARCHAR(255),
    publication_year INT,
    isbn VARCHAR(13) UNIQUE, -- International Standard Book Number, thêm ràng buộc UNIQUE
    quantity INT NOT NULL DEFAULT 0, 
    available_quantity INT NOT NULL DEFAULT 0, -- Số lượng sách có sẵn để mượn
    CHECK (available_quantity <= quantity) -- Ràng buộc kiểm tra số lượng sách khả dụng không vượt quá tổng số lượng
);

-- Table: readers
CREATE TABLE readers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE, 
    phone VARCHAR(20),
    address TEXT
);

-- Table: loans
CREATE TABLE loans (
    id SERIAL PRIMARY KEY,
    book_id INT REFERENCES books(id) ON DELETE CASCADE,
    reader_id INT REFERENCES readers(id) ON DELETE CASCADE,
    borrow_date DATE NOT NULL DEFAULT CURRENT_DATE,
    due_date DATE NOT NULL, 
    return_date DATE,
    CHECK (due_date >= borrow_date) -- Ràng buộc kiểm tra ngày trả phải sau ngày mượn
);

-- Tạo chỉ mục cho các cột thường được sử dụng để tìm kiếm
CREATE INDEX idx_books_title ON books (title);
CREATE INDEX idx_books_author ON books (author);
CREATE INDEX idx_readers_name ON readers (name);



-- Thêm dữ liệu mẫu vào bảng books
INSERT INTO books (title, author, genre, publisher, publication_year, isbn, quantity, available_quantity)
VALUES 
    ('Đắc Nhân Tâm', 'Dale Carnegie', 'Kỹ năng sống', 'NXB Trẻ', 1936, '90671023522', 5, 3),
    ('Nhà Giả Kim', 'Paulo Coelho', 'Tiểu thuyết', 'NXB Văn Học', 1988, '0062315007', 3, 0),
    ('Sapiens: Lược Sử Loài Người', 'Yuval Noah Harari', 'Lịch sử', 'NXB Tri Thức', 2011, '0062316097', 2, 2),
    ('Hành Trình Về Phương Đông', 'Baird T. Spalding', 'Tâm linh', 'NXB Hồng Đức', 1924, '0875165604', 4, 4),
    ('Tôi Tài Giỏi, Bạn Cũng Thế!', 'Adam Khoo', 'Phát triển bản thân', 'NXB Tổng hợp TP.HCM', 2001, '6045852156', 6, 5);

-- Thêm dữ liệu mẫu vào bảng readers
INSERT INTO readers (name, email, phone, address)
VALUES
    ('Nguyễn Văn A', 'nguyenvana@gmail.com', '0912345678', '123 Nguyễn Trãi, Quận 1, TP.HCM'),
    ('Trần Thị B', 'tranthib@yahoo.com', '0987654321', '456 Lê Lợi, Quận 3, TP.HCM'),
    ('Lê Văn C', 'levanc@hotmail.com', '0123456789', '789 Hai Bà Trưng, Quận 10, TP.HCM');

   -- Thêm dữ liệu mẫu vào bảng loans (giả sử sách "Nhà Giả Kim" đã được mượn hết)
INSERT INTO loans (book_id, reader_id, borrow_date, due_date, return_date)
VALUES
    (1, 1, '2023-08-15', '2023-08-30', NULL), 
    (2, 2, '2023-08-10', '2023-08-25', '2023-08-20'), 
    (2, 3, '2023-08-18', '2023-09-02', NULL); 
   
   
   
select * from loans l ; 
select * from books b;   
select * from readers r ;
```
