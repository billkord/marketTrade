package com.market.trade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Message extends BaseEntity {

    @Min(0)
    @NotNull
    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal amountSell;

    @Min(0)
    @NotNull
    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal amountBuy;

    @Min(0)
    @NotNull
    @Column(nullable = false)
    private Double rate;

    @Column
    private Timestamp timePlaced;

    @NotNull
    @Column(nullable = false)
    private String originatingCountry;

    private boolean valid;

    @ManyToOne
    private Person person;

    @ManyToOne
    private CurrencySymbol currencyFrom;

    @ManyToOne
    private CurrencySymbol currencyTo;


    public BigDecimal getAmountSell() {
        return amountSell;
    }

    public void setAmountSell(BigDecimal amountSell) {
        this.amountSell = amountSell;
    }

    public BigDecimal getAmountBuy() {
        return amountBuy;
    }

    public void setAmountBuy(BigDecimal amountBuy) {
        this.amountBuy = amountBuy;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Timestamp getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(Timestamp timePlaced) {
        this.timePlaced = timePlaced;
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
