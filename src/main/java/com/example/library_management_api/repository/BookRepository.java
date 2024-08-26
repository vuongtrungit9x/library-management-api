package com.example.library_management_api.repository;

import com.example.library_management_api.entity.Book;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);
    // Bạn có thể thêm các phương thức truy vấn tùy chỉnh ở đây nếu cần
    // Ví dụ: tìm kiếm sách theo tiêu đề, tác giả, ...
}
