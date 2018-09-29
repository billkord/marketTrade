package com.market.trade.model.projection;

import com.market.trade.model.CurrencySymbol;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlinedCurrencySymbol", types = CurrencySymbol.class)
public interface InlinedCurrencySymbolProjection extends BaseEntityProjection {

    String getSymbol();

    String getName();
}
