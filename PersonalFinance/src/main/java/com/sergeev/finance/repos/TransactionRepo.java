package com.sergeev.finance.repos;

import com.sergeev.finance.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {

    List<Transaction> findAll();

    List<Transaction> findAllByUserId(Long id);

    List<Transaction> findAllById(Long id);

    //6 filters available
    List<Transaction> findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountBetweenAndTimestampBetween
            (Long id, String categoryType, String categoryName, Double minAmount, Double maxAmount, Timestamp timestamp, Timestamp timestamp2);

    //5 filters available
    List<Transaction> findAllByUserIdAndCategoryNameCategoryAndAmountBetweenAndTimestampBetween
            (Long id, String categoryName, Double minAmount, Double maxAmount, Timestamp timestamp, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryTypeAndAmountBetweenAndTimestampBetween
            (Long id, String categoryType, Double minAmount, Double maxAmount, Timestamp timestamp, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountBeforeAndTimestampBetween
            (Long id, String categoryType, String categoryName, Double maxAmount, Timestamp timestamp, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountAfterAndTimestampBetween
            (Long id, String categoryType, String categoryName, Double minAmount, Timestamp timestamp, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountBetweenAndTimestampBefore
            (Long id, String categoryType, String categoryName, Double minAmount, Double maxAmount, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountBetweenAndTimestampAfter
            (Long id, String categoryType, String categoryName, Double minAmount, Double maxAmount, Timestamp timestamp);

    //4 filters available
    List<Transaction> findAllByUserIdAndAmountBetweenAndTimestampBetween
    (Long id, Double minAmount, Double maxAmount, Timestamp timestamp, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryNameCategoryAndAmountBeforeAndTimestampBetween
            (Long id, String categoryName, Double maxAmount, Timestamp timestamp, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryNameCategoryAndAmountAfterAndTimestampBetween
            (Long id, String categoryName, Double minAmount, Timestamp timestamp, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryNameCategoryAndAmountBetweenAndTimestampBefore
            (Long id, String categoryName, Double minAmount, Double maxAmount, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryNameCategoryAndAmountBetweenAndTimestampAfter
            (Long id, String categoryName, Double minAmount, Double maxAmount, Timestamp timestamp);

    List<Transaction> findAllByUserIdAndCategoryTypeAndAmountBeforeAndTimestampBetween
            (Long id, String categoryType, Double maxAmount, Timestamp timestamp, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryTypeAndAmountAfterAndTimestampBetween
            (Long id, String categoryType, Double minAmount, Timestamp timestamp, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryTypeAndAmountBetweenAndTimestampBefore
            (Long id, String categoryType, Double minAmount, Double maxAmount, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryTypeAndAmountBetweenAndTimestampAfter
            (Long id, String categoryType, Double minAmount, Double maxAmount, Timestamp timestamp);

    //4 filters available, exclude 3-x
    List<Transaction> findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndTimestampBetween
    (Long id, String categoryType, String categoryName, Timestamp timestamp, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountBeforeAndTimestampBefore
            (Long id, String categoryType, String categoryName, Double maxAmount, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountBeforeAndTimestampAfter
            (Long id, String categoryType, String categoryName, Double maxAmount, Timestamp timestamp);

    //4 filters available, exclude 4-x
    List<Transaction> findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountAfterAndTimestampBefore
            (Long id, String categoryType, String categoryName, Double minAmount, Timestamp timestamp2);

    List<Transaction> findAllByUserIdAndCategoryTypeAndCategoryNameCategoryAndAmountAfterAndTimestampAfter
            (Long id, String categoryType, String categoryName, Double minAmount, Timestamp timestamp);




    List<Transaction> findAllByUserIdAndCategoryNameCategory(Long id, String nameCategory);

    List<Transaction> findAllByUserIdAndCategoryNameCategoryAndAmountBetween(Long id, String nameCategory, Double min, Double max);

    List<Transaction> findAllByUserIdAndAmountAfter(Long id, Double minAmount);

    List<Transaction> findAllByUserIdAndAmountBefore(Long id, Double minAmount);

    List<Transaction> findAllByUserIdAndTimestampBetween(Long id, Timestamp timestamp, Timestamp timestamp2);

    void deleteById(Long id);

}
