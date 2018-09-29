package com.market.trade.security;

import com.market.trade.model.Person;
import com.market.trade.utils.Permissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class SecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);

    private static final Long ADMIN_ID = 1L;

    public Set<Permissions> getPermissions(Person person) {
        Set<Permissions> permissions = new TreeSet<>();

        // Here we declare what are the permissions going to be
        if (person.getId().equals(ADMIN_ID)) {
            permissions.add(Permissions.ADMIN);
        }
        permissions.add(Permissions.TRADE_PERMISSION);

        return permissions;
    }
}
