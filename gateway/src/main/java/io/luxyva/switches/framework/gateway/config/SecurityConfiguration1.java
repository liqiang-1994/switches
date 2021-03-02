//package io.luxyva.switches.framework.gateway.config;
//
//import com.nimbusds.oauth2.sdk.auth.JWTAuthentication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.cglib.core.Converter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.oidc.userinfo.OidcReactiveOAuth2UserService;
//import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
//import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2TokenValidator;
//import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
//import org.springframework.security.oauth2.jwt.*;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.WebFilterExchange;
//import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
//import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
//import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
//import org.springframework.security.web.server.csrf.CsrfWebFilter;
//import org.springframework.security.web.server.header.ReferrerPolicyServerHttpHeadersWriter;
//import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;
//import org.springframework.security.web.server.util.matcher.OrServerWebExchangeMatcher;
//import org.springframework.util.StringUtils;
//import org.zalando.problem.spring.webflux.advice.security.SecurityProblemSupport;
//import reactor.core.publisher.Mono;
//
//import java.util.Set;
//
//import static org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.pathMatchers;
//
//
//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
//public class SecurityConfiguration1 {
//
//  private final SecurityProblemSupport problemSupport;
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public ReactiveAuthenticationManager reactiveAuthenticationManager() {
//    UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
//    authenticationManager.setPasswordEncoder(passwordEncoder());
//    return authenticationManager;
//  }
//
//  public SecurityConfiguration1(SecurityProblemSupport problemSupport) {
//    this.problemSupport = problemSupport;
//  }
//
////  @Bean
////  public MapReactiveUserDetailsService userDetailsService(SecurityProperties properties) {
////    SecurityProperties.User user = properties.getUser();
////    UserDetails userDetails = User
////      .withUsername(user.getName())
////      .password("{noop}"+user.getPassword())
////      .roles(StringUtils.toStringArray(user.getRoles()))
////      .build();
////    return new MapReactiveUserDetailsService(userDetails);
////  }
//
////  @Bean
////  public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService) {
////    return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
////  }
//  @Bean
//  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//    http
//      .securityMatcher(new NegatedServerWebExchangeMatcher(new OrServerWebExchangeMatcher(
//        pathMatchers("/app/**", "content/**", "/swagger-ui/**", "/test/**"),
//        pathMatchers(HttpMethod.OPTIONS, "/**")
//      )))
//      .csrf()
//      .csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
//      .and()
//      .addFilterAt(new CookieCsrfFilter(), SecurityWebFiltersOrder.REACTOR_CONTEXT)
//    .authenticationManager(reactiveAuthenticationManager())
//      .exceptionHandling()
//      .accessDeniedHandler(problemSupport)
//      .authenticationEntryPoint(problemSupport)
//      .and()
//      .headers()
//      .contentSecurityPolicy("default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:")
//      .and()
//      .referrerPolicy(ReferrerPolicyServerHttpHeadersWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
//      .and()
//      .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'")
//      .and()
//      .frameOptions().disable()
//      .and()
//      .authorizeExchange()
//      .pathMatchers("/api/auth-info").permitAll()
//      .pathMatchers("/api/**").authenticated()
//      .pathMatchers("/services/**", "/swagger-resources/**", "/v2/api-docs").authenticated()
//      .pathMatchers("/management/health").permitAll()
//      .pathMatchers("/management/info").permitAll()
//      .pathMatchers("/management/prometheus").permitAll()
//      .pathMatchers("/management/**").hasAuthority("ADMIN");
//
//    http.oauth2Login()
//      .authenticationSuccessHandler(this::onAuthenticationSuccess)
//      .and()
//      .oauth2ResourceServer()
//      .jwt()
//      .jwtAuthenticationConverter(grantedAuthoritiesExtractor())
//      .and()
//      .and()
//      .oauth2Client();
//    return http.build();
//  }
//
//  Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
//    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new JwtAuthorityExtractor());
//    return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
//  }
//
//  @Bean
//  public ReactiveOAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
//    final OidcReactiveOAuth2UserService delegate = new OidcReactiveOAuth2UserService();
//
//    return (userRequest) -> {
//      // Delegate to the default implementation for loading a user
//      return delegate.loadUser(userRequest).map(user -> {
//        Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
//
//        user.getAuthorities().forEach(authority -> {
//          if (authority instanceof OidcUserAuthority) {
//            OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) authority;
//            mappedAuthorities.addAll(SecurityUtils.extractAuthorityFromClaims(oidcUserAuthority.getUserInfo().getClaims()));
//          }
//        });
//
//        return new DefaultOidcUser(mappedAuthorities, user.getIdToken(), user.getUserInfo());
//      });
//    };
//  }
//
//  @Bean
//  ReactiveJwtDecoder jwtDecoder() {
//    NimbusReactiveJwtDecoder jwtDecoder = (NimbusReactiveJwtDecoder) ReactiveJwtDecoders.fromOidcIssuerLocation(issuerUri);
//
//    OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(jHipsterProperties.getSecurity().getOauth2().getAudience());
//    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
//    OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
//
//    jwtDecoder.setJwtValidator(withAudience);
//
//    return jwtDecoder;
//  }
//
//  private ServerAuthenticationSuccessHandler serverAuthenticationSuccessHandler = new RedirectServerAuthenticationSuccessHandler();
//
//  private Mono<Void> onAuthenticationSuccess(WebFilterExchange exchange, Authentication authentication) {
//    return serverAuthenticationSuccessHandler.onAuthenticationSuccess(exchange, authentication)
//      .thenReturn(authentication.getPrincipal())
//      .filter(principal -> principal instanceof OidcUser)
//      .map(principal -> ((OidcUser) principal).getPreferredUsername())
//      .filter(login->!"Anoymonus".equals(login))
//      .then();
//  }
//
//}
