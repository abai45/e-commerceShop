package kz.abai.eCommerce.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.abai.eCommerce.domain.Token;
import kz.abai.eCommerce.domain.TokenData;
import kz.abai.eCommerce.dto.ClientDto;
import kz.abai.eCommerce.entities.ClientEntity;
import kz.abai.eCommerce.enums.TokenType;
import kz.abai.eCommerce.repository.ClientRepository;
import kz.abai.eCommerce.security.JwtConfiguration;
import kz.abai.eCommerce.service.ClientService;
import kz.abai.eCommerce.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static kz.abai.eCommerce.constants.Constants.*;
import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl extends JwtConfiguration implements JwtService {
    private final ClientRepository clientRepository;
    private final ClientService clientService;

    private final Supplier<SecretKey> key = () -> Keys.hmacShaKeyFor(Decoders.BASE64.decode(getSecret()));
    private final Function<String, Claims> claimsFunction = token ->
            Jwts.parser()
                    .verifyWith(key.get())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

    private final Function<String, String> subject = token ->
            getClaimsValue(token,Claims::getSubject);

    private final BiFunction<HttpServletRequest, String, Optional<String>> extractToken = (request, cookieName) ->
            Optional.of(stream(request.getCookies() == null ? new Cookie[]{new Cookie(EMPTY_VALUE, EMPTY_VALUE)} : request.getCookies())
                    .filter(cookie -> Objects.equals(cookieName, cookie.getName()))
                    .map(Cookie::getValue)
                    .findAny())
                    .orElse(empty());

    public Function<String, List<GrantedAuthority>> authorities = token ->
            commaSeparatedStringToAuthorityList(new StringJoiner(AUTHORITY_DELIMITER).add(claimsFunction.apply(token).get(AUTHORITIES, String.class))
                    .add(ROLE_PREFIX + claimsFunction.apply(token).get(ROLE, String.class)).toString());
    @Override
    public String createToken(ClientDto client, Function<Token, String> tokenFunction) {
        var clientEntity = getClientByDto(client);
        
    }

    @Override
    public Optional<String> extractToken(HttpServletRequest request, String tokenType) {
        return empty();
    }

    @Override
    public void addCookie(HttpServletResponse response, ClientDto client, TokenType tokenType) {

    }

    @Override
    public <T> T getRTokenData(String token, Function<TokenData, T> tokenTFunction) {
        return null;
    }

    private ClientEntity getClientByDto(ClientDto client) {
        return clientRepository.findByEmail(client.getEmail())
                .orElseThrow(() -> new RuntimeException("User By email not found"));
    }
}
