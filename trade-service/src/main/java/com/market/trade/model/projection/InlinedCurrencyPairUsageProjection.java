package com.market.trade.model.projection;

import com.market.trade.model.CurrencyPairUsage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigInteger;

@Projection(name = "inlinedCurrencyPairUsage", types = CurrencyPairUsage.class)
public interface InlinedCurrencyPairUsageProjection extends BaseEntityProjection {

    InlinedCurrencySymbolProjection getCurrencyFrom();

    InlinedCurrencySymbolProjection getCurrencyTo();

    BigInteger getAppearances();

    @Value("#{@currencyPairUsageService.getGraphPair(target)}")
    String getDisplayName();
}
