package com.example.library_management_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.library_management_api.entity.Reader;
import com.example.library_management_api.repository.ReaderRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    @Autowired
    private ReaderRepository readerRepository;

    // Get all readers
    @GetMapping
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    // Get a reader by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable Long id) {
        Optional<Reader> reader = readerRepository.findById(id);
        return reader.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new reader
    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {
        Reader savedReader = readerRepository.save(reader);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReader);
    }

    // Update a reader
    @PutMapping("/{id}")
    public ResponseEntity<Reader> updateReader(@PathVariable Long id, @RequestBody Reader reader) {
        if (!readerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reader.setId(id);
        Reader updatedReader = readerRepository.save(reader);
        return ResponseEntity.ok(updatedReader);
    }

    // Delete a reader
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
        if (!readerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        readerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}