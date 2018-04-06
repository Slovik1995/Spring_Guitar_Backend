package com.guitar.config;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {


    private final UserSecurityService userSecurityService;
    private final TokenAuthenticationService tokenAuthenticationService;

    public StatelessLoginFilter(String urlMapping, TokenAuthenticationService tokenAuthenticationService,
                                UserSecurityService userDetailsService, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(urlMapping));
        System.out.println("JESTEM W StatelessLoginFilter");
        this.userSecurityService = userDetailsService;
        this.tokenAuthenticationService = tokenAuthenticationService;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        System.out.println("JESTEM W attemptAuthentication");
        String header = request.getHeader("authorization");
        String login = header.split(":")[0];
        String password = header.split(":")[1];
        System.out.println(">>>>  "+login+" "+password);
        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                login, password
        );
        Authentication authentication =getAuthenticationManager().authenticate(loginToken);     //<<<====tu jest cos nie tak
        System.out.println("wychodze z attemptAuthentication");
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        System.out.println("JESTEM W successfulAuthentication");
        UserDetails userDetails = userSecurityService.loadUserByUsername(((com.guitar.model.User) authentication.getPrincipal()).getUsername());

        final User authenticatedUser = new User(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

        tokenAuthenticationService.addAuthentication(response, userAuthentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("wychodze z successfulAuthentication");
    }


}
