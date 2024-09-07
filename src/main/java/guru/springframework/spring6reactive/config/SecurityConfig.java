package guru.springframework.spring6reactive.config;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 27/08/2024 - 14:11
 * @since jdk 1.21
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityWebFilterChain actuatorSecurityFilterChain(ServerHttpSecurity http) {
        http.securityMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeExchange(authorize -> authorize.anyExchange().permitAll());

        return http.build();
    }

    @Bean
    @Order(2)
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec.anyExchange().authenticated())
                .oauth2ResourceServer(oAuth2ResourceServerSpec ->  oAuth2ResourceServerSpec.jwt(Customizer.withDefaults()));

        return http.build();
    }

}
