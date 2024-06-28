package kz.abai.eCommerce.service.impl;

import jakarta.transaction.Transactional;
import kz.abai.eCommerce.dto.CategoryDto;
import kz.abai.eCommerce.entities.CategoryEntity;
import kz.abai.eCommerce.mapper.CategoryMapper;
import kz.abai.eCommerce.repository.CategoryRepository;
import kz.abai.eCommerce.service.CategoryService;
import kz.abai.eCommerce.utils.CategoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryUtils categoryUtils;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryUtils categoryUtils, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryUtils = categoryUtils;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CategoryDto newCategory(String name, String description) {
        var categoryEntity = categoryUtils.createNewCategoryEntity(name, description);
        System.out.println("category entity: "+categoryEntity.getName()); // for checking
        return categoryMapper.INSTANCE.toDto(categoryEntity);
    }

    @Override
    public CategoryDto newSubCategory(String name, String description, String parentCategoryName) {
        var parentCategory = getCategoryByName(parentCategoryName);
        var subCategoryEntity = categoryUtils.createSubcategoryEntity(name, description, parentCategory);
        System.out.println("subcategory entity: "+subCategoryEntity.getName()); // for checking
        return categoryMapper.INSTANCE.toDto(subCategoryEntity);
    }

    @Override
    public CategoryDto updateCategory(String name,String newName, String description, String parentCategoryName) {
        var categoryEntity = getCategoryByName(name);
        categoryEntity.setName(newName);
        categoryEntity.setDescription(description);
        if(parentCategoryName !=null && !parentCategoryName.isBlank()) {
            var parentCategory = getCategoryByName(parentCategoryName);
            categoryEntity.setParentCategory(parentCategory);
        } else {
            categoryEntity.setParentCategory(null);
        }
        categoryRepository.save(categoryEntity);
        return categoryMapper.toDto(categoryEntity);
    }

    @Transactional
    @Override
    public void deleteCategory(String name) {
        var categoryEntity = getCategoryByName(name);
        categoryRepository.deleteAll(categoryEntity.getSubCategories());
        categoryRepository.delete(categoryEntity);
    }


    private CategoryEntity getCategoryByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("Parent Category by name not found"));
    }
}
