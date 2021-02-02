package io.luxyva.switches.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Properties配置类
 */
@Component
@ConfigurationProperties(prefix = "switches", ignoreUnknownFields = false)
public class SwitchesProperties {

  private final Mail mail = new Mail();

  private final Security security = new Security();

  private final Swagger swagger = new Swagger();

  private final Metrics metrics = new Metrics();

  private final Logging logging = new Logging();

  private final CorsConfiguration cors = new CorsConfiguration();

  private final Social social = new Social();

  private final Gateway gateway = new Gateway();

  private final ClientApp clientApp = new ClientApp();

  private final Registry registry = new Registry();

  public Mail getMail() {
    return mail;
  }

  public Security getSecurity() {
    return security;
  }

  public Swagger getSwagger() {
    return swagger;
  }

  public Metrics getMetrics() {
    return metrics;
  }

  public Logging getLogging() {
    return logging;
  }

  public CorsConfiguration getCors() {
    return cors;
  }

  public Social getSocial() {
    return social;
  }

  public Gateway getGateway() {
    return gateway;
  }

  public ClientApp getClientApp() {
    return clientApp;
  }

  public Registry getRegistry() {
    return registry;
  }

  public static class Mail {

    private boolean enabled = SwitchesDefaults.Mail.enabled;

    private String from = SwitchesDefaults.Mail.from;

    private String baseUrl = SwitchesDefaults.Mail.baseUrl;

    public boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }

    public String getFrom() {
      return from;
    }

    public void setFrom(String from) {
      this.from = from;
    }

