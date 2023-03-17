package com.bowwow.customer.category;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bowwow.common.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	public List<Category> findCategoriesByParentIsNull();

}
