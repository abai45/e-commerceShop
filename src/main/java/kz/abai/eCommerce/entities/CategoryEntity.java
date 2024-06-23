package kz.abai.eCommerce.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kz.abai.eCommerce.enums.CategoryStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CategoryEntity extends Auditable{
    @NotNull
    private String name;
    @Column(length = 1000)
    private String description;
    private CategoryStatus status;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntity parentCategory;
    @OneToMany(mappedBy = "parentCategory",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CategoryEntity> subCategories = new ArrayList<>();
}
