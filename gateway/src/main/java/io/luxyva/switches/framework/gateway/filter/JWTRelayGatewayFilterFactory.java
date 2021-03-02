package io.luxyva.switches.framework.gateway.filter;

import io.luxyva.switches.framework.AuthoritiesConstants;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JWTRelayGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private final TokenProvider tokenProvider;

    public JWTRelayGatewayFilterFactory(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String token = extractJWTToken(exchange.getRequest());
            if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
                ServerHttpRequest request = exchange.getRequest().mutate()
                        .header(AuthoritiesConstants.AUTHORIZATION_HEADER, "Bearer " + token)
                        .build();
                return chain.filter(exchange.mutate().request(request).build());
            }
            return chain.filter(exchange);
        };
    }

    private String extractJWTToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(AuthoritiesConstants.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw new IllegalArgumentException("Invalid token in Authorization header");
    }
}
