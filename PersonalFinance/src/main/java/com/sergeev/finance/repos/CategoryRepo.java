package com.sergeev.finance.repos;

import com.sergeev.finance.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepo extends CrudRepository<Category, Long> {

    List<Category> findAll();
    List<Category> findByNameCategory(String name);
    List<Category> findAllByNameCategory(String name);


}
