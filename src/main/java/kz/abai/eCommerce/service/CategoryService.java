package kz.abai.eCommerce.service;

import kz.abai.eCommerce.dto.CategoryDto;

public interface CategoryService {
    CategoryDto newCategory(String name, String description);
    CategoryDto newSubCategory(String name, String description, String parentCategoryName);
    CategoryDto updateCategory(String name, String newName, String description, String parentCategoryName, String status);
    void deleteCategory(String name);
}
