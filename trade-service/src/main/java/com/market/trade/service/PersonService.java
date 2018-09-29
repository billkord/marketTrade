package com.market.trade.service;

import com.market.trade.model.Person;
import com.market.trade.converter.PersonInitializeDtoToPerson;
import com.market.trade.dto.PersonInitializeDTO;
import com.market.trade.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonInitializeDtoToPerson personInitializeDtoToPerson;

    public boolean save (PersonInitializeDTO personInitializeDTO) {
        try {
            Person person = personInitializeDtoToPerson.apply(personInitializeDTO);
            personRepository.save(person);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValid (Long personId, Principal principal) {
        Person person = personRepository.findOne(personId);
        return person != null
                && person.getEmail().equals(principal.getName());
    }
}
