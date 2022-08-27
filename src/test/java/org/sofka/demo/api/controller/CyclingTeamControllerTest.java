package org.sofka.demo.api.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sofka.demo.domain.Country;
import org.sofka.demo.domain.CyclingTeam;
import org.sofka.demo.repository.CyclingTeamRepository;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CyclingTeamControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CyclingTeamController.class);

    @Autowired
    CyclingTeamRepository cyclingTeamRepository;

    @Test
    @Order(5)
    @Rollback(false)
    void  saveCyclist(){
        Country country = new Country(1);
        //CyclingTeam cyclingTeam = new CyclingTeam(1, "test", "123");
        CyclingTeam cyclingTeam = new CyclingTeam( 1,"test1", "123", country);
        CyclingTeam cyclingTeamSave = cyclingTeamRepository.save(cyclingTeam);
        assertNotNull(cyclingTeamSave);
    }

}