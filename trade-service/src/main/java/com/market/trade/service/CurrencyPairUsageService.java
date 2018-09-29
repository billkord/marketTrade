package com.market.trade.service;

import com.market.trade.converter.MessageToCurrencyPairUsage;
import com.market.trade.model.CurrencyPairUsage;
import com.market.trade.model.Message;
import com.market.trade.repository.CurrencyPairUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

@Service
public class CurrencyPairUsageService {

    @Autowired
    private CurrencyPairUsageRepository currencyPairUsageRepository;

    @Autowired
    private MessageToCurrencyPairUsage messageToCurrencyPairUsage;

    public void processMessage (Message message) {
        CurrencyPairUsage currencyPairUsage = Optional
                .ofNullable(currencyPairUsageRepository.findByCurrencyFromAndCurrencyTo(message.getCurrencyFrom(), message.getCurrencyTo()))
                .orElse(messageToCurrencyPairUsage.apply(message));
        BigInteger appearancesPlusOne = currencyPairUsage.getAppearances().add(BigInteger.ONE);
        currencyPairUsage.setAppearances(appearancesPlusOne);

        currencyPairUsageRepository.save(currencyPairUsage);
    }

    public String getGraphPair (CurrencyPairUsage currencyPairUsage) {
        return currencyPairUsage.getCurrencyFrom().getSymbol()
                .concat("/")
                .concat(currencyPairUsage.getCurrencyTo().getSymbol());
    }

    public Collection<CurrencyPairUsage> findTopTen (String duration) {
        return currencyPairUsageRepository.findTopTen(duration);
    }
}
