package com.market.trade.converter;

import com.market.trade.model.CurrencySymbol;
import com.market.trade.dto.CurrencyDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CurrencyDtoToCurrencySymbolConverter implements Function<CurrencyDTO, CurrencySymbol> {

    @Override
    public CurrencySymbol apply(CurrencyDTO dto) {
        CurrencySymbol currencySymbol = new CurrencySymbol();
        currencySymbol.setSymbol(dto.getSymbol());
        currencySymbol.setName(dto.getName());
        return currencySymbol;
    }
}
