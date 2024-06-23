package kz.abai.eCommerce.service.impl;

import kz.abai.eCommerce.dto.CategoryDto;
import kz.abai.eCommerce.entities.CategoryEntity;
import kz.abai.eCommerce.mapper.CategoryMapper;
import kz.abai.eCommerce.repository.CategoryRepository;
import kz.abai.eCommerce.service.CategoryService;
import kz.abai.eCommerce.utils.CategoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryUtils categoryUtils;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto newCategory(String name, String description) {
        var categoryEntity = categoryUtils.createNewCategoryEntity(name, description);
        return categoryMapper.toDto(categoryEntity);
    }

    @Override
    public CategoryDto newSubCategory(String name, String description, String parentCategoryName) {
        var parentCategory = getCategoryByName(parentCategoryName);
        var subCategoryEntity = categoryUtils.createSubcategoryEntity(name, description, parentCategory);
        return categoryMapper.toDto(subCategoryEntity);
    }
    private CategoryEntity getCategoryByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("Parent Category by name not found"));
    }
}
