package com.enigma.library.service.impl;

import com.enigma.library.constant.ResponMesaage;
import com.enigma.library.entity.Book;
import com.enigma.library.entity.Borrow;
import com.enigma.library.entity.Member;
import com.enigma.library.exception.DataNotFoundException;
import com.enigma.library.repository.BorrowRepository;
import com.enigma.library.service.BookService;
import com.enigma.library.service.BorrowService;
import com.enigma.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private MemberService memberService;

    @Override
    public Borrow getBorrowById(String borrowId) {
        if (!borrowRepository.findById(borrowId).isPresent()) {
            String message = String.format(ResponMesaage.DATA_NOT_FOUND, "borrow", borrowId);
            throw new DataNotFoundException(message);
        }
        return borrowRepository.findById(borrowId).get();
    }

    @Override
    public void saveBorrow(String memberId, String bookId, String isbn, Date borrowDate) {
        Member member = memberService.getMemberById(memberId);
        Book book = bookService.getBookById(bookId);

        if (book.getStock() > 0) {
            book.setStock(book.getStock() - 1);
            bookService.saveBook(book);

            Borrow borrow = new Borrow();
            borrow.setBook(book);
            borrow.setMember(member);
            borrow.setIsbn(isbn);
            borrow.setBorrowDate(borrowDate);
            borrow.setStatus("active");
            borrowRepository.save(borrow);
        }
    }
}
