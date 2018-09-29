package com.market.trade.repository;

import com.market.trade.model.CurrencyPairUsage;
import com.market.trade.model.CurrencySymbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface CurrencyPairUsageRepository extends JpaRepository<CurrencyPairUsage, Long>, CurrencyPairUsageRepositoryCustom {

    CurrencyPairUsage findByCurrencyFromAndCurrencyTo(CurrencySymbol currencyFrom, CurrencySymbol currencyTo);
}
