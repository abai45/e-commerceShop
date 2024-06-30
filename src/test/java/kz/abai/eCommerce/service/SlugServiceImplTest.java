package kz.abai.eCommerce.service;

import kz.abai.eCommerce.service.impl.SlugServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlugServiceImplTest {

    private SlugService slugService;

    @BeforeEach
    public void setUp() {
        slugService = new SlugServiceImpl();
    }

    @Test
    public void testCreateSlug() {
        assertEquals("a", slugService.createSlug("а"));
        assertEquals("zh", slugService.createSlug("ж"));
        assertEquals("test", slugService.createSlug("test"));
        assertEquals("privet-mir", slugService.createSlug("Привет Мир"));
        assertEquals("yu", slugService.createSlug("ю"));
        assertEquals("ya", slugService.createSlug("я"));
    }

    @Test
    public void testCreateSlugWithSpecialCharacters() {
        assertEquals("privet-mir", slugService.createSlug("Привет, мир!"));
    }

    @Test
    public void testCreateSlugWithNumbers() {
        assertEquals("tovar123", slugService.createSlug("товар123"));
        assertEquals("123tovar", slugService.createSlug("123товар"));
    }

    @Test
    public void testCreateSlugWithEmptyInput() {
        assertEquals("", slugService.createSlug(""));
    }

    @Test
    public void testCreateSlugWithKazakhCharacters() {
        assertEquals("a", slugService.createSlug("ә"));
        assertEquals("g", slugService.createSlug("ғ"));
        assertEquals("k", slugService.createSlug("қ"));
        assertEquals("n", slugService.createSlug("ң"));
        assertEquals("o", slugService.createSlug("ө"));
        assertEquals("u", slugService.createSlug("ұ"));
        assertEquals("u", slugService.createSlug("ү"));
        assertEquals("i", slugService.createSlug("і"));
    }
}
