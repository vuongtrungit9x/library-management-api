package com.example.library_management_api.service;

import com.example.library_management_api.dto.LoanDTO;
import com.example.library_management_api.entity.Book;
import com.example.library_management_api.entity.Loan;
import com.example.library_management_api.entity.Reader;
import com.example.library_management_api.repository.BookRepository;
import com.example.library_management_api.repository.LoanRepository;
import com.example.library_management_api.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public Loan createLoan(LoanDTO loanDTO) {
        var bookId = loanDTO.getBookId();
        var readerId = loanDTO.getReaderId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sách có ID: " + bookId));

        if (book.getAvailableQuantity() == 0) {
            throw new RuntimeException("Sách hiện không có sẵn để mượn");
        }

        Reader reader = readerRepository.findById(loanDTO.getReaderId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy độc giả có ID: " + readerId));

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setReader(reader);
        loan.setBorrowDate(LocalDate.now());
        loan.setDueDate(loanDTO.getDueDate());

        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId) {
        return loanRepository.findById(loanId)
                .map(loan -> {
                    loan.setReturnDate(LocalDate.now());

                    Book book = loan.getBook();
                    book.setAvailableQuantity(book.getAvailableQuantity() + 1);
                    bookRepository.save(book);

                    return loanRepository.save(loan);
                })
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khoản vay có ID: " + loanId));
    }

    public void deleteLoan(Long loanId) {
        Loan loan =  loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Không tìm thấy khoản vay có ID: " + loanId));
                    loan.setReturnDate(LocalDate.now());
                    if(loan.getReturnDate() == null) {
                        Book book = loan.getBook();
                        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
                        bookRepository.save(book);
                    }
                    loanRepository.deleteById(loanId);

    }


    // ... các phương thức khác (ví dụ: tìm kiếm các khoản vay quá hạn)
}