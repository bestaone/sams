package cn.webestar.sams.starter.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocConfig {

    @Bean
    public OpenAPI restfulOpenAPI() {
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes("Authorization", new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer"))
                )
                .info(
                        new Info()
                                .title("SAMS API")
                                .version("1.0")
                );
    }

}
