package org.social.desk.oauthserver.config;

import org.social.desk.oauthclients.OAuth2SocialClientsFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	

	@Autowired
	OAuth2ClientContext oauth2ClientContext;
	
	@Autowired
	private OAuth2SocialClientsFilters filters;

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.addFilterBefore(filters.ssoFilter(), BasicAuthenticationFilter.class);
	  }
}
