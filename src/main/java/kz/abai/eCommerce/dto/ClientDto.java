package kz.abai.eCommerce.dto;

import kz.abai.eCommerce.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String imageUrl;
    private BigDecimal balance;
    private LocalDateTime lastLogin;
    private Integer loginAttempts;
    private LocalDateTime lockTime;
    private RoleEntity roles;
}
