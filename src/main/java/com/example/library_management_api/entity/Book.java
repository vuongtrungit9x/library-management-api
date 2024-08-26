package com.example.library_management_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data // This will automatically generate getters, setters, equals, hashCode, and toString
@NoArgsConstructor // Generates a no-argument constructor
@JsonIgnoreProperties("loans")
@Table(name = "books") 
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String genre;

    private String publisher;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(unique = true)
    private String isbn;

    private int quantity;

    @Column(name = "available_quantity")
    private int availableQuantity;

    // Constructors, getters, and setters
}