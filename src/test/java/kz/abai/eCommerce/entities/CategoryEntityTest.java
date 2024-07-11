import kz.abai.eCommerce.entities.CategoryEntity;
import kz.abai.eCommerce.enums.CategoryStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryEntityTest {

    private CategoryEntity electronics;
    private CategoryEntity phonesGadgets;
    private CategoryEntity accessories;
    private CategoryEntity smartphones;
    private CategoryEntity gadgets;
    private CategoryEntity smartWatches;

    @BeforeEach
    void setUp() {
        electronics = new CategoryEntity();
        electronics.setName("Electronics");
        electronics.setStatus(CategoryStatus.ACTIVE);

        phonesGadgets = new CategoryEntity();
        phonesGadgets.setName("Phones and Gadgets");
        phonesGadgets.setStatus(CategoryStatus.ACTIVE);
        electronics.addSubCategory(phonesGadgets);

        accessories = new CategoryEntity();
        accessories.setName("Phone Accessories");
        accessories.setStatus(CategoryStatus.ACTIVE);
        phonesGadgets.addSubCategory(accessories);

        smartphones = new CategoryEntity();
        smartphones.setName("Smartphones");
        smartphones.setStatus(CategoryStatus.ACTIVE);
        phonesGadgets.addSubCategory(smartphones);

        gadgets = new CategoryEntity();
        gadgets.setName("Gadgets");
        gadgets.setStatus(CategoryStatus.ACTIVE);
        phonesGadgets.addSubCategory(gadgets);

        smartWatches = new CategoryEntity();
        smartWatches.setName("Smart Watches");
        smartWatches.setStatus(CategoryStatus.ACTIVE);
        gadgets.addSubCategory(smartWatches);
    }

    @Test
    void testAddSubCategory() {
        assertEquals(1, electronics.getSubCategories().size());
        assertTrue(electronics.getSubCategories().contains(phonesGadgets));
    }

    @Test
    void testRemoveSubCategory() {
        electronics.removeSubCategory(phonesGadgets);
        assertEquals(0, electronics.getSubCategories().size());
        assertNull(phonesGadgets.getParentCategory());
    }

    @Test
    void testHierarchy() {
        assertEquals("Electronics", phonesGadgets.getParentCategory().getName());
        assertEquals("Phones and Gadgets", accessories.getParentCategory().getName());
        assertEquals("Phones and Gadgets", smartphones.getParentCategory().getName());
        assertEquals("Phones and Gadgets", gadgets.getParentCategory().getName());
        assertEquals("Gadgets", smartWatches.getParentCategory().getName());
    }
}
