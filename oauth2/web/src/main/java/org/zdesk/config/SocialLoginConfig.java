package org.zdesk.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
//@EnableOAuth2Sso
@EnableOAuth2Client
public class SocialLoginConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext oauth2ClientContext;	

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.authorizeRequests().antMatchers("/index.html", "/home.html", "/code", "/", "/media").permitAll()
				.anyRequest().authenticated().and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
		// @formatter:on
	}
	
	
	private Filter ssoFilter() {
		  OAuth2ClientAuthenticationProcessingFilter zdeskAuthFilter = new OAuth2ClientAuthenticationProcessingFilter("/media");
		  OAuth2RestTemplate zdeskTemplate = new OAuth2RestTemplate(zdesk(), oauth2ClientContext);
		  zdeskAuthFilter.setRestTemplate(zdeskTemplate);
		  UserInfoTokenServices tokenServices = new UserInfoTokenServices(zdeskResource().getUserInfoUri(), zdesk().getClientId());
		  tokenServices.setRestTemplate(zdeskTemplate);
		  zdeskAuthFilter.setTokenServices(tokenServices);
		  return zdeskAuthFilter;
		}
	
	
		@Bean
		@ConfigurationProperties("zdesk.client")
		public AuthorizationCodeResourceDetails zdesk() {
			System.out.println((new AuthorizationCodeResourceDetails().getClientId()));
		    return new AuthorizationCodeResourceDetails();
		}
		
		@Bean
		@ConfigurationProperties("zdesk.resource")
		public ResourceServerProperties  zdeskResource() {
		    return new ResourceServerProperties();
		}
	
}
