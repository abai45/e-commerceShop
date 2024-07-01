package kz.abai.eCommerce.domain;

import kz.abai.eCommerce.dto.ClientDto;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class ApiAuthentication extends AbstractAuthenticationToken {
    private static final String PASSWORD_PROTECTED = "[PASSWORD_PROTECTED]";
    private static final String EMAIL_PROTECTED = "[EMAIL_PROTECTED]";
    private ClientDto client;
    private String email;
    private String password;
    private boolean authenticated;

    private ApiAuthentication(String email, String password) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.password = PASSWORD_PROTECTED;
        this.email = EMAIL_PROTECTED;
        this.authenticated = false;
    }

    private ApiAuthentication(ClientDto client, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.client = client;
        this.password = PASSWORD_PROTECTED;
        this.email = EMAIL_PROTECTED;
        this.authenticated = true;
    }

    public static ApiAuthentication unauthenticated(String email, String password) {
        return new ApiAuthentication(email, password);
    }

    public static ApiAuthentication authenticated(ClientDto client, Collection<? extends GrantedAuthority> authorities) {
        return new ApiAuthentication(client, authorities);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        throw new RuntimeException("You cannot set authentication");
    }

    @Override
    public Object getCredentials() {
        return PASSWORD_PROTECTED;
    }

    @Override
    public Object getPrincipal() {
        return this.client;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }
}
