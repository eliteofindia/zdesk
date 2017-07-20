package org.social.desk.oauthclients;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.web.filter.CompositeFilter;

@Configuration
public class OAuth2SocialClientsFilters {
	
	@Autowired
	private OAuth2SocialClients oauth2socialclients;
	
	@Autowired
	private  OAuth2ClientContext OAuth2ClientContext;
	
	public Filter ssoFilter() {
		  CompositeFilter filter = new CompositeFilter();
		  List<Filter> filters = new ArrayList<>();
		  filters.add(ssoFilter(oauth2socialclients.facebook(), "/login/facebook"));
		  //filters.add(ssoFilter(github(), "/login/github"));
		  filter.setFilters(filters);
		  return filter;
		}
	
	
	private Filter ssoFilter(ClientResources client, String path) {
		  OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
		  OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), OAuth2ClientContext);
		  filter.setRestTemplate(template);
		  UserInfoTokenServices tokenServices = new UserInfoTokenServices(
		      client.getResource().getUserInfoUri(), client.getClient().getClientId());
		  tokenServices.setRestTemplate(template);
		  filter.setTokenServices(tokenServices);
		  return filter;
		}

}


class ClientResources {

	  @NestedConfigurationProperty
	  private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

	  @NestedConfigurationProperty
	  private ResourceServerProperties resource = new ResourceServerProperties();

	  public AuthorizationCodeResourceDetails getClient() {
	    return client;
	  }

	  public ResourceServerProperties getResource() {
	    return resource;
	  }
	}
