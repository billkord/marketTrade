package com.market.trade.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class CurrencySymbol extends BaseEntity {

    @NotNull
    @Column(nullable = false)
    private String symbol;

    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "currencyFrom")
    private Collection<Message> messagesFrom;

    @OneToMany(mappedBy = "currencyTo")
    private Collection<Message> messagesTo;


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Message> getMessagesFrom() {
        return messagesFrom;
    }

    public void setMessagesFrom(Collection<Message> messagesFrom) {
        this.messagesFrom = messagesFrom;
    }

    public Collection<Message> getMessagesTo() {
        return messagesTo;
    }

    public void setMessagesTo(Collection<Message> messagesTo) {
        this.messagesTo = messagesTo;
    }
}
