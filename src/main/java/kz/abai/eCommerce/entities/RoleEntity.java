package kz.abai.eCommerce.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import kz.abai.eCommerce.enums.AuthorityEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RoleEntity extends Auditable{
    @Column(nullable = false, unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private AuthorityEnum authorities;
}
