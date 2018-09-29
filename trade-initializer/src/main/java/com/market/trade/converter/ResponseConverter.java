package com.market.trade.converter;

import com.market.trade.dto.CurrencyDTO;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class ResponseConverter implements Function<Map.Entry<String, String>, CurrencyDTO> {

    @Override
    public CurrencyDTO apply(Map.Entry<String, String> entry) {
        return CurrencyDTO.CurrencyDTOBuilder
                .aCurrencyDTO()
                .withSymbol(entry.getKey())
                .withName(entry.getValue())
                .build();
    }
}
