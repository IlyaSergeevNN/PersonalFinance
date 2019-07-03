package com.sergeev.finance.repos;

import com.sergeev.finance.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId " +
            "AND (t.category.type = :categoryType OR :categoryType = '') " +
            "AND (t.category.nameCategory = :category OR :category = '') " +
            "AND ((t.amount BETWEEN :minAmount AND :maxAmount) " +
            "OR (:minAmount is null AND :maxAmount is null) " +
            "OR (t.amount >= :minAmount AND :maxAmount is null) " +
            "OR (t.amount <= :maxAmount AND :minAmount is null)) " +
            "AND (t.timestamp BETWEEN :minTimestamp AND :maxTimestamp)")
    List<Transaction> findAllByFilters(@Param("userId") Long userId,
                              @Param("categoryType") String categoryType,
                              @Param("category") String category,
                              @Param("minAmount") Double minAmount,
                              @Param("maxAmount") Double maxAmount,
                              @Param("minTimestamp") Timestamp minTimestamp,
                              @Param("maxTimestamp") Timestamp maxTimestamp);

    List<Transaction> findAllByUserId(Long id);

    List<Transaction> findAllById(Long id);

    void deleteById(Long id);

}
