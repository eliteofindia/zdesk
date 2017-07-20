package org.social.desk.webcontrollers;


import java.io.IOException;
import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthLoginController {

	private static final Log logger = LogFactory.getLog(OAuthLoginController.class);

    @RequestMapping({"/user"})
    public Principal user(Principal user) throws IOException {
        logger.info("AS /user has been called");
        logger.debug("user info: " + user.toString());
        return user;
    }
	
}
