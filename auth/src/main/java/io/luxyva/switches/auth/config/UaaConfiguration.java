package io.luxyva.switches.auth.config;

import io.luxyva.switches.framework.AuthoritiesConstants;
import io.luxyva.switches.framework.config.SwitchesProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableAuthorizationServer
public class UaaConfiguration extends AuthorizationServerConfigurerAdapter implements ApplicationContextAware {

    private static final int MIN_ACCESS_TOKEN_VALIDITY_SECS = 60;

    private ApplicationContext applicationContext;

    private final SwitchesProperties switchesProperties;

    private final UaaProperties uaaProperties;

    private final PasswordEncoder passwordEncoder;

    public UaaConfiguration(SwitchesProperties switchesProperties, UaaProperties uaaProperties, PasswordEncoder passwordEncoder) {
        this.switchesProperties = switchesProperties;
        this.uaaProperties = uaaProperties;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @EnableResourceServer
    public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        private final TokenStore tokenStore;

        private final SwitchesProperties switchesProperties;

        private final CorsFilter corsFilter;

        public ResourceServerConfiguration(TokenStore tokenStore, SwitchesProperties switchesProperties, CorsFilter corsFilter) {
            this.tokenStore = tokenStore;
            this.switchesProperties = switchesProperties;
            this.corsFilter = corsFilter;
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .exceptionHandling()
                    .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                    .and()
                    .csrf()
                    .disable()
                    .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                    .headers()
                    .frameOptions()
                    .disable()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/register").permitAll()
                    .antMatchers("/api/activate").permitAll()
                    .antMatchers("/api/authenticate").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .antMatchers("/management/health").permitAll()
                    .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
                    .antMatchers("/v3/api-docs/**").permitAll()
                    .antMatchers("/swagger-resources/configuration/ui").permitAll()
                    .antMatchers("/swagger-ui/index.html").hasAuthority(AuthoritiesConstants.ADMIN);
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId("switches-auth").tokenStore(tokenStore);
        }

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        int accessTokenValidity = uaaProperties.getWebClientConfiguration().getAccessTokenValidityInSeconds();
        accessTokenValidity = Math.max(accessTokenValidity, MIN_ACCESS_TOKEN_VALIDITY_SECS);
        int refreshTokenValidity = uaaProperties.getWebClientConfiguration().getRefreshTokenValidityInSecondsForRememberMe();
        refreshTokenValidity = Math.max(refreshTokenValidity, accessTokenValidity);

        clients.inMemory()
                .withClient(uaaProperties.getWebClientConfiguration().getClient())
                .secret(passwordEncoder.encode(uaaProperties.getWebClientConfiguration().getSecret()))
                .scopes("openid")
                .autoApprove(true)
                .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code")
                .accessTokenValiditySeconds(accessTokenValidity)
                .refreshTokenValiditySeconds(refreshTokenValidity)
                .and()
                .withClient(switchesProperties.getSecurity().getClientAuthorization().getClientId())
                .secret(passwordEncoder.encode(switchesProperties.getSecurity().getClientAuthorization().getClientSecret()))
                .scopes("web-app")
                .authorities("ROLE_ADMIN")
                .autoApprove(true)
                .authorizedGrantTypes("client_credentials")
                .accessTokenValiditySeconds((int) switchesProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds())
                .refreshTokenValiditySeconds((int) switchesProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        Collection<TokenEnhancer> tokenEnhancers = applicationContext.getBeansOfType(TokenEnhancer.class).values();
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(new ArrayList<>(tokenEnhancers));
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .reuseRefreshTokens(false);

    }

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
                new ClassPathResource(uaaProperties.getKeyStore().getName()), uaaProperties.getKeyStore().getPassword().toCharArray())
                .getKeyPair(uaaProperties.getKeyStore().getAlias());
        converter.setKeyPair(keyPair);
        return converter;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
}
