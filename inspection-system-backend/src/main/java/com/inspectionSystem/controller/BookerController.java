package com.inspectionSystem.controller;

import com.inspectionSystem.entity.Book;
import com.inspectionSystem.entity.details.BookDetails;
import com.inspectionSystem.entity.resp.RestBean;
import com.inspectionSystem.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class BookerController {

    @Resource
    BookService service;

    @GetMapping("/booksWork")
    public RestBean<?> BookList() {
        return new RestBean<>(HttpStatus.OK, "获取成功！", service.getAllBooks());
    }


    @GetMapping("/booksWork/{id}")
    public RestBean<?> getBook(@PathVariable Integer id) {
        return new RestBean<>(HttpStatus.OK, "获取成功！", service.getBookById(id));
    }

    @GetMapping("/booksWork/employee/{employeeId}")
    public RestBean<?> getBooksByEmployeeId(@PathVariable Integer employeeId) {
        return new RestBean<>(HttpStatus.OK, "获取成功！", service.getBooksByEmployeeId(employeeId));
    }

    @PostMapping("/booksWork/{id}")
    public RestBean<?> change(@RequestBody BookDetails BookDetails, @PathVariable Integer id) {
        Book Book = new Book(BookDetails);
        Book.setId(id);
        service.updateBook(Book);
        return new RestBean<Void>(HttpStatus.OK, "修改成功！");
    }

    @PutMapping("/booksWork")
    public RestBean<?> addBook(@RequestBody BookDetails BookDetails) {
        service.addBook(BookDetails);
        return new RestBean<Void>(HttpStatus.OK, "添加成功！");
    }

    @DeleteMapping("/booksWork/{id}")
    public RestBean<?> remove(@PathVariable Integer id) {
        service.removeBook(id);
        return new RestBean<Void>(HttpStatus.OK, "删除成功！");
    }

}
