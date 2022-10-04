package dio.exploringDesignPatterns.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class Swagger {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                    .title("Exploring Design Patterns API")
                    .description("REST API to exercise Design Patterns (Singleton, Strategy and Facade).")
                    .version("v0.0.1")
                );
    }
}
