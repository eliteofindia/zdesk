package org.zdesk.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	                                    Authentication authentication) throws IOException, ServletException {
	    //implementation
		
		System.out.println("#################### Details ###################");
	    System.out.println(authentication.getDetails());
	    response.setHeader("AUTH", "MY AUTH HEADER");
	    response.getWriter().write(authentication.getPrincipal().toString());
	}

}
