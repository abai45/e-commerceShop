package kz.abai.eCommerce.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.abai.eCommerce.domain.Token;
import kz.abai.eCommerce.domain.TokenData;
import kz.abai.eCommerce.dto.ClientDto;
import kz.abai.eCommerce.enums.TokenType;

import java.util.Optional;
import java.util.function.Function;

public interface JwtService {
    String createToken(ClientDto client, Function<Token, String> tokenFunction);
    Optional<String> extractToken(HttpServletRequest request, String tokenType);
    void addCookie(HttpServletResponse response, ClientDto client, TokenType tokenType);
    <T> T getRTokenData(String token, Function<TokenData, T> tokenTFunction);
}
