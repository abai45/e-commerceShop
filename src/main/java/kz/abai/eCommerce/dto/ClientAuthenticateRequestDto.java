package kz.abai.eCommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientAuthenticateRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
