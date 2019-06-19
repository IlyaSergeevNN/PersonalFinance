package com.sergeev.finance.service;

import com.sergeev.finance.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

}
