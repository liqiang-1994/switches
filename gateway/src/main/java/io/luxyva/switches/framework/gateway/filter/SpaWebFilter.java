package io.luxyva.switches.framework.gateway.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class SpaWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        String path = serverWebExchange.getRequest().getURI().getPath();
        if (!path.startsWith("/api") && !path.startsWith("/management")
                && !path.startsWith("/services") && !path.startsWith("/swagger") && !path.startsWith("/v3/api-docs")
                && path.matches("[^\\\\.]*")) {
            return webFilterChain.filter(
                    serverWebExchange.mutate().request(serverWebExchange.getRequest().mutate().path("/index.html").build())
                            .build());
        }
        return webFilterChain.filter(serverWebExchange);
    }
}
