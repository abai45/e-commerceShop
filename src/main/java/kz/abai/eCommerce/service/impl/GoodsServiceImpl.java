package kz.abai.eCommerce.service.impl;

import kz.abai.eCommerce.dto.GoodDto;
import kz.abai.eCommerce.entities.CategoryEntity;
import kz.abai.eCommerce.entities.GoodsEntity;
import kz.abai.eCommerce.mapper.GoodsMapper;
import kz.abai.eCommerce.repository.CategoryRepository;
import kz.abai.eCommerce.repository.GoodsRepository;
import kz.abai.eCommerce.service.GoodsService;
import kz.abai.eCommerce.service.SlugService;
import kz.abai.eCommerce.utils.GoodsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.abai.eCommerce.constants.Constants.DEFAULT_IMG_URL;

@Service
public class GoodsServiceImpl implements GoodsService {

    private GoodsRepository goodsRepository;
    private GoodsUtils goodsUtils;
    private CategoryRepository categoryRepository;
    private GoodsMapper goodsMapper;
    private SlugService slugService;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository,
                            GoodsUtils goodsUtils,
                            CategoryRepository categoryRepository,
                            GoodsMapper goodsMapper,
                            SlugService slugService) {
        this.goodsRepository = goodsRepository;
        this.goodsUtils = goodsUtils;
        this.categoryRepository = categoryRepository;
        this.goodsMapper = goodsMapper;
        this.slugService = slugService;
    }
    @Override
    public GoodDto addGood(String name, String description, String imgUrl, String categoryName, BigDecimal cost) {
        var categoryEntity = getCategoryByName(categoryName);
        imgUrl = Optional.ofNullable(imgUrl).filter(url -> !url.isEmpty()).orElse(DEFAULT_IMG_URL);

        String slug = slugService.createSlug(name);
        var goodEntity = goodsRepository.save(goodsUtils.addNewGoodEntity(name, slug, description, imgUrl, categoryEntity, cost));
        return goodsMapper.toDto(goodEntity);
    }

    @Override
    public void deleteGood(String slug) {
        var goodEntity = getGoodEntityBySlug(slug);
        goodsRepository.delete(goodEntity);
    }

    @Override
    public GoodDto updateGoodInfo(String slug, String name, String description, String imgUrl, String categoryName, BigDecimal cost) {
        var goodEntity = getGoodEntityBySlug(slug);
        var categoryEntity = getCategoryByName(categoryName);
        goodEntity.setName(name);
        goodEntity.setDescription(description);
        goodEntity.setImgUrl(imgUrl);
        goodEntity.setCategory(categoryEntity);
        goodEntity.setCost(cost);
        goodEntity.setSlug(slugService.createSlug(name));
        goodsRepository.save(goodEntity);
        return goodsMapper.toDto(goodEntity);
    }

    @Override
    public List<GoodDto> getAllGoods() {
        return goodsRepository.findAll()
                .stream()
                .map(goodsEntity -> goodsMapper.toDto(goodsEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<GoodDto> getAllGoodsByCategoryName(String category) {
        var categoryEntity = getCategoryByName(category);
        return goodsRepository.findAllByCategory(categoryEntity)
                .stream()
                .map(goodsEntity -> goodsMapper.toDto(goodsEntity))
                .collect(Collectors.toList());
    }

    @Override
    public GoodDto getGoodBySlug(String slug) {
        var goodEntity = getGoodEntityBySlug(slug);
        return goodsMapper.toDto(goodEntity);
    }

    private GoodsEntity getGoodEntityBySlug(String slug) {
        return goodsRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Good by slug not found"));
    }

    private CategoryEntity getCategoryByName(String categoryName) {
        return categoryRepository.findByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new RuntimeException("Category by name not found"));
    }
}
