package io.luxyva.switches.connect.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "connect.alipay")
@Configuration
public class AliPayProperties {

	private String protocol;

	private String gatewayHost;

	private String signType;

	private String appId;

	/**
	 * 应用私钥
	 */
	private String merchantPrivateKey;

	/**
	 * 应用公钥证书文件路径
	 */
	private String merchantCertPath;

	/**
	 * 支付宝公钥证书文件路径
	 */
	private String alipayCertPath;

	/**
	 * 支付宝根证书文件路径
	 */
	private String alipayRootCertPath;

	/**
	 * 支付宝公钥
	 */
	private String alipayPublicKey;

	/**
	 * 异步通知接收服务地址
	 */
	private String notifyUrl;

	/**
	 * AES密钥
	 */
	private String encryptKey;

	private String appPublicKey;

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getGatewayHost() {
		return gatewayHost;
	}

	public void setGatewayHost(String gatewayHost) {
		this.gatewayHost = gatewayHost;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMerchantPrivateKey() {
		return merchantPrivateKey;
	}

	public void setMerchantPrivateKey(String merchantPrivateKey) {
		this.merchantPrivateKey = merchantPrivateKey;
	}

	public String getMerchantCertPath() {
		return merchantCertPath;
	}

	public void setMerchantCertPath(String merchantCertPath) {
		this.merchantCertPath = merchantCertPath;
	}

	public String getAlipayCertPath() {
		return alipayCertPath;
	}

	public void setAlipayCertPath(String alipayCertPath) {
		this.alipayCertPath = alipayCertPath;
	}

	public String getAlipayRootCertPath() {
		return alipayRootCertPath;
	}

	public void setAlipayRootCertPath(String alipayRootCertPath) {
		this.alipayRootCertPath = alipayRootCertPath;
	}

	public String getAlipayPublicKey() {
		return alipayPublicKey;
	}

	public void setAlipayPublicKey(String alipayPublicKey) {
		this.alipayPublicKey = alipayPublicKey;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getEncryptKey() {
		return encryptKey;
	}

	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}

	public String getAppPublicKey() {
		return appPublicKey;
	}

	public void setAppPublicKey(String appPublicKey) {
		this.appPublicKey = appPublicKey;
	}
}
