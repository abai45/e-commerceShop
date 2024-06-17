package kz.abai.eCommerce.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "sub_category")
public class SubCategoryEntity extends Auditable{
    @NotNull
    private String name;
    @ManyToOne
    private CategoryEntity category;
    @OneToMany(mappedBy = "subCategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<GoodsEntity> goods;
}
