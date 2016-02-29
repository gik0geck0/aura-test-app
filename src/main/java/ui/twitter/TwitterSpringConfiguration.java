package ui.twitter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ui.twitter")
public class TwitterSpringConfiguration {
	@Bean
	DatabasePoolService dbPoolProvider() {
		System.out.println("Starting a Database Pool Service");
		return new DatabasePoolService();
	}
}
