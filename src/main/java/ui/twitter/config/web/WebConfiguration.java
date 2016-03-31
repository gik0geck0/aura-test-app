package ui.twitter.config.web;

import javax.servlet.DispatcherType;

import org.auraframework.http.AuraContextFilter;
import org.auraframework.http.AuraFrameworkServlet;
import org.auraframework.http.AuraResourceRewriteFilter;
import org.auraframework.http.AuraResourceServlet;
import org.auraframework.http.AuraRewriteFilter;
import org.auraframework.http.AuraServlet;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.ImmutableList;

import ui.twitter.security.TwitterAuthFilter;

@Configuration
public class WebConfiguration {

	@Bean
	public ServletRegistrationBean auraServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new AuraServlet(), "/aura");
		return servletRegistrationBean;
	}

	@Bean
	public ServletRegistrationBean auraResourceServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new AuraResourceServlet(), "/auraResource");
		return servletRegistrationBean;
	}

	@Bean
	public ServletRegistrationBean auraFrameworkServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new AuraFrameworkServlet(), "/auraFW/*");
		servletRegistrationBean.setLoadOnStartup(0);
		return servletRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean auraContextFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new AuraContextFilter());
		registrationBean.setOrder(2);

		registrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);
		registrationBean.setUrlPatterns(ImmutableList.of("/aura", "*.app", "*.cmp", "/auraResource", "/auraFW"));
		
		return registrationBean;
	}
	
	@Bean
	public FilterRegistrationBean authFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new TwitterAuthFilter());
		registrationBean.setOrder(1);
		registrationBean.setUrlPatterns(ImmutableList.of("/*"));
		
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean auraRewriteFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new AuraRewriteFilter());
		
		registrationBean.setUrlPatterns(ImmutableList.of("*.cmp", "*.app"));
		registrationBean.setOrder(2);
		
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean auraResourceRewriteFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		AuraResourceRewriteFilter rewriteFilter = new AuraResourceRewriteFilter();
		registrationBean.setFilter(rewriteFilter);
		
		registrationBean.setUrlPatterns(ImmutableList.of("/l/*"));
		registrationBean.setOrder(3);
		
		return registrationBean;
	}
}
