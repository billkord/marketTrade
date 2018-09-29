package com.market.trade.service;

import com.market.trade.model.CurrencySymbol;
import com.market.trade.converter.CurrencyDtoToCurrencySymbolConverter;
import com.market.trade.dto.CurrencyDTO;
import com.market.trade.exception.EntityExistException;
import com.market.trade.repository.CurrencySymbolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencySymbolService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencySymbolService.class);

    @Autowired
    private CurrencySymbolRepository currencySymbolRepository;

    @Autowired
    private CurrencyDtoToCurrencySymbolConverter currencyDtoToCurrencySymbolConverter;

    public boolean save (CurrencyDTO dto) {
        try {
            if (currencyExist(dto)) {
                throw new EntityExistException(dto.getSymbol() + " already Exist");
            }
            CurrencySymbol currencySymbol = currencyDtoToCurrencySymbolConverter.apply(dto);
            currencySymbolRepository.save(currencySymbol);
            return true;
        } catch (EntityExistException ex) {
            return false; // or handle specifically
        } catch (Exception e) {
            return false;
        }
    }

    private boolean currencyExist (CurrencyDTO dto) {
        CurrencySymbol existCurrencySymbol = currencySymbolRepository.findBySymbolAndName(dto.getSymbol(),dto.getName());
        return existCurrencySymbol != null;
    }

    public CurrencySymbol fetchOrCreate (String symbol) {
        CurrencySymbol currencySymbol = currencySymbolRepository.findBySymbol(symbol);
        if (currencySymbol == null) {
            currencySymbol = new CurrencySymbol();
            currencySymbol.setSymbol(symbol);
            currencySymbol.setName("");
            currencySymbolRepository.save(currencySymbol);
            LOGGER.info("{} currency symbol was inserted in DB because it was not included", symbol);
        }
        return currencySymbol;
    }
}
