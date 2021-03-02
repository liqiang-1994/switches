package io.luxyva.switches.framework.gateway.config.apidoc;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

@Primary
@Configuration
public class SwaggerConfiguration {

  @Autowired
  RouteDefinitionLocator locator;

  @Bean
  public List<GroupedOpenApi> apis() {
    List<GroupedOpenApi> groups = new ArrayList<>();
    List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
    definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
      String name = routeDefinition.getId().replaceAll("-service", "");
      GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").setGroup(name).build();
    });
    return groups;
  }
}
