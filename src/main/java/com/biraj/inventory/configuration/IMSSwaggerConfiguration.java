package com.biraj.inventory.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author birajmishra
 *
 */

@EnableSwagger2
@Configuration
public class IMSSwaggerConfiguration {

	@Bean
	public Docket InventoryApi() {

		return new Docket(DocumentationType.SWAGGER_2).select().paths(paths()).paths(paths()).build();
	}

	@SuppressWarnings("unchecked")
	private Predicate<String> paths() {
		return Predicates.or(PathSelectors.regex("/ims.*"));
	}

	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl");
	}
}
