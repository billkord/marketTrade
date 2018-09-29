package com.market.trade;

import com.market.trade.dto.CurrencyApiWrapper;
import com.market.trade.service.PopulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.market.trade")
@PropertySource("classpath:application.properties")
@SpringBootApplication
public class InitializerApplication implements ApplicationRunner {

    private static final String currencyUrl = "http://apilayer.net/api/list?access_key=d94d23dfe24cc34aeab84c4127eda978";

    @Autowired
    private PopulateService populateService;

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(InitializerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        populateService.initializeDB();
        CurrencyApiWrapper response = restTemplate.getForObject(currencyUrl, CurrencyApiWrapper.class);
        populateService.populateToDB(response);
    }

}
