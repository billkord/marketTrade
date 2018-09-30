package com.market.trade.converter;

import com.market.trade.MockGenerateEntitiesHelper;
import com.market.trade.dto.ConsumeMessageDTO;
import com.market.trade.exception.IllegalCurrencySymbol;
import com.market.trade.exception.InvalidTimestampException;
import com.market.trade.model.CurrencySymbol;
import com.market.trade.model.Message;
import com.market.trade.model.Person;
import com.market.trade.repository.PersonRepository;
import com.market.trade.service.CurrencySymbolService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@Component
public class ConsumeMessageDtoToMessageUnitTest {

    @InjectMocks
    private ConsumeMessageDtoToMessage consumeMessageDtoToMessage;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private CurrencySymbolService currencySymbolService;

    @Before
    public void setUp() {
        consumeMessageDtoToMessage = new ConsumeMessageDtoToMessage();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test () throws Exception {
        MockGenerateEntitiesHelper generator = new MockGenerateEntitiesHelper();
        ConsumeMessageDTO validDto = generator.getConsumeMessageDTO();
        ConsumeMessageDTO invalidDto = generator.getConsumeMessageDTO();
        invalidDto.setTimePlaced("15 JAN 2018");

        // PersonRepository Mock
        Person person = generator.getPerson();
        when(personRepository.findOne(any(Long.class))).thenReturn(person);

        // CurrencySymbolService Mock
        CurrencySymbol currencySymbolEur = generator.getEURCurrencySymbol();
        CurrencySymbol currencySymbolGbp = generator.getGBPCurrencySymbol();
        when(currencySymbolService.fetchOrCreate("EUR")).thenReturn(currencySymbolEur);
        when(currencySymbolService.fetchOrCreate("GBP")).thenReturn(currencySymbolGbp);

        Message validMessage = consumeMessageDtoToMessage.apply(validDto);
        Message invalidMessage = consumeMessageDtoToMessage.apply(invalidDto);

        Assert.assertEquals(validDto.getUserId(), validMessage.getPerson().getId());
        Assert.assertEquals(validDto.getAmountBuy(), validMessage.getAmountBuy());
        Assert.assertEquals(validDto.getAmountSell(), validMessage.getAmountSell());
        Assert.assertEquals(validDto.getCurrencyFrom(), validMessage.getCurrencyFrom().getSymbol());
        Assert.assertEquals(validDto.getCurrencyTo(), validMessage.getCurrencyTo().getSymbol());
        Assert.assertEquals(validDto.getOriginatingCountry(), validMessage.getOriginatingCountry());
        Assert.assertEquals(validDto.getRate(), validMessage.getRate());

        Assert.assertEquals(getTimestamp(validDto.getTimePlaced()), validMessage.getTimePlaced());

        Assert.assertNull(invalidMessage.getTimePlaced());

        Assert.assertTrue(validMessage.isValid());
        Assert.assertFalse(invalidMessage.isValid());
    }

    private Timestamp getTimestamp (String timePlaced) {
        DateFormat format = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
        try {
            Date date = format.parse(timePlaced);
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            return new Timestamp(new Date().getTime());
        }
    }
}
