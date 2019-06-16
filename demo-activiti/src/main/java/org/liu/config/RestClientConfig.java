package org.liu.config;

import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.impl.client.CloseableHttpClient;
import org.liu.util.HttpClientUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 与http，https请求相关的配置，主要是RestTemplate的配置
 * 
 * @author liuzhsh
 */
@Configuration
public class RestClientConfig {

	@Bean
	public RestTemplate restTemplate() throws KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
		CloseableHttpClient httpClient = HttpClientUtil.acceptsUntrustedCertsHttpClient();
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				httpClient);
		StringHttpMessageConverter messageConverters = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		RestTemplate restTemplate = new RestTemplateBuilder().requestFactory(clientHttpRequestFactory).additionalMessageConverters(messageConverters).build();
		return restTemplate;
	}

}
