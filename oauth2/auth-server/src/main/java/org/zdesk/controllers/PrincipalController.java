package org.zdesk.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalController {

	@RequestMapping("/user")
	public Principal getPrincipal(Principal principal) {
		return principal;
	}
}
