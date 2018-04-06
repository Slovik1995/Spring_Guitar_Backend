package com.guitar.config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    private final TokenHandler tokenHandler;

    public TokenAuthenticationService(String secret, UserSecurityService userSecurityService) {
        tokenHandler = new TokenHandler(secret, userSecurityService);
    }

    public void addAuthentication(HttpServletResponse response, UserAuthentication userAuthentication) {
        System.out.println("addAuthentication");
        final User user = userAuthentication.getDetails();
        response.addHeader("Access-Control-Expose-Headers", "X-AUTH-TOKEN");
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));

        //response.addHeader("Access-Control-Expose-Headers", "Set-Cookie");
        //response.addHeader(Set-Cookie, tokenHandler.createTokenForUser(user));
        //Cookie cookie = new Cookie("aaa","bbb");
        //cookie.setMaxAge(60*60);
        //cookie.setHttpOnly(false);
        //response.addCookie(cookie);
        //System.out.println(response.getHeader("Set-Cookie"));
        //System.out.println(response.getHeaderNames());
    //    response.addCookie(new Cookie("username", user.getUsername()));
     //   System.out.println(user.getUsername());
        //System.out.println("mam header: "+response.getHeader(AUTH_HEADER_NAME));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        System.out.println("getAuthentication");
        final String token = request.getHeader(AUTH_HEADER_NAME);
        System.out.println(token);
        if (token != null && !"null".equals(token)) {
            System.out.println("good token in getAuthentication");
            final User user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
                System.out.println("udalo sie zautentyfikowac");
                return new UserAuthentication(user);
            }
        }
        System.out.println("NIE udalo sie zautentyfikowac");
        return null;
    }
}
