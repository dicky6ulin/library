package com.enigma.library.service.impl;

import com.enigma.library.entity.Borrow;
import com.enigma.library.entity.ReturnBook;
import com.enigma.library.repository.ReturnBookRepository;
import com.enigma.library.service.BorrowService;
import com.enigma.library.service.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ReturnBookServiceImpl implements ReturnBookService {

    @Autowired
    private ReturnBookRepository returnRepository;

    @Autowired
    private BorrowService borrowService;

    @Override
    public void saveReturnBook(String borrowId, Date returnDate) {

        Borrow borrow = borrowService.getBorrowById(borrowId);

        borrow.getBook().setStock(borrow.getBook().getStock() + 1);

        ReturnBook returnBook = new ReturnBook();
        returnBook.setBorrow(borrow);
        returnBook.setReturnDate(returnDate);
        borrow.setStatus("inactive");
        returnRepository.save(returnBook);
    }
}
