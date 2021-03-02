package io.luxyva.switches.base.web;

import io.luxyva.switches.api.service.YesService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @DubboReference(version = "1.0.0")
  private YesService yesService;

  @GetMapping("/yes")
  public String hello() {
    String x = yesService.response("liqiang ");
    return x;
  }

}
