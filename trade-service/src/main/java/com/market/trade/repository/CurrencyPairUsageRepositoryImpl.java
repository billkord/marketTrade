package com.market.trade.repository;

import com.market.trade.model.CurrencyPairUsage;
import com.market.trade.model.CurrencySymbol;
import com.market.trade.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class CurrencyPairUsageRepositoryImpl implements CurrencyPairUsageRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<CurrencyPairUsage> findTopTen(String duration) {
        Collection<CurrencyPairUsage> currencyPairUsages;
        switch (duration) {
            case "TOTAL":
                currencyPairUsages = findTopTenTotal();
                break;
            case "MONTH":
                currencyPairUsages = getCurrencyPairUsages(startOfMonth());
                break;
            case "WEEK":
                currencyPairUsages = getCurrencyPairUsages(startOfWeek());
                break;
            default:
                currencyPairUsages = findTopTenTotal();
                break;
        }
        return currencyPairUsages;
    }

    private Collection<CurrencyPairUsage> findTopTenTotal () {
        TypedQuery<CurrencyPairUsage> query = entityManager.createQuery("SELECT cpu FROM CurrencyPairUsage cpu ORDER BY cpu.appearances DESC", CurrencyPairUsage.class);
        query.setMaxResults(10);
        return query.getResultList();
    }

    private Collection<CurrencyPairUsage> getCurrencyPairUsages(Timestamp timestamp) {
        TypedQuery<Message> query = entityManager.createQuery("SELECT m FROM Message m WHERE m.timePlaced > ?1", Message.class);
        query.setParameter(1, addDays(timestamp, -1));
        Collection<Message> messages = query.getResultList();

        Map<CurrencyFromTo, List<Message>> map = messages
                .stream()
                .collect(groupingBy(m -> new CurrencyFromTo(m.getCurrencyFrom(), m.getCurrencyTo())));

        Map<CurrencyFromTo, BigInteger> countMap = map
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> BigInteger.valueOf(entry.getValue().size())));

        return countMap
                .entrySet()
                .stream()
                .map(this::createCurrencyPairUsage)
                .collect(Collectors.toList());
    }

    private Timestamp addDays(Timestamp timestamp, int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        return new Timestamp(calendar.getTime().getTime());
    }

    private Timestamp startOfMonth () {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        Timestamp now = Timestamp.from(utc.toInstant());
        Date today = new Date(now.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        Date startOfMonth = java.sql.Date.valueOf(year + "-" + month + "-01");
        return new Timestamp(startOfMonth.getTime());
    }

    private Timestamp startOfWeek () {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        Timestamp now = Timestamp.from(utc.toInstant());
        Date today = new Date(now.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        Date startOfWeek = calendar.getTime();
        return new Timestamp(startOfWeek.getTime());
    }

    private CurrencyPairUsage createCurrencyPairUsage (Map.Entry<CurrencyFromTo, BigInteger> entry) {
        CurrencyPairUsage currencyPairUsage = new CurrencyPairUsage();
        currencyPairUsage.setId(-1L);
        currencyPairUsage.setCurrencyFrom(entry.getKey().getCurrencyFrom());
        currencyPairUsage.setCurrencyTo(entry.getKey().getCurrencyTo());
        currencyPairUsage.setAppearances(entry.getValue());
        return currencyPairUsage;
    }

    private static class CurrencyFromTo {
        private CurrencySymbol currencyFrom;
        private CurrencySymbol currencyTo;

        public CurrencyFromTo(CurrencySymbol currencyFrom, CurrencySymbol currencyTo) {
            this.currencyFrom = currencyFrom;
            this.currencyTo = currencyTo;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CurrencyFromTo that = (CurrencyFromTo) o;
            if (currencyFrom == null || currencyTo == null) return false;
            return Objects.equals(currencyFrom.getSymbol(), that.currencyFrom.getSymbol()) &&
                    Objects.equals(currencyTo.getSymbol(), that.currencyTo.getSymbol());
        }

        @Override
        public int hashCode() {
            return (currencyFrom == null || currencyTo == null) ? 0 : Objects.hash(currencyFrom.getSymbol(), currencyTo.getSymbol());
        }
    }
}
