package br.com.social;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API-Mini Rede Social",
				version = "1.0",
				description = "API para mini rede social.",
				contact = @Contact(name = "Carlos Roberto", email = "crrsj1@gmail.com")
		)
)
public class SocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
