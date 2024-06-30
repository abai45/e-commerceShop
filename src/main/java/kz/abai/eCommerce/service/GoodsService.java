package kz.abai.eCommerce.service;

import kz.abai.eCommerce.dto.GoodDto;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsService {
    GoodDto addGood(String name, String description, String imgUrl, String categoryName, BigDecimal cost);
    void deleteGood(String slug);
    GoodDto updateGoodInfo(String slug, String name, String description, String imgUrl, String categoryName, BigDecimal cost);
    List<GoodDto> getAllGoods();
    List<GoodDto> getAllGoodsByCategoryName(String category);
    GoodDto getGoodBySlug(String slug);
}
