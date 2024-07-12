package kz.abai.eCommerce.service;

import kz.abai.eCommerce.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductDto addGood(String name, String description, String imgUrl, String categoryName, BigDecimal cost);
    void deleteGood(String slug);
    ProductDto updateGoodInfo(String slug, String name, String description, String imgUrl, String categoryName, BigDecimal cost);
    List<ProductDto> getAllGoods();
    List<ProductDto> getAllGoodsByCategoryName(String category);
    ProductDto getGoodBySlug(String slug);
}
