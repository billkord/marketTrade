package com.market.trade.repository;

import com.market.trade.model.CurrencySymbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CurrencySymbolRepository extends JpaRepository<CurrencySymbol, Long> {

    CurrencySymbol findBySymbolAndName(String symbol, String name);

    CurrencySymbol findBySymbol(String symbol);
}
