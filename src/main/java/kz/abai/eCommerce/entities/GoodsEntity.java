package kz.abai.eCommerce.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
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
    @JoinTable(
            name = "goods_category",
            joinColumns = @JoinColumn(
                    name = "good_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id"))
    private CategoryEntity category;
    private BigDecimal cost;
}
