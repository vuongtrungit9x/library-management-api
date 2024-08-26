package com.example.library_management_api.service;

import com.example.library_management_api.entity.Reader;
import com.example.library_management_api.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Optional<Reader> getReaderById(Long id) {
        return readerRepository.findById(id);
    }

    public Reader createReader(Reader reader) {
        // Có thể thêm các xử lý kiểm tra dữ liệu đầu vào ở đây
        return readerRepository.save(reader);
    }

    public Reader updateReader(Long id, Reader updatedReader) {
        return readerRepository.findById(id)
                .map(reader -> {
                    reader.setName(updatedReader.getName());
                    reader.setEmail(updatedReader.getEmail());
                    // ... cập nhật các trường khác
                    return readerRepository.save(reader);
                })
                .orElseThrow(() -> new RuntimeException("Không tìm thấy độc giả có ID: " + id));
    }

    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }

    public Optional<Reader> findReaderByEmail(String email) {
        return readerRepository.findByEmail(email);
    }

    // ... các phương thức khác
}
