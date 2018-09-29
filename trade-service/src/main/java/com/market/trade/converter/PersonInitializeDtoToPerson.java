package com.market.trade.converter;

import com.market.trade.model.Person;
import com.market.trade.dto.PersonInitializeDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PersonInitializeDtoToPerson implements Function<PersonInitializeDTO, Person> {

    @Override
    public Person apply(PersonInitializeDTO dto) {
        Person person = new Person();
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setEmail(dto.getEmail());
        person.setPassword(dto.getPassword());
        person.setGender(dto.getGender());
        return person;
    }
}
