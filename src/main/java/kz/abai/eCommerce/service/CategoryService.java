package kz.abai.eCommerce.service;

import kz.abai.eCommerce.dto.CategoryDto;

public interface CategoryService {
    CategoryDto newCategory(String name, String description);
    CategoryDto newSubCategory(String name, String description, String parentCategoryName);
}
