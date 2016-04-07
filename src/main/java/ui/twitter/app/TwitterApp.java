package ui.twitter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import ui.twitter.db.DatabasePoolService;

@Configuration
@EnableAutoConfiguration
public class TwitterApp {
	
	public static void main(String[] args) throws Exception {
		// Force DB initialization
		DatabasePoolService.get();

		SpringApplication.run(TwitterApp.class, args);
	}
}
