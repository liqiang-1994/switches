package io.luxyva.switches.storage.web;

import io.luxyva.switches.storage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

  @Autowired
  private FileService fileService;

  @Value("${server.port}")
  private String port;

  @GetMapping("/hello")
  public String echo() {
    fileService.saveFile();
    return "I am email " + port;
  }

  @GetMapping("/hell")
  public String echo1() {
    fileService.x();
    return "I am email " + port;
  }
}
