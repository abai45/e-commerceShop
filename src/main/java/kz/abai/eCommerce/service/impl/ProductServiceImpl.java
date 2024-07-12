package kz.abai.eCommerce.service.impl;

import kz.abai.eCommerce.dto.ProductDto;
import kz.abai.eCommerce.entities.CategoryEntity;
import kz.abai.eCommerce.entities.ProductEntity;
import kz.abai.eCommerce.mapper.ProductMapper;
import kz.abai.eCommerce.repository.CategoryRepository;
import kz.abai.eCommerce.repository.GoodsRepository;
import kz.abai.eCommerce.service.ProductService;
import kz.abai.eCommerce.service.SlugService;
import kz.abai.eCommerce.utils.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static kz.abai.eCommerce.constants.Constants.DEFAULT_IMG_URL;

@Service
public class ProductServiceImpl implements ProductService {

    private GoodsRepository goodsRepository;
    private ProductUtils productUtils;
    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;
    private SlugService slugService;

    @Autowired
    public ProductServiceImpl(GoodsRepository goodsRepository,
                              ProductUtils productUtils,
                              CategoryRepository categoryRepository,
                              ProductMapper productMapper,
                              SlugService slugService) {
        this.goodsRepository = goodsRepository;
        this.productUtils = productUtils;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.slugService = slugService;
    }
    @Override
    public ProductDto addGood(String name, String description, String imgUrl, String categoryName, BigDecimal cost) {
        var categoryEntity = getCategoryByName(categoryName);
        imgUrl = Optional.ofNullable(imgUrl).filter(url -> !url.isEmpty()).orElse(DEFAULT_IMG_URL);

        String slug = slugService.createSlug(name);
        var goodEntity = goodsRepository.save(productUtils.addNewGoodEntity(name, slug, description, imgUrl, categoryEntity, cost));
        return productMapper.toDto(goodEntity);
    }

    @Override
    public void deleteGood(String slug) {
        var goodEntity = getGoodEntityBySlug(slug);
        goodsRepository.delete(goodEntity);
    }

    @Override
    public ProductDto updateGoodInfo(String slug, String name, String description, String imgUrl, String categoryName, BigDecimal cost) {
        var goodEntity = getGoodEntityBySlug(slug);
        var categoryEntity = getCategoryByName(categoryName);
        goodEntity.setName(name);
        goodEntity.setDescription(description);
        goodEntity.setImgUrl(imgUrl);
        goodEntity.setCategory(categoryEntity);
        goodEntity.setCost(cost);
        goodEntity.setSlug(slugService.createSlug(name));
        goodsRepository.save(goodEntity);
        return productMapper.toDto(goodEntity);
    }

    @Override
    public List<ProductDto> getAllGoods() {
        return goodsRepository.findAll()
                .stream()
                .map(goodsEntity -> productMapper.toDto(goodsEntity))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllGoodsByCategoryName(String category) {
        var categoryEntity = getCategoryByName(category);
        return goodsRepository.findAllByCategory(categoryEntity)
                .stream()
                .map(goodsEntity -> productMapper.toDto(goodsEntity))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getGoodBySlug(String slug) {
        var goodEntity = getGoodEntityBySlug(slug);
        return productMapper.toDto(goodEntity);
    }

    private ProductEntity getGoodEntityBySlug(String slug) {
        return goodsRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Good by slug not found"));
    }

    private CategoryEntity getCategoryByName(String categoryName) {
        return categoryRepository.findByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new RuntimeException("Category by name not found"));
    }
}
