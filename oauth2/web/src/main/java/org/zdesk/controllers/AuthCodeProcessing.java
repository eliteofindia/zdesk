package org.zdesk.controllers;

import java.util.Arrays;
import java.util.Base64;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/code")
public class AuthCodeProcessing {
	
	@GetMapping
	public Object processCode(@RequestParam("code") String code) {
		
		System.out.println("############################## ---- CODE ---- ###############################");
		System.out.println(code);
		
		ResponseEntity<String> response = null;
		RestTemplate restTemplate = new RestTemplate();
		
		String credentials = "zdesk:zdesksecret";
		String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		String access_token_url = "http://localhost:8080/oauth/token";
		access_token_url += "?code=" + code;
		access_token_url += "&grant_type=authorization_code";
		access_token_url += "&redirect_uri=http://localhost:9001/app/code";

		response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);
		
		System.out.println(response.toString());
		
		return response;
		
	}

}
