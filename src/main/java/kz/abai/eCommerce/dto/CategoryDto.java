package kz.abai.eCommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDto {
    private String name;
    private String description;
    private String status;
    private String parentCategory;
    private String subCategories;
}
