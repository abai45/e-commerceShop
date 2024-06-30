package kz.abai.eCommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateDto {
    private String name;
    private String newName;
    private String description;
    private String parentCategory;
    private String status;
}
