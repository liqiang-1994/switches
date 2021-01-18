package io.luxyva.switches.connect.service;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import io.luxyva.switches.connect.config.AliPayProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AliPayService {

	@Autowired
	private AliPayProperties aliPayProperties;

	public void pay() throws Exception {
		Factory.setOptions(getConfig());
		AlipayTradePrecreateResponse response = Factory
				.Payment
				.FaceToFace()
				.preCreate("Apple iPhone11 128G", "2234567890", "5799.00");
		if (ResponseChecker.success(response)) {
			log.info("pay success");
		} else {
			log.error("pay error: msg: {}, subMsg:{}", response.getMsg(), response.getSubMsg());
		}
	}

	private Config getConfig() {
		Config config = new Config();
		String x = aliPayProperties.getProtocol();
		config.protocol = aliPayProperties.getProtocol();
		config.gatewayHost = aliPayProperties.getGatewayHost();
		config.appId = aliPayProperties.getAppId();
		config.signType = aliPayProperties.getSignType();
		config.merchantPrivateKey = aliPayProperties.getMerchantPrivateKey();
		config.merchantCertPath = aliPayProperties.getMerchantCertPath();
		config.alipayCertPath = aliPayProperties.getAlipayCertPath();
		config.alipayRootCertPath = aliPayProperties.getAlipayRootCertPath();
		config.alipayPublicKey = aliPayProperties.getAlipayPublicKey();
		return config;
	}
}
