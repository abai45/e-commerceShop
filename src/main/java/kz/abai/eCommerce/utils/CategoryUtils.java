package kz.abai.eCommerce.utils;

import kz.abai.eCommerce.entities.CategoryEntity;
import kz.abai.eCommerce.enums.CategoryStatus;
import kz.abai.eCommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryUtils {
    private final CategoryRepository categoryRepository;
    public CategoryEntity createNewCategoryEntity(String name, String description) {
        var category = CategoryEntity.builder()
                .name(name)
                .description(description)
                .status(CategoryStatus.ACTIVE)
                .build();
        return categoryRepository.save(category);
    }
    public CategoryEntity createSubcategoryEntity(String name, String description, CategoryEntity parentCategory) {
        var subCategory = CategoryEntity.builder()
                .name(name)
                .description(description)
                .status(CategoryStatus.ACTIVE)
                .parentCategory(parentCategory)
                .build();
        parentCategory.addSubCategory(subCategory);
        return categoryRepository.save(subCategory);
    }
}
