package com.guitar.config;


import com.guitar.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
@ComponentScan(basePackages = {"com.guitar.repository.jpa"})
public class UserSecurityService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        Optional<com.guitar.model.User> user = userRepository.findOneByName(username);
        if(!user.isPresent())
            System.out.println("NIE ZNALAZLO USERA :"+username);
        else System.out.println("ZNALAZLO USERA :"+username);
        user.orElseThrow(() -> new UsernameNotFoundException("could not find the user '"
                + username + "'"));
        System.out.println("wychodze z loadUserByUsername :"+user.get().getName());
        return user.get();
    }
}
