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
    @Column(nullable = false)
    private String name;
    @Column(length = 1000)
    private String description;
    @Enumerated(EnumType.STRING)
    private CategoryStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryEntity parentCategory;
    @OneToMany(mappedBy = "parentCategory",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryEntity> subCategories = new ArrayList<>();

    public void addSubCategory(CategoryEntity category) {
        subCategories.add(category);
        category.setParentCategory(this);
    }

    public void removeSubCategory(CategoryEntity category) {
        subCategories.remove(category);
        category.setParentCategory(null);
    }
}
