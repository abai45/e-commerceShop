package kz.abai.eCommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attributes")
public class AttributeEntity extends Auditable{
    @Column(nullable = false)
    private String nameKZ;
    @Column(nullable = false)
    private String nameRU;
    @Column(nullable = false)
    private String nameEN;
    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AttributeValueEntity> values = new ArrayList<>();
    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CategoryAttributeEntity> categoryAttributes = new ArrayList<>();
}
