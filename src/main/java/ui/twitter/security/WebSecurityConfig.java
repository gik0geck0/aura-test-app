package ui.twitter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(99)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().ignoringAntMatchers("/aura")	// aura does its own CSRF
			.and().authorizeRequests()
				.anyRequest().authenticated()
					.and()
				.formLogin()
					.and()
				.logout()
					.and()
				.httpBasic();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("admin")
					.password("password")
					.roles("ADMIN", "USER")
					.and()
				.withUser("user")
					.password("password")
					.roles("USER");
	}
}
