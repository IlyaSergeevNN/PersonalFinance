package com.sergeev.finance.service;

import com.sergeev.finance.domain.Transaction;
import com.sergeev.finance.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    public Object findAll() {
        return transactionRepo.findAll();
    }

    public Transaction findTransactionById (Long id){
        return transactionRepo.findAllById(id).get(0);
    }

}
