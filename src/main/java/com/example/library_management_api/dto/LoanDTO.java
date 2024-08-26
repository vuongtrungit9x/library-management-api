package com.example.library_management_api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data // This will automatically generate getters, setters, equals, hashCode, and toString
@NoArgsConstructor // Generates a no-argument constructor
public class LoanDTO {
    private Long bookId;
    private Long readerId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    // Add other fields as needed (e.g., interestRate, status)

    // Getters and setters
}