package kz.abai.eCommerce.resource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kz.abai.eCommerce.domain.Response;
import kz.abai.eCommerce.dto.CategoryRequestDto;
import kz.abai.eCommerce.dto.ClientDto;
import kz.abai.eCommerce.dto.SubCategoryRequestDto;
import kz.abai.eCommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static kz.abai.eCommerce.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/category")
public class CategoryResource {
    private CategoryService categoryService;
    @PostMapping("/newcategory")
    public ResponseEntity<Response> newCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto, HttpServletRequest request) {
        var category = categoryService.newCategory(categoryRequestDto.getName(), categoryRequestDto.getDescription());
        return ResponseEntity.ok().body(getResponse(request, Map.of("category", category), "New category created", OK));
    }
    @PostMapping("/subcategory")
    public ResponseEntity<Response> newSubCategory(@RequestBody @Valid SubCategoryRequestDto subCategoryRequestDto, HttpServletRequest request) {
        var category = categoryService.newSubCategory(subCategoryRequestDto.getName(), subCategoryRequestDto.getDescription(), subCategoryRequestDto.getParentCategoryName());
        return ResponseEntity.ok().body(getResponse(request, Map.of("subcategory", category), "New subcategory created", OK));
    }
}
