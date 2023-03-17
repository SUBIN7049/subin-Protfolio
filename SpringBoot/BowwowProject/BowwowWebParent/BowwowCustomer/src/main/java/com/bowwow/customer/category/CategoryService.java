package com.bowwow.customer.category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Category;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
    
    public List<Category> getParentCategories() {
        return categoryRepository.findCategoriesByParentIsNull();
    }

    public List<Category> getChildrenByParentCategory(Integer categoryId) {
        Optional<Category> optionalParentCategory = categoryRepository.findById(categoryId);
        if (optionalParentCategory.isPresent()) {
            Category parentCategory = optionalParentCategory.get();
            return new ArrayList<>(parentCategory.getChildren());
        }
        return Collections.emptyList();
    }

}
