package com.springboot.h2db.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bookName;
    private String bookAuthor;
}
