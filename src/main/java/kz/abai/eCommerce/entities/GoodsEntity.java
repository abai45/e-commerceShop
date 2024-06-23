package kz.abai.eCommerce.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "goods")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class GoodsEntity extends Auditable{
    @NotNull
    private String goodId;
    @NotNull
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
        private CategoryEntity category;
    private BigDecimal cost;
}
