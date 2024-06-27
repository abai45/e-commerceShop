package kz.abai.eCommerce.service;

import kz.abai.eCommerce.dto.GoodDto;

import java.math.BigDecimal;

public interface GoodsService {
    GoodDto addGood(String name, String description, String imgUrl, String categoryName, BigDecimal cost);
}
