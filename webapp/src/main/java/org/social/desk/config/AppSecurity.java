package org.social.desk.config;

import org.social.desk.oauthclients.OAuth2SocialClientsFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableOAuth2Client
public class AppSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private OAuth2SocialClientsFilters filters;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").permitAll();
        http.addFilterBefore(filters.ssoFilter(), BasicAuthenticationFilter.class);
    }
}
