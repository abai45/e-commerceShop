package kz.abai.eCommerce.security;

import kz.abai.eCommerce.handler.ApiAccessDeniedHandler;
import kz.abai.eCommerce.handler.ApiAuthenticationEntityPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class FilterChainConfiguration {

    private final ApiAccessDeniedHandler apiAccessDeniedHandler;
    private final ApiAuthenticationEntityPoint apiAuthenticationEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(daoAuthenticationProvider);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.disable())
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .accessDeniedHandler(apiAccessDeniedHandler)
                                .authenticationEntryPoint(apiAuthenticationEntryPoint)
                )
                .authorizeHttpRequests(request ->
                        request.requestMatchers("client/register").permitAll()
                                .requestMatchers("category/newcategory").permitAll() //временно
                                .requestMatchers("category/subcategory").permitAll() //временно
                                .requestMatchers("category/remove").permitAll() //временно
                                .anyRequest().authenticated()
                );
        return http.build();
    }
}
