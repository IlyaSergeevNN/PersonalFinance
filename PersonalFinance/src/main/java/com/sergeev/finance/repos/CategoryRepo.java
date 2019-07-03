package com.sergeev.finance.repos;

import com.sergeev.finance.domain.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepo extends CrudRepository<Category, Long> {

    List<Category> findAll();
    List<Category> findByNameCategory(String name);

    @Query("SELECT c FROM Category c WHERE c.user.id = :userId " +
            "OR c.user.id is null OR c.user.id = 1")
    List<Category> findAllByUserId(@Param("userId") Long userId);


}
