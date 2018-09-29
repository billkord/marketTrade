package com.market.trade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import java.math.BigInteger;

@Entity
public class CurrencyPairUsage extends BaseEntity {

    @ManyToOne
    private CurrencySymbol currencyFrom;

    @ManyToOne
    private CurrencySymbol currencyTo;

    @Min(0)
    @Column
    private BigInteger appearances;


    public CurrencySymbol getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(CurrencySymbol currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public CurrencySymbol getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(CurrencySymbol currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigInteger getAppearances() {
        return appearances;
    }

    public void setAppearances(BigInteger appearances) {
        this.appearances = appearances;
    }
}
