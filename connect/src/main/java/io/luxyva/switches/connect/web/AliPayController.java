package io.luxyva.switches.connect.web;

import io.luxyva.switches.connect.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AliPayController {

	@Autowired
	private AliPayService aliPayService;

	@GetMapping("/get")
	public void aliPay() throws Exception {
		aliPayService.pay();
	}
}
