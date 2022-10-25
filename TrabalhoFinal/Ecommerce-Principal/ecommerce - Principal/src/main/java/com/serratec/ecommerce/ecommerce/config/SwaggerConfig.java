/*
 * package br.org.serratec.config;
 * 
 * import org.springframework.context.annotation.Bean;
 * import org.springframework.context.annotation.Configuration;
 * 
 * import com.serratec.ecommerce.ecommerce.model.Cliente;
 * import com.serratec.ecommerce.ecommerce.model.Endereco;
 * 
 * import springfox.documentation.builders.ApiInfoBuilder;
 * import springfox.documentation.builders.PathSelectors;
 * import springfox.documentation.builders.RequestHandlerSelectors;
 * import springfox.documentation.service.ApiInfo;
 * import springfox.documentation.service.Contact;
 * import springfox.documentation.spi.DocumentationType;
 * import springfox.documentation.spring.web.plugins.Docket;
 * 
 * @Configuration
 * public class SwaggerConfig {
 * 
 * @Bean
 * public Docket api() {
 * return new Docket(DocumentationType.SWAGGER_2).select()
 * .apis(RequestHandlerSelectors.basePackage("br.org.serratec.controller"))
 * .paths(PathSelectors.any())
 * .build()
 * .ignoredParameterTypes(Cliente.class, Endereco.class)
 * .apiInfo(apiInfo());
 * }
 * 
 * public ApiInfo apiInfo() {
 * ApiInfo info = new ApiInfoBuilder()
 * .title("API DO E-COMMERCE")
 * .description("Essa API foi desenvolvida pelo kifelG")
 * .license("Apache License, Version 2.0")
 * .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
 * .termsOfServiceUrl("/service.html")
 * .version("1.0.1")
 * .contact(new Contact("Serratec", "www.serratec.org.br",
 * "serratec@serratec.org.br"))
 * .build();
 * return info;
 * }
 * }
 */