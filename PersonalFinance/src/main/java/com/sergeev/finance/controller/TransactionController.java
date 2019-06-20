package com.sergeev.finance.controller;

import com.sergeev.finance.domain.Transaction;
import com.sergeev.finance.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("transactions", transactionService.findAll());

        return "transaction";
    }

    @PostMapping
    public String editTransaction(Long id, Model model){
        Transaction transaction = transactionService.findTransactionById(id);
        model.addAttribute("transaction", transaction);
        System.out.println(transaction);
        return "main";
    }



}
