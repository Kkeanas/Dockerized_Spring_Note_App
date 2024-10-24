package com.project.notesv2.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Category getCategoryById(long id) {
        if (categoryRepository.existsById(id)) {
            return categoryRepository.getReferenceById(id);
        }
            throw new DataIntegrityViolationException("Категория с id " + id + " не существует");
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    public void addCategory(Category category) {
        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            if (categoryRepository.existsByName(category.getName())) {
                throw new DataIntegrityViolationException("Категория уже существует");
            } else {
            throw new DataIntegrityViolationException("Ошибка формы полученных данных");
            }
        }
    }

    public Category updateCategory(Category category, long id) {
        if (categoryRepository.existsById(id)) {
            Category updatedCategory = categoryRepository.getReferenceById(id);
            updatedCategory.setName(category.getName());
            categoryRepository.save(updatedCategory);
            return categoryRepository.getReferenceById(id);
        }
        throw new DataIntegrityViolationException("Категория с id " + id + " не существует");
    }

    public Category deleteCategory(Category category) {

        if (categoryRepository.existsById(category.getId())) {
            categoryRepository.deleteById(category.getId());
            return category;
        }
        return null;
    }

    public Category convertDTOtoCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }
}
