package kz.abai.eCommerce.resource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kz.abai.eCommerce.domain.Response;
import kz.abai.eCommerce.dto.GoodDto;
import kz.abai.eCommerce.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static kz.abai.eCommerce.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsResorce {
    private final GoodsService goodsService;

    @PostMapping("/addnew")
    ResponseEntity<Response> addNewGood(@RequestBody @Valid GoodDto goodDto, HttpServletRequest request) {
        var good = goodsService.addGood(goodDto.getName(), goodDto.getDescription(), goodDto.getImgUrl(), goodDto.getCategoryName(), goodDto.getCost());
        return ResponseEntity.ok().body(getResponse(request, Map.of("good", good), "New good added", OK));
    }
}