package com.enigma.library.service;

import com.enigma.library.dto.BookSearchDTO;
import com.enigma.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    public Book getBookById(String bookId);
    public Book saveBook(Book book);
    public void deleteBook(String idMember);
    Page<Book> getBookPerPage(Pageable pageable, BookSearchDTO bookSearchDTO);
}
