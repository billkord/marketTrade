package com.market.trade.service;

import com.market.trade.dto.PersonInitializeDTO;
import com.market.trade.model.Person;

public final class PersonHelper {

    public static PersonInitializeDTO createAdmin () {
        return PersonInitializeDTO.PersonInitializeDTOBuilder
                .aPersonInitializeDTO()
                .withFirstName("Currency")
                .withLastName("Fair")
                .withEmail("currencyFair@info.com")
                .withPassword("123456")
                .build();
    }

    public static PersonInitializeDTO createPerson () {
        return PersonInitializeDTO.PersonInitializeDTOBuilder
                .aPersonInitializeDTO()
                .withFirstName("One")
                .withLastName("Person")
                .withEmail("oneperson@email.com")
                .withPassword("123456")
                .withGender(Person.Gender.MALE)
                .build();
    }
}
