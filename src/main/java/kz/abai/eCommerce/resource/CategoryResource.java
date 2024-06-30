package kz.abai.eCommerce.resource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kz.abai.eCommerce.domain.Response;
import kz.abai.eCommerce.dto.CategoryRemoveRequestDto;
import kz.abai.eCommerce.dto.CategoryRequestDto;
import kz.abai.eCommerce.dto.CategoryUpdateDto;
import kz.abai.eCommerce.dto.SubCategoryRequestDto;
import kz.abai.eCommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.util.Collections.emptyMap;
import static kz.abai.eCommerce.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryResource {
    private final CategoryService categoryService;

    @PostMapping("/newcategory")
    public ResponseEntity<Response> newCategory(@RequestBody CategoryRequestDto categoryRequestDto, HttpServletRequest request) {
        var category = categoryService.newCategory(categoryRequestDto.getName(), categoryRequestDto.getDescription());
        return ResponseEntity.ok().body(getResponse(request, Map.of("Category created", category), "New category created", OK));
    }
    @PostMapping("/subcategory")
    public ResponseEntity<Response> newSubCategory(@RequestBody @Valid SubCategoryRequestDto subCategoryRequestDto, HttpServletRequest request) {
        var category = categoryService.newSubCategory(subCategoryRequestDto.getName(), subCategoryRequestDto.getDescription(), subCategoryRequestDto.getParentCategoryName());
        return ResponseEntity.ok().body(getResponse(request, Map.of("Subcategory created", category), "New subcategory created", OK));
    }
    @PostMapping("/update")
    public ResponseEntity<Response> updateCategory(@RequestBody @Valid CategoryUpdateDto categoryUpdateDto, HttpServletRequest request) {
        var category = categoryService.updateCategory(categoryUpdateDto.getName(), categoryUpdateDto.getNewName(), categoryUpdateDto.getDescription(), categoryUpdateDto.getParentCategory(), categoryUpdateDto.getStatus());
        return ResponseEntity.ok().body(getResponse(request, Map.of("category", category), "Category updated", OK));
    }
    @PostMapping("/remove")
    public ResponseEntity<Response> removeCategory(@RequestBody @Valid CategoryRemoveRequestDto categoryRemoveRequestDto, HttpServletRequest request) {
        categoryService.deleteCategory(categoryRemoveRequestDto.getName());
        return ResponseEntity.ok().body(getResponse(request, emptyMap(), "Category was deleted", OK));
    }
}
