package com.enigma.library.controller;

import com.enigma.library.constant.ApiURLConstant;
import com.enigma.library.entity.Borrow;
import com.enigma.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping(ApiURLConstant.BORROW)
public class BorrowController {

    @Autowired
    BorrowService borrowService;

    @GetMapping("/{id}")
    public Borrow getBorrowById(@PathVariable String id) {
        return borrowService.getBorrowById(id);
    }

    @PostMapping
    public void saveBorrow(@RequestParam String memberId, @RequestParam String bookId, @RequestParam String isbn, @RequestParam Date date) {
        borrowService.saveBorrow(memberId, bookId, isbn, date);
    }
}
