package com.example.library_management_api.service;

import com.example.library_management_api.entity.Book;
import com.example.library_management_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        // Có thể thêm các xử lý kiểm tra dữ liệu đầu vào ở đây
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    // ... cập nhật các trường khác
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sách có ID: " + id));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    // ... các phương thức khác (ví dụ: cập nhật số lượng sách khả dụng khi mượn/trả
    // sách)
}