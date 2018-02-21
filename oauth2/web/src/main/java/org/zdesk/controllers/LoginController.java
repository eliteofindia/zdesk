package org.zdesk.controllers;

import java.util.Arrays;
import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("intralogin")
public class LoginController {
	
	@GetMapping
	public Object Login() {
		RestTemplate restTemplate = new RestTemplate();
		String credentials = "zdesk:zdesksecret";
		String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("username", "john");
		map.add("password", "john");
		map.add("grant_type", "password");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		String access_token_url = "http://localhost:8080/oauth/token";	
		
		return restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);
	}

}
