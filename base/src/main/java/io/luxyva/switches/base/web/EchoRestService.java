package io.luxyva.switches.base.web;

import io.luxyva.switches.api.service.EchoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@DubboService(version = "1.0.0")
public class EchoRestService implements EchoService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public String echo(@RequestParam String message) {
    return "i am luxyva" + message;
  }
}
