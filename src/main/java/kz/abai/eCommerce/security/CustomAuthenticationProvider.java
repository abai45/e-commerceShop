package kz.abai.eCommerce.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var client = (UsernamePasswordAuthenticationToken) authentication;
        var clientFromDb = userDetailsService.loadUserByUsername((String) client.getPrincipal());
        var password = (String) client.getCredentials();
        if(password.equals(clientFromDb.getPassword())) {
            return UsernamePasswordAuthenticationToken.authenticated(clientFromDb, "[PROTECTED PASSWORD]", clientFromDb.getAuthorities());
        }
        throw new BadCredentialsException("Unable to login");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
