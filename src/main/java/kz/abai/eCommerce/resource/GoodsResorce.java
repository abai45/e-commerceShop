package kz.abai.eCommerce.resource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kz.abai.eCommerce.domain.Response;
import kz.abai.eCommerce.dto.ProductDto;
import kz.abai.eCommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.util.Collections.emptyMap;
import static kz.abai.eCommerce.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsResorce {
    private final ProductService goodsService;

    @PostMapping("/addnew")
    ResponseEntity<Response> addNewGood(@RequestBody @Valid ProductDto productDto, HttpServletRequest request) {
        var good = goodsService.addGood(productDto.getName(), productDto.getDescription(), productDto.getImgUrl(), productDto.getCategoryName(), productDto.getCost());
        return ResponseEntity.ok().body(getResponse(request, Map.of("good", good), "New good added", OK));
    }
    @PostMapping("/update/{slug}")
    public ResponseEntity<Response> updateGood(@PathVariable String slug, @RequestBody @Valid ProductDto productDto, HttpServletRequest request) {
        var good = goodsService.updateGoodInfo(slug, productDto.getName(), productDto.getDescription(),  productDto.getImgUrl(), productDto.getCategoryName(), productDto.getCost());
        return ResponseEntity.ok().body(getResponse(request, Map.of("good", good), "Good updated", OK));
    }
    @PostMapping("/delete/{slug}")
    public ResponseEntity<Response> deleteGood(@PathVariable String slug, HttpServletRequest request) {
        goodsService.deleteGood(slug);
        return ResponseEntity.ok().body(getResponse(request, emptyMap(), "Good deleted", OK));
    }
    @GetMapping("/{slug}")
    public ResponseEntity<Response> getGood(@PathVariable String slug, HttpServletRequest request) {
        var good = goodsService.getGoodBySlug(slug);
        return ResponseEntity.ok().body(getResponse(request, Map.of("good", good), "Good retrieved", OK));
    }
    @GetMapping()
    public ResponseEntity<Response> getAllGoods(HttpServletRequest request) {
        var goodsList = goodsService.getAllGoods();
        return ResponseEntity.ok().body(getResponse(request, Map.of("goodsList", goodsList), "All goods retrieved", OK));
    }
    @GetMapping("/{categoryName}")
    public ResponseEntity<Response> getAllGoodsByCategory(@PathVariable String categoryName, HttpServletRequest request) {
        var goodsList = goodsService.getAllGoodsByCategoryName(categoryName);
        return ResponseEntity.ok().body(getResponse(request, Map.of("goodsList", goodsList),"All goods by category retrived", OK));
    }
}
