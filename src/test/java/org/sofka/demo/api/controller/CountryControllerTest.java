package org.sofka.demo.api.controller;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sofka.demo.domain.Country;
import org.sofka.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryControllerTest {
    private static final Logger log = LoggerFactory.getLogger(CountryControllerTest.class);
    @Autowired
    private CountryRepository countryRepository;

    @Test
    @Rollback(false)
    @Order(1)
    void  saveCountry(){
        Country country = new Country( 1,"test123", "331");
        Country countrySave = countryRepository.save(country);
        log.info(countrySave+"hola-----------------------------------------------------------");
        assertNotNull(countrySave);
    }

    @Test
    @Order(3)
    void  listCountry(){
        List<Country> countries = (List<Country>) countryRepository.findAll();
        for(Country country : countries){
            log.info(country+"-------------------------------------------------");
        }
        assertThat(countries).size().isGreaterThan(0);
    }

    @Test
    @Order(2)
    void findByCountryCode(){
        String code = "123";
        Country country = new Country( 1,"test1", "123");
        Optional<Country> countrysave = countryRepository.findCountryByCountryCode(code);
        log.info(countrysave.toString());
        AssertionsForClassTypes.assertThat(country.getCountryCode()).isEqualTo(code);
    }

    @Test
    @Order(4)
    @Rollback(false)
    void updateCountry(){
        Integer idCountry = 1;
        Optional<Country> countryUpdate = countryRepository.findById(idCountry);
        log.info(countryUpdate+"--------------------------------------------");
        AssertionsForClassTypes.assertThat(countryUpdate.get().getId()).isEqualTo(idCountry);
        Country country = new Country("test222","143");
        country.setId(idCountry);
        countryRepository.save(country);

    }

    @Test
    @Order(5)
    @Rollback(false)
    void deleteCounty(){
        Integer idCountry = 1;
        boolean countryExistsYes = countryRepository.findById(idCountry).isPresent();
        countryRepository.deleteById(idCountry);
        boolean countryExistsNot = countryRepository.findById(idCountry).isPresent();
        assertTrue(countryExistsYes);
        assertFalse(countryExistsNot);
    }

}