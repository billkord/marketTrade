package com.market.trade.controller;

import com.market.trade.model.CurrencyPairUsage;
import com.market.trade.service.CurrencyPairUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@BasePathAwareController
@RequestMapping("currencyPairUsages")
public class CurrencyPairUsageController {

    @Autowired
    private CurrencyPairUsageService currencyPairUsageService;

    private Resources<?> findTopTen(String duration, PersistentEntityResourceAssembler assembler) {
        Collection<CurrencyPairUsage> topTenCurrencyPairUsages = currencyPairUsageService.findTopTen(duration)
                .stream()
                .sorted(Comparator.comparing(CurrencyPairUsage::getAppearances).reversed())
                .collect(Collectors.toList());
        List<PersistentEntityResource> resources = topTenCurrencyPairUsages
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new  Resources<>(resources);
    }

    @GetMapping("search/findTopTenTotal")
    public ResponseEntity<?> findTopTen (PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(findTopTen("TOTAL", assembler));
    }

    @GetMapping("search/findTopTenMonth")
    public ResponseEntity<?> findTopTenMonth (PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(findTopTen("MONTH", assembler));
    }

    @GetMapping("search/findTopTenWeek")
    public ResponseEntity<?> findTopTenWeek (PersistentEntityResourceAssembler assembler) {
        return ResponseEntity.ok(findTopTen("WEEK", assembler));
    }
}
