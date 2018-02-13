package org.zdesk.utils;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

public class CustomPrincipalExtractor implements PrincipalExtractor {

    private static final String[] PRINCIPAL_KEYS = new String[] {
            "user", "username", "principal",
            "userid", "user_id",
            "login", "id",
            "name", "uuid",
            "email"};

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        for (String key : PRINCIPAL_KEYS) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
        }
        return null;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setForcePrincipalAsString(false);
        return daoAuthenticationProvider;
    }

}