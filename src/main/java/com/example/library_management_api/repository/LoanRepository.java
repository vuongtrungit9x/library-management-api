package com.example.library_management_api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library_management_api.entity.Loan;
import com.example.library_management_api.entity.Reader;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    /**
     * @param title
     * @return
     */
    List<Loan> findByReaderAndReturnDateIsNull(Reader reader);

    // Bạn có thể thêm các phương thức truy vấn tùy chỉnh ở đây nếu cần
    // Ví dụ: tìm kiếm các khoản vay theo độc giả, sách, trạng thái, ...

}