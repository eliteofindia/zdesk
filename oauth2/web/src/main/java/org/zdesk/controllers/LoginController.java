package org.zdesk.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("intralogin")
public class LoginController {
	
	@SuppressWarnings("unchecked")
	@GetMapping
	public Object Login() throws JsonParseException, JsonMappingException, IOException {
		
		ResponseEntity<String> response = null;
		
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
		
		response =  restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);
		
		Map<String,String> myMap = new HashMap<String, String>();

		ObjectMapper objectMapper = new ObjectMapper();
		myMap = objectMapper.readValue(response.getBody(), HashMap.class);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", myMap.get("access_token"));
		responseHeaders.setLocation(URI.create("http://localhost:9001/app/"));
		
		return new ResponseEntity<Object>(myMap.get("access_token"), HttpStatus.OK);
		//return responseHeaders;
	}

}
