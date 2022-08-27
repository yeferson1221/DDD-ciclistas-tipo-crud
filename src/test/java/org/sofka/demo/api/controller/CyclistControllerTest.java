package org.sofka.demo.api.controller;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sofka.demo.domain.Country;
import org.sofka.demo.domain.CyclingTeam;
import org.sofka.demo.domain.Cyclist;
import org.sofka.demo.repository.CyclistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CyclistControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CyclistControllerTest.class);

    @Autowired
    private CyclistRepository cyclistRepository;

    @Test
    @Order(6)
    @Rollback(false)
    void  saveCyclist(){
        Country country = new Country(1);
        CyclingTeam cyclingTeam = new CyclingTeam(1);
        Cyclist cyclist = new Cyclist( 1,"test1", "123", country, cyclingTeam);
        Cyclist cyclistSave = cyclistRepository.save(cyclist);
        assertNotNull(cyclistSave);
    }
}