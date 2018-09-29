package com.market.trade.repository;

import com.market.trade.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Long> {

    @RestResource(exported = false)
    Person findByEmail(String email);
}
