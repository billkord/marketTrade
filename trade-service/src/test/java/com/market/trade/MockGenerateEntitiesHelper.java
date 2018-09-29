package com.market.trade;

import com.market.trade.dto.ConsumeMessageDTO;
import com.market.trade.dto.CurrencyDTO;
import com.market.trade.dto.PersonInitializeDTO;
import com.market.trade.model.CurrencySymbol;
import com.market.trade.model.Person;

import java.math.BigDecimal;

public class MockGenerateEntitiesHelper {

    public ConsumeMessageDTO getConsumeMessageDTO () {
        ConsumeMessageDTO dto = new ConsumeMessageDTO();
        dto.setAmountBuy(BigDecimal.TEN);
        dto.setAmountSell(BigDecimal.TEN.add(BigDecimal.TEN));
        dto.setCurrencyFrom("EUR");
        dto.setCurrencyTo("GBP");
        dto.setOriginatingCountry("GR");
        dto.setRate(0.796);
        dto.setTimePlaced("24-JAN-15 10:27:44");
        dto.setUserId(1L);
        return dto;
    }

    public CurrencyDTO getCurrencyDTO () {
        return CurrencyDTO.CurrencyDTOBuilder
                .aCurrencyDTO()
                .withSymbol("EUR")
                .withName("Euro")
                .build();
    }

    public PersonInitializeDTO getPersonInitializeDTO () {
        return PersonInitializeDTO.PersonInitializeDTOBuilder
                .aPersonInitializeDTO()
                .withFirstName("Vasilis")
                .withLastName("Kordalis")
                .withEmail("billkord2012@gmail.com")
                .withPassword("123456")
                .withGender(Person.Gender.MALE)
                .build();
    }

    public Person getPerson () {
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("Vasilis");
        person.setLastName("Kordalis");
        person.setEmail("billkord2012@gmail.com");
        person.setPassword("132456");
        person.setGender(Person.Gender.MALE);
        return person;
    }

    public CurrencySymbol getEURCurrencySymbol () {
        CurrencySymbol currencySymbol = new CurrencySymbol();
        currencySymbol.setId(1L);
        currencySymbol.setSymbol("EUR");
        currencySymbol.setName("Euro");
        return currencySymbol;
    }

    public CurrencySymbol getGBPCurrencySymbol () {
        CurrencySymbol currencySymbol = new CurrencySymbol();
        currencySymbol.setId(2L);
        currencySymbol.setSymbol("GBP");
        currencySymbol.setName("British Pound Sterling");
        return currencySymbol;
    }
}
