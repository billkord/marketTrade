package com.market.trade.repository;

import com.market.trade.model.CurrencyPairUsage;

import java.util.Collection;

public interface CurrencyPairUsageRepositoryCustom {

    Collection<CurrencyPairUsage> findTopTen(String duration);
}
