package org.sofka.demo.api.controller;

import org.assertj.core.api.AssertionsForClassTypes;
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


import java.util.List;
import java.util.Optional;

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
    void  saveCyclingTeam(){
        Country country = new Country(1);
        //CyclingTeam cyclingTeam = new CyclingTeam(1, "test", "123");
        CyclingTeam cyclingTeam = new CyclingTeam( 1,"test1", "123", country);
        CyclingTeam cyclingTeamSave = cyclingTeamRepository.save(cyclingTeam);
        assertNotNull(cyclingTeamSave);
    }

    @Test
    @Order(6)
    void  listCyclingTeam(){
        List<CyclingTeam> cyclingTeams = (List<CyclingTeam>) cyclingTeamRepository.findAll();
        for(CyclingTeam cyclingTeam : cyclingTeams){
            log.info(cyclingTeam+"-------------------------------------------------");
        }
        assertThat(cyclingTeams).size().isGreaterThan(0);
    }

    @Test
    @Order(7)
    void findByCyclingTeamCode(){
        String code = "123";
        CyclingTeam cyclingTeam = new CyclingTeam( 1,"test1", "123");
        Optional<CyclingTeam> cyclingTeamsave = cyclingTeamRepository.findCyclingTeamByTeamCode(code);
        log.info(cyclingTeamsave.toString());
        AssertionsForClassTypes.assertThat(cyclingTeam.getTeamCode()).isEqualTo(code);
    }

    @Test
    @Order(4)
    @Rollback(false)
    void updateCyclingTeam(){
        Integer id = 1;
        Country country = new Country(1);
        Optional<CyclingTeam> cyclingTeamUpdate = cyclingTeamRepository.findById(id);
        log.info(cyclingTeamUpdate+"--------------------------------------------");
        AssertionsForClassTypes.assertThat(cyclingTeamUpdate.get().getId()).isEqualTo(id);
        CyclingTeam cyclingTeam = new CyclingTeam("test222","143", country);
        cyclingTeam.setId(id);
        cyclingTeamRepository.save(cyclingTeam);

    }

    @Test
    @Order(5)
    @Rollback(false)
    void deleteCyclingTeam(){
        Integer id = 1;
        boolean countryExistsYes = cyclingTeamRepository.findById(id).isPresent();
        cyclingTeamRepository.deleteById(id);
        boolean countryExistsNot = cyclingTeamRepository.findById(id).isPresent();
        assertTrue(countryExistsYes);
        assertFalse(countryExistsNot);
    }
}