package com.market.trade.converter;

import com.market.trade.model.CurrencyPairUsage;
import com.market.trade.model.Message;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.function.Function;

@Component
public class MessageToCurrencyPairUsage implements Function<Message, CurrencyPairUsage> {

    @Override
    public CurrencyPairUsage apply(Message message) {
        CurrencyPairUsage currencyPairUsage = new CurrencyPairUsage();
        currencyPairUsage.setCurrencyFrom(message.getCurrencyFrom());
        currencyPairUsage.setCurrencyTo(message.getCurrencyTo());
        currencyPairUsage.setAppearances(BigInteger.ZERO);
        return currencyPairUsage;
    }
}
