package org.zdesk.controllers;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public Message home() {
		return new Message("Hello World");
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Map<String, String> acciableToUser() {
        return Collections.singletonMap("message", "Congrats! you are granted the authority to see this content as ROLE_USER");
	}
	
	 @RequestMapping(value = "/details", method = RequestMethod.GET)
	public Principal getDetils(Principal user) {		 
		return user;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public Map<String, String> acciableToAdmin() {
        return Collections.singletonMap("message", "Congrats! you are granted the authority to see this content as ROLE_ADMIN");
	}
}


class Message {
	private String id = UUID.randomUUID().toString();
	private String content;

	Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
