package kz.abai.eCommerce.service.impl;

import kz.abai.eCommerce.dto.GoodDto;
import kz.abai.eCommerce.entities.CategoryEntity;
import kz.abai.eCommerce.mapper.GoodsMapper;
import kz.abai.eCommerce.repository.CategoryRepository;
import kz.abai.eCommerce.repository.GoodsRepository;
import kz.abai.eCommerce.service.GoodsService;
import kz.abai.eCommerce.utils.GoodsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsUtils goodsUtils;
    private final CategoryRepository categoryRepository;
    private final GoodsMapper goodsMapper;
    @Override
    public GoodDto addGood(String name, String description, String imgUrl, String categoryName, BigDecimal cost) {
        var categoryEntity = getCategoryByName(categoryName);
        var goodEntity = goodsUtils.addNewGoodEntity(name, description, imgUrl, categoryEntity, cost);
        return goodsMapper.toDto(goodEntity);
    }

    private CategoryEntity getCategoryByName(String categoryName) {
        return categoryRepository.findByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new RuntimeException("Category by name not found"));
    }
}
