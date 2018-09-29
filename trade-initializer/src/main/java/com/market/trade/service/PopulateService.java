package com.market.trade.service;

import com.market.trade.model.Person;
import com.market.trade.converter.ResponseConverter;
import com.market.trade.dto.CurrencyApiWrapper;
import com.market.trade.dto.CurrencyDTO;
import com.market.trade.dto.PersonInitializeDTO;
import com.mysql.jdbc.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PopulateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PopulateService.class);

    private CurrencySymbolService currencySymbolService;

    private PersonService personService;

    private ResponseConverter responseConverter;

    @Autowired
    public PopulateService (CurrencySymbolService currencySymbolService, ResponseConverter responseConverter, PersonService personService) {
        this.currencySymbolService = currencySymbolService;
        this.responseConverter = responseConverter;
        this.personService = personService;
    }

    public void populateToDB (CurrencyApiWrapper response) {
        List<CurrencyDTO> dtos = response.getCurrencies()
                .entrySet()
                .stream()
                .map(responseConverter)
                .collect(Collectors.toList());

        for (CurrencyDTO dto : dtos) {
            boolean saved = currencySymbolService.save(dto);
            if (saved) {
                LOGGER.info("{} symbol successfully saved", dto.getSymbol());
            } else {
                LOGGER.warn("{} symbol did not saved or already exists", dto.getSymbol());
            }
        }
    }

    public void initializeDB () {
        insertPerson();
    }

    private void insertPerson () {
        List<PersonInitializeDTO> people = new ArrayList<>();
        people.add(PersonHelper.createAdmin());
        people.add(PersonHelper.createPerson());

        for (PersonInitializeDTO person : people) {
            boolean saved = personService.save(person);
            if (saved) {
                LOGGER.info("\n{} \nsuccessfully saved\n", person.toString());
            } else {
                LOGGER.warn("\n{} \ndid not saved or already exists\n", person.toString());
            }
        }
    }
}