    public String getBaseUrl() {
      return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
    }
  }

  public static class Security {

    private final ClientAuthorization clientAuthorization = new ClientAuthorization();

    private final Authentication authentication = new Authentication();

    private final RememberMe rememberMe = new RememberMe();

    private final OAuth2 oauth2 = new OAuth2();

    public ClientAuthorization getClientAuthorization() {
      return clientAuthorization;
    }

    public Authentication getAuthentication() {
      return authentication;
    }

    public RememberMe getRememberMe() {
      return rememberMe;
    }

    public OAuth2 getOauth2() {
      return oauth2;
    }

    public static class ClientAuthorization {

      private String accessTokenUri = SwitchesDefaults.Security.ClientAuthorization.accessTokenUri;

      private String tokenServiceId = SwitchesDefaults.Security.ClientAuthorization.tokenServiceId;

      private String clientId = SwitchesDefaults.Security.ClientAuthorization.clientId;

      private String clientSecret = SwitchesDefaults.Security.ClientAuthorization.clientSecret;

      public String getAccessTokenUri() {
        return accessTokenUri;
      }

      public void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
      }

      public String getTokenServiceId() {
        return tokenServiceId;
      }

      public void setTokenServiceId(String tokenServiceId) {
        this.tokenServiceId = tokenServiceId;
      }

      public String getClientId() {
        return clientId;
      }

      public void setClientId(String clientId) {
        this.clientId = clientId;
      }

      public String getClientSecret() {
        return clientSecret;
      }

      public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
      }
    }

    public static class Authentication {

      private final Jwt jwt = new Jwt();

      public Jwt getJwt() {
        return jwt;
      }

      public static class Jwt {

        private String secret = SwitchesDefaults.Security.Authentication.Jwt.secret;

        private String base64Secret = SwitchesDefaults.Security.Authentication.Jwt.base64Secret;

        private long tokenValidityInSeconds = SwitchesDefaults.Security.Authentication.Jwt
          .tokenValidityInSeconds;

        private long tokenValidityInSecondsForRememberMe = SwitchesDefaults.Security.Authentication.Jwt
          .tokenValidityInSecondsForRememberMe;

        public String getSecret() {
          return secret;
        }

        public void setSecret(String secret) {
          this.secret = secret;
        }

        public String getBase64Secret() {
          return base64Secret;
        }

        public void setBase64Secret(String base64Secret) {
          this.base64Secret = base64Secret;
        }

        public long getTokenValidityInSeconds() {
          return tokenValidityInSeconds;
        }

        public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
          this.tokenValidityInSeconds = tokenValidityInSeconds;
        }

        public long getTokenValidityInSecondsForRememberMe() {
          return tokenValidityInSecondsForRememberMe;
        }

        public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
          this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
        }
      }
    }

    public static class RememberMe {

      private String key = SwitchesDefaults.Security.RememberMe.key;

      public String getKey() {
        return key;
      }

      public void setKey(String key) {
        this.key = key;
      }
    }

    public static class OAuth2 {
      private List<String> audience = new ArrayList<>();

      public List<String> getAudience() {
        return Collections.unmodifiableList(audience);
      }

      public void setAudience(List<String> audience) {
        this.audience.addAll(audience);
      }
    }
  }

  public static class Swagger {

    private String title = SwitchesDefaults.Swagger.title;

    private String description = SwitchesDefaults.Swagger.description;

    private String version = SwitchesDefaults.Swagger.version;

    private String termsOfServiceUrl = SwitchesDefaults.Swagger.termsOfServiceUrl;

    private String contactName = SwitchesDefaults.Swagger.contactName;

    private String contactUrl = SwitchesDefaults.Swagger.contactUrl;

    private String contactEmail = SwitchesDefaults.Swagger.contactEmail;

    private String license = SwitchesDefaults.Swagger.license;

    private String licenseUrl = SwitchesDefaults.Swagger.licenseUrl;

    private String defaultIncludePattern = SwitchesDefaults.Swagger.defaultIncludePattern;

    private String host = SwitchesDefaults.Swagger.host;

    private String[] protocols = SwitchesDefaults.Swagger.protocols;

    private boolean useDefaultResponseMessages = SwitchesDefaults.Swagger.useDefaultResponseMessages;

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getVersion() {
      return version;
    }

    public void setVersion(String version) {
      this.version = version;
    }

    public String getTermsOfServiceUrl() {
      return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
      this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getContactName() {
      return contactName;
    }

    public void setContactName(String contactName) {
      this.contactName = contactName;
    }

    public String getContactUrl() {
      return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
      this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
      return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
      this.contactEmail = contactEmail;
    }

    public String getLicense() {
      return license;
    }

    public void setLicense(String license) {
      this.license = license;
    }

    public String getLicenseUrl() {
      return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
      this.licenseUrl = licenseUrl;
    }

    public String getDefaultIncludePattern() {
      return defaultIncludePattern;
    }

    public void setDefaultIncludePattern(String defaultIncludePattern) {
      this.defaultIncludePattern = defaultIncludePattern;
    }

    public String getHost() {
      return host;
    }

    public void setHost(final String host) {
      this.host = host;
    }

    public String[] getProtocols() {
      return protocols;
    }

    public void setProtocols(final String[] protocols) {
      this.protocols = protocols;
    }

    public boolean isUseDefaultResponseMessages() {
      return useDefaultResponseMessages;
    }

    public void setUseDefaultResponseMessages(final boolean useDefaultResponseMessages) {
      this.useDefaultResponseMessages = useDefaultResponseMessages;
    }
  }

  public static class Metrics {

    private final Logs logs = new Logs();

    public Logs getLogs() {
      return logs;
    }

    public static class Logs {

      private boolean enabled = SwitchesDefaults.Metrics.Logs.enabled;

      private long reportFrequency = SwitchesDefaults.Metrics.Logs.reportFrequency;

      public boolean isEnabled() {
        return enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public long getReportFrequency() {
        return reportFrequency;
      }

      public void setReportFrequency(long reportFrequency) {
        this.reportFrequency = reportFrequency;
      }
    }
  }

  public static class Logging {

    private boolean useJsonFormat = SwitchesDefaults.Logging.useJsonFormat;

    private final Logstash logstash = new Logstash();

    public boolean isUseJsonFormat() {
      return useJsonFormat;
    }

    public void setUseJsonFormat(boolean useJsonFormat) {
      this.useJsonFormat = useJsonFormat;
    }

    public Logstash getLogstash() {
      return logstash;
    }

    public static class Logstash {

      private boolean enabled = SwitchesDefaults.Logging.Logstash.enabled;

      private String host = SwitchesDefaults.Logging.Logstash.host;

      private int port = SwitchesDefaults.Logging.Logstash.port;

      private int queueSize = SwitchesDefaults.Logging.Logstash.queueSize;

      public boolean isEnabled() {
        return enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public String getHost() {
        return host;
      }

      public void setHost(String host) {
        this.host = host;
      }

      public int getPort() {
        return port;
      }

      public void setPort(int port) {
        this.port = port;
      }

      public int getQueueSize() {
        return queueSize;
      }

      public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
      }
    }
  }

  public static class Social {

    private String redirectAfterSignIn = SwitchesDefaults.Social.redirectAfterSignIn;

    public String getRedirectAfterSignIn() {
      return redirectAfterSignIn;
    }

    public void setRedirectAfterSignIn(String redirectAfterSignIn) {
      this.redirectAfterSignIn = redirectAfterSignIn;
    }
  }

  public static class Gateway {

    private final RateLimiting rateLimiting = new RateLimiting();

    public RateLimiting getRateLimiting() {
      return rateLimiting;
    }

    private Map<String, List<String>> authorizedMicroservicesEndpoints = SwitchesDefaults.Gateway
      .authorizedMicroservicesEndpoints;

    public Map<String, List<String>> getAuthorizedMicroservicesEndpoints() {
      return authorizedMicroservicesEndpoints;
    }

    public void setAuthorizedMicroservicesEndpoints(Map<String, List<String>> authorizedMicroservicesEndpoints) {
      this.authorizedMicroservicesEndpoints = authorizedMicroservicesEndpoints;
    }

    public static class RateLimiting {

      private boolean enabled = SwitchesDefaults.Gateway.RateLimiting.enabled;

      private long limit = SwitchesDefaults.Gateway.RateLimiting.limit;

      private int durationInSeconds = SwitchesDefaults.Gateway.RateLimiting.durationInSeconds;

      public boolean isEnabled() {
        return enabled;
      }

      public void setEnabled(boolean enabled) {
        this.enabled = enabled;
      }

      public long getLimit() {
        return this.limit;
      }

      public void setLimit(long limit) {
        this.limit = limit;
      }

      public int getDurationInSeconds() {
        return durationInSeconds;
      }

      public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
      }
    }
  }

  public static class ClientApp {

    private String name = SwitchesDefaults.ClientApp.name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  public static class Registry {

    private String password = SwitchesDefaults.Registry.password;

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }

}
