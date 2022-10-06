package dio.exploringDesignPatterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ExploringDesignPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExploringDesignPatternsApplication.class, args);
	}

}
