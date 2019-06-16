package com.sergeev.finance.repos;

import com.sergeev.finance.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {

    List<Transaction> findAll();

    List<Transaction> findAllByUserId(Long id);
}
