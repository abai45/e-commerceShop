package kz.abai.eCommerce.service;

import kz.abai.eCommerce.repository.CategoryRepository;
import kz.abai.eCommerce.utils.CategoryUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryUtilsTest {
    @Autowired
    private CategoryUtils categoryUtils;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateNewCategoryEntity() {
        var category = categoryUtils.createNewCategoryEntity("Electronics", "New category for electronic devices");
        assertNotNull(category);
        assertEquals("Electronics", category.getName());
    }

    @Test
    public void testCreateSubcategoryEntity() {
        var electronics = categoryUtils.createNewCategoryEntity("Electronics", "New category for electronic devices");
        var smartphones = categoryUtils.createSubcategoryEntity("Smartphones", "Latest smartphones", electronics);
        assertNotNull(smartphones);
        assertEquals("Smartphones", smartphones.getName());
        assertEquals(electronics, smartphones.getParentCategory());
        electronics = categoryRepository.findById(electronics.getId())
                .orElseThrow(() -> new RuntimeException("Category by ID not found"));
        assertTrue(electronics.getSubCategories().contains(smartphones));
    }
}
