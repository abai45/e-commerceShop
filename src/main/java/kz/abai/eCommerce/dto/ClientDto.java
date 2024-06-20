package kz.abai.eCommerce.dto;

import kz.abai.eCommerce.entities.RoleEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
