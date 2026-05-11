package dev.guilhermesilva.atom_backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Atom API")
                        .version("v1")
                        .description("Sistema ATOM")
                        .contact(new Contact().name("Guilherme Silva")
                                .email("gluizdasilvade@gmail.com")
                                .url("https://github.com/younglherme"))
                );
    }

}