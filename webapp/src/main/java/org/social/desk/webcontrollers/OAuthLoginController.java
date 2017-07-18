package org.social.desk.webcontrollers;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthLoginController {

	@RequestMapping(value = "/login/facebook", method = RequestMethod.GET)
    public void facebookLogin(HttpServletResponse httpServletResponse) throws IOException {
        //return resourceServerProxy.getForObject("http://localhost:8080/auth/login/facebook", Map.class);
		httpServletResponse.sendRedirect("http://localhost:8080/auth/login/facebook");
    }
	
}
