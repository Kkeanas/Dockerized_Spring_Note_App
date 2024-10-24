package com.project.notesv2.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/category")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }


    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.addCategory(categoryService.convertDTOtoCategory(categoryDTO));
            return ResponseEntity.ok(categoryService.getAllCategories());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable long id) {
        try {
            categoryService.updateCategory(categoryService.convertDTOtoCategory(categoryDTO), id);
            return ResponseEntity.ok(categoryService.getCategoryByName(categoryDTO.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable long id) {
        try {Category delCat = categoryService.getCategoryById(id);
        if (categoryService.deleteCategory(delCat) == null) {
            return ResponseEntity.badRequest().body("Категория не была найдена");
        }
        return ResponseEntity.ok("Категория была успешно удалена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Категории не существует");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable long id) {
        try {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
