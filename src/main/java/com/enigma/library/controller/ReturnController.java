package com.enigma.library.controller;

import com.enigma.library.constant.ApiURLConstant;
import com.enigma.library.service.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping(ApiURLConstant.RETURN)
public class ReturnController {

    @Autowired
    ReturnBookService returnService;

    @PostMapping
    public void saveReturnBook(@RequestParam String borrowId, @RequestParam Date date) {
        returnService.saveReturnBook(borrowId, date);
    }
}
