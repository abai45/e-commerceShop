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
import java.util.Optional;

@Service
public class GoodsServiceImpl implements GoodsService {
    private static final String DEFAULT_IMG_URL = "https://cdn.vectorstock.com/i/500p/65/30/default-image-icon-missing-picture-page-vector-40546530.jpg";

    private GoodsRepository goodsRepository;
    private GoodsUtils goodsUtils;
    private CategoryRepository categoryRepository;
    private GoodsMapper goodsMapper;

    public GoodsServiceImpl(GoodsRepository goodsRepository, GoodsUtils goodsUtils, CategoryRepository categoryRepository, GoodsMapper goodsMapper) {
        this.goodsRepository = goodsRepository;
        this.goodsUtils = goodsUtils;
        this.categoryRepository = categoryRepository;
        this.goodsMapper = goodsMapper;
    }
    @Override
    public GoodDto addGood(String name, String description, String imgUrl, String categoryName, BigDecimal cost) {
        var categoryEntity = getCategoryByName(categoryName);
        imgUrl = Optional.ofNullable(imgUrl).filter(url -> !url.isEmpty()).orElse(DEFAULT_IMG_URL);
        
        var goodEntity = goodsRepository.save(goodsUtils.addNewGoodEntity(name, description, imgUrl, categoryEntity, cost));
        return goodsMapper.toDto(goodEntity);
    }

    private CategoryEntity getCategoryByName(String categoryName) {
        return categoryRepository.findByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new RuntimeException("Category by name not found"));
    }
}
