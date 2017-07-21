package org.social.desk.oauthserver.config;

import org.social.desk.oauthclients.OAuth2SocialClientsFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableOAuth2Client
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
		
	@Autowired
	private OAuth2SocialClientsFilters filters;

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests().anyRequest().authenticated()
	    .and().addFilterBefore(filters.ssoFilter(), BasicAuthenticationFilter.class);
	  }
	  
	  
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("john").password("john").roles("USER");
	}



	@Configuration  
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http.antMatcher("/user").authorizeRequests().anyRequest().authenticated();
			http.antMatcher("/me").authorizeRequests().anyRequest().authenticated();
			// @formatter:on
		}
	}
}
