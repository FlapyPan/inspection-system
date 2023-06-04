package com.inspectionSystem.service;

import com.inspectionSystem.entity.Book;
import com.inspectionSystem.entity.details.BookDetails;

import java.util.List;

public interface BookService {

    Book getBookById(Integer id);
    List<Book> getAllBooks();
    List<Book> getBooksByEmployeeId(Integer employeeId);
    void addBook(BookDetails bookDetails);
    void updateBook(Book book);
    void removeBook(Integer id);

}
