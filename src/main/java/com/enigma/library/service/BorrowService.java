package com.enigma.library.service;

import com.enigma.library.entity.Book;
import com.enigma.library.entity.Borrow;

import java.sql.Date;
import java.util.List;

public interface BorrowService {
    public Borrow getBorrowById(String borrowId);
    public void saveBorrow(String memberId, String bookId, String isbn, Date borrowDate);
}
