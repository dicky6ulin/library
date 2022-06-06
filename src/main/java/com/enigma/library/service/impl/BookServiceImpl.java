package com.enigma.library.service.impl;

import com.enigma.library.constant.ResponMesaage;
import com.enigma.library.dto.BookSearchDTO;
import com.enigma.library.entity.Book;
import com.enigma.library.exception.DataNotFoundException;
import com.enigma.library.repository.BookRepository;
import com.enigma.library.service.BookService;
import com.enigma.library.specification.BookSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookById(String bookId) {
        if (!bookRepository.findById(bookId).isPresent()) {
            String message = String.format(ResponMesaage.DATA_NOT_FOUND, "book", bookId);
            throw new DataNotFoundException(message);
        }
        return bookRepository.findById(bookId).get();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String idMember) {
        bookRepository.deleteById(idMember);
    }

    @Override
    public Page<Book> getBookPerPage(Pageable pageable, BookSearchDTO bookSearchDTO) {
        Specification<Book> bookSpecification = BookSpecification.getSpecification(bookSearchDTO);
        return bookRepository.findAll(bookSpecification, pageable);
    }
}
