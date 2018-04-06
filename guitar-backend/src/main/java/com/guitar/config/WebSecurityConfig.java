package com.guitar.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserSecurityService userSecurityService;
    private final TokenAuthenticationService tokenAuthenticationService;
    private final String secret = "Very faken sectet string";

    public WebSecurityConfig() {
        super(true);
        userSecurityService = new UserSecurityService();
        this.tokenAuthenticationService = new TokenAuthenticationService(secret, userSecurityService);

    }

/*
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
        return manager;
    }
*/
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().and()
                .anonymous().and()
                .servletApi().and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
            //    .antMatchers("/play").permitAll()
            //    .antMatchers("/play/").permitAll()
                .antMatchers("/play/*").permitAll()
            //    .antMatchers("/song").permitAll()
            //    .antMatchers("/song/").permitAll()
                .antMatchers("/song/*").permitAll()
                .antMatchers("/player").permitAll()
            //    .antMatchers("/checkSong").permitAll()
            //    .antMatchers("/checkSong/*").permitAll()
            //    .antMatchers("/checkSong/*/").permitAll()
                .antMatchers("/checkSong/*/*").permitAll()

                .anyRequest().authenticated().and()
                .addFilterBefore(new StatelessLoginFilter("/login", tokenAuthenticationService, userSecurityService,authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
                        UsernamePasswordAuthenticationFilter.class)
                //.formLogin()
                //.and()
                //.httpBasic().and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler(new LogoutSuccessHandler("http://google.com")).and()
                .headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserSecurityService userDetailsService() {
        System.out.println("Jestem w userDetailsService()");
        return userSecurityService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenAuthenticationService tokenAuthenticationService() {
        return tokenAuthenticationService;
    }

}