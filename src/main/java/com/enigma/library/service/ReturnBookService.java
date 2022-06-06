package com.enigma.library.service;

import java.sql.Date;

public interface ReturnBookService {
    public void saveReturnBook(String borrowId, Date returnDate);
}
