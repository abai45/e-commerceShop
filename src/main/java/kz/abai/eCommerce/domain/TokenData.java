package kz.abai.eCommerce.domain;

import io.jsonwebtoken.Claims;
import kz.abai.eCommerce.dto.ClientDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Builder
@Getter
@Setter
public class TokenData {
    private ClientDto client;
    private Claims claims;
    private boolean valid;
    private List<GrantedAuthority> authorities;
}
