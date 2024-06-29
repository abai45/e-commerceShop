package kz.abai.eCommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodDto {
    @NotNull
    private String name;
    private String slug;
    private String description;
    private String imgUrl;
    @NotNull
    private String categoryName;
    @NotNull
    private BigDecimal cost;
}
