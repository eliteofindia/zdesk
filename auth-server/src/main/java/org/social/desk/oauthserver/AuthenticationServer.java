package org.social.desk.oauthserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@RestController
@ComponentScan("org.social.desk")
public class AuthenticationServer {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServer.class, args);
    }

    private static final Log logger = LogFactory.getLog(AuthenticationServer.class);

    @RequestMapping({"/user","/me"})
    public Principal user(Principal user, HttpServletResponse httpresponse) throws IOException {
        logger.info("AS /user has been called");
        logger.debug("user info: " + user.toString());
        return user;
    }
    
}
