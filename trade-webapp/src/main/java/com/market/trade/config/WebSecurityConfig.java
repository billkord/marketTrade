package com.market.trade.config;


import com.market.trade.utils.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/",
                "/api/messages/search/findTotal",
                "/api/currencyPairUsages/search/findTopTenTotal",
                "/api/currencyPairUsages/search/findTopTenMonth",
                "/api/currencyPairUsages/search/findTopTenWeek");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsFilter(), ChannelProcessingFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

        // Messages
        http.authorizeRequests().antMatchers(POST, "/api/messages/**").hasAuthority(Permissions.TRADE_PERMISSION.getValue());
        http.authorizeRequests().antMatchers(GET, "/api/messages/**").hasAuthority(Permissions.TRADE_PERMISSION.getValue());
        http.authorizeRequests().antMatchers(PATCH, "/api/messages/**").hasAuthority(Permissions.TRADE_PERMISSION.getValue());
        http.authorizeRequests().antMatchers(DELETE, "/api/messages/**").hasAuthority(Permissions.TRADE_PERMISSION.getValue());

        // CurrencyPairUsages
        http.authorizeRequests().antMatchers(POST, "/api/currencyPairUsages/**").hasAuthority(Permissions.TRADE_PERMISSION.getValue());
        http.authorizeRequests().antMatchers(GET, "/api/currencyPairUsages/**").hasAuthority(Permissions.TRADE_PERMISSION.getValue());
        http.authorizeRequests().antMatchers(PATCH, "/api/currencyPairUsages/**").hasAuthority(Permissions.TRADE_PERMISSION.getValue());
        http.authorizeRequests().antMatchers(DELETE, "/api/currencyPairUsages/**").hasAuthority(Permissions.TRADE_PERMISSION.getValue());

        // CurrencySymbols
        http.authorizeRequests().antMatchers(POST, "/api/currencySymbols/**").hasAuthority(Permissions.TRADE_PERMISSION.getValue());
        http.authorizeRequests().antMatchers(GET, "/api/currencySymbols/**").hasAuthority(Permissions.TRADE_PERMISSION.getValue());
        http.authorizeRequests().antMatchers(PATCH, "/api/currencySymbols/**").hasAuthority(Permissions.TRADE_PERMISSION.getValue());
        http.authorizeRequests().antMatchers(DELETE, "/api/currencySymbols/**").hasAuthority(Permissions.TRADE_PERMISSION.getValue());

        // Persons
        http.authorizeRequests().antMatchers(POST, "/api/persons/**").hasAuthority(Permissions.ADMIN.getValue());
        http.authorizeRequests().antMatchers(GET, "/api/persons/**").hasAuthority(Permissions.ADMIN.getValue());
        http.authorizeRequests().antMatchers(PATCH, "/api/persons/**").hasAuthority(Permissions.ADMIN.getValue());
        http.authorizeRequests().antMatchers(DELETE, "/api/persons/**").hasAuthority(Permissions.ADMIN.getValue());

        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
