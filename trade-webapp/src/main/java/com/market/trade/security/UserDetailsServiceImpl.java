package com.market.trade.security;

import com.market.trade.dto.CustomUserDetails;
import com.market.trade.model.Person;
import com.market.trade.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(email);
        if (person == null) {
            LOGGER.error("User not found or inactive, username: {}", email);
            throw new UsernameNotFoundException("");
        }

        Set<GrantedAuthority> grantedAuthorities = securityService.getPermissions(person)
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toSet());

        return new CustomUserDetails(person.getId(), person.getEmail(), person.getPassword(), grantedAuthorities);
    }
}
