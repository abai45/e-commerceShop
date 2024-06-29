package kz.abai.eCommerce.utils;

import kz.abai.eCommerce.dto.GoodDto;
import kz.abai.eCommerce.entities.CategoryEntity;
import kz.abai.eCommerce.entities.GoodsEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class GoodsUtils {
    public GoodsEntity addNewGoodEntity(String name,String slug, String description, String imgUrl, CategoryEntity category, BigDecimal cost) {
        return GoodsEntity.builder()
                .goodId(UUID.randomUUID().toString())
                .name(name)
                .slug(slug)
                .description(description)
                .imgUrl(imgUrl)
                .category(category)
                .cost(cost)
                .build();
    }
}
