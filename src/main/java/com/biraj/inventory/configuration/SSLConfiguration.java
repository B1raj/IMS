package com.biraj.inventory.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SSLConfiguration {

	@Autowired
	Environment env;

	@Bean
	public Boolean configureSSL() {
		System.setProperty("javax.net.ssl.trustStore", env.getRequiredProperty("security.host.truststore.path"));
		System.setProperty("javax.net.ssl.trustStorePassword", env.getRequiredProperty("security.host.truststor.pwd"));
		return true;

	}

}
