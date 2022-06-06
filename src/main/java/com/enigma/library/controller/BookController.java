package com.enigma.library.controller;

import com.enigma.library.constant.ApiURLConstant;
import com.enigma.library.dto.BookSearchDTO;
import com.enigma.library.entity.Book;
import com.enigma.library.service.BookService;
import com.enigma.library.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiURLConstant.BOOK)
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    public Page<Book> getBookPerPage(@RequestBody BookSearchDTO bookSearchDTO,
                                     @RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", defaultValue = "3") Integer sizePerPage,
                                     @RequestParam(name = "sortBy", defaultValue = "title") String sortBy,
                                     @RequestParam(name = "direction", defaultValue = "asc") String direction) {
        Sort sorting = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page,sizePerPage,sorting);
        return bookService.getBookPerPage(pageable, bookSearchDTO);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping
    public void deleteBook(@RequestParam String id) {
        bookService.deleteBook(id);
    }
}
