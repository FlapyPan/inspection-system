package com.inspectionSystem.service.impl;

import com.inspectionSystem.entity.Book;
import com.inspectionSystem.entity.details.BookDetails;
import com.inspectionSystem.mapper.BookMapper;
import com.inspectionSystem.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper mapper;

    @Override
    public Book getBookById(Integer id) {
        return mapper.selectBookById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return mapper.selectAll();
    }

    @Override
    public List<Book> getBooksByEmployeeId(Integer employeeId) {
        return mapper.selectBooksByEmployeeId(employeeId);
    }

    @Override
    public void addBook(BookDetails bookDetails) {
        mapper.addBook(bookDetails);
    }

    @Override
    public void updateBook(Book book) {
        mapper.updateBook(book);
    }

    @Override
    public void removeBook(Integer id) {
        mapper.deleteBook(id);
    }
}
