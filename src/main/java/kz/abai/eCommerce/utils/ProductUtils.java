package kz.abai.eCommerce.utils;

import kz.abai.eCommerce.entities.CategoryEntity;
import kz.abai.eCommerce.entities.ProductEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

import static kz.abai.eCommerce.constants.Constants.CODE_LENGTH;

@Component
public class ProductUtils {
    public ProductEntity addNewGoodEntity(String name, String slug, String description, String imgUrl, CategoryEntity category, BigDecimal cost) {
        var goodCode = generateGoodCode();
        var slugGood = slug+"-"+goodCode;
        return ProductEntity.builder()
                .goodCode(goodCode)
                .name(name)
                .slug(slugGood)
                .description(description)
                .imgUrl(imgUrl)
                .category(category)
                .cost(cost)
                .build();
    }
    private String generateGoodCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for(int i = 0; i < CODE_LENGTH; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
