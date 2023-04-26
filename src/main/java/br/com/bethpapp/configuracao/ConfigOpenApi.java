package br.com.bethpapp.configuracao;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class ConfigOpenApi {
	private static final String GROUP = "backend-api";
	@Bean
	public OpenAPI customOpenAPI(@Value("Beth APP") String appDesciption,
			@Value("Sistema de Gestão") String appVersion) {
		Contact contato = new Contact();
		contato.setEmail("messiasfernandes@gamil.com");
		contato.setName("Messias Fernandes");
		contato.setUrl("https://www.linkedin.com/in/messias-da-consolacao/");
		return new OpenAPI().info(new Info().title("Beth APP").version(appVersion).description(appDesciption)
				.termsOfService("http://swagger.io/terms/").contact(contato)
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));

	}
	@Bean
	public GroupedOpenApi groupOpenApi() {
		return GroupedOpenApi.builder().group(GROUP).pathsToMatch("/**").build();
	}

}