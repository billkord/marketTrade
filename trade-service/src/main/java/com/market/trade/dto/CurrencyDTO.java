package com.market.trade.dto;

public class CurrencyDTO {

    private String symbol;

    private String name;


    private CurrencyDTO() {
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    private void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    private void setName(String name) {
        this.name = name;
    }

    public static final class CurrencyDTOBuilder {
        private String symbol;
        private String name;

        private CurrencyDTOBuilder() {}

        public static CurrencyDTOBuilder aCurrencyDTO() {
            return new CurrencyDTOBuilder();
        }

        public CurrencyDTOBuilder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public CurrencyDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CurrencyDTO build() {
            CurrencyDTO currencyDTO = new CurrencyDTO();
            currencyDTO.setSymbol(symbol);
            currencyDTO.setName(name);
            return currencyDTO;
        }
    }
}
