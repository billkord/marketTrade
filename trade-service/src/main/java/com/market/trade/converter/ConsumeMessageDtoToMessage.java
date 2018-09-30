package com.market.trade.converter;

import com.market.trade.dto.ConsumeMessageDTO;
import com.market.trade.exception.IllegalCurrencySymbol;
import com.market.trade.exception.InvalidTimestampException;
import com.market.trade.model.CurrencySymbol;
import com.market.trade.model.Message;
import com.market.trade.model.Person;
import com.market.trade.repository.PersonRepository;
import com.market.trade.service.CurrencySymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ConsumeMessageDtoToMessage {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CurrencySymbolService currencySymbolService;

    public Message apply(ConsumeMessageDTO consumeMessageDTO) throws IllegalCurrencySymbol {
        Message message = new Message();

        Person person = personRepository.findOne(consumeMessageDTO.getUserId());
        CurrencySymbol currencyFrom = currencySymbolService.fetchOrCreate(consumeMessageDTO.getCurrencyFrom());
        CurrencySymbol currencyTo = currencySymbolService.fetchOrCreate(consumeMessageDTO.getCurrencyTo());

        message.setPerson(person);
        message.setCurrencyFrom(currencyFrom);
        message.setCurrencyTo(currencyTo);
        message.setAmountBuy(consumeMessageDTO.getAmountBuy());
        message.setAmountSell(consumeMessageDTO.getAmountSell());
        message.setRate(consumeMessageDTO.getRate());
        message.setOriginatingCountry(consumeMessageDTO.getOriginatingCountry());

        try {
            Timestamp timePlaced = getTimePlaced(consumeMessageDTO.getTimePlaced());
            message.setTimePlaced(timePlaced);
            message.setValid(true);
        } catch (InvalidTimestampException e) {
            message.setValid(false);
        }

        return message;
    }

    private Timestamp getTimePlaced (String timePlaced) throws InvalidTimestampException {
        DateFormat format = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
        try {
            Date date = format.parse(timePlaced);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new InvalidTimestampException();
        }
    }
}
