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
import org.sofka.demo.domain.Cyclist;
import org.sofka.demo.repository.CyclistRepository;
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
class CyclistControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CyclistControllerTest.class);

    @Autowired
    private CyclistRepository cyclistRepository;

    @Test
    @Order(1)
    @Rollback(false)
    void  saveCyclist(){
        Country country = new Country(1);
        CyclingTeam cyclingTeam = new CyclingTeam(1);
        Cyclist cyclist = new Cyclist( 1,"test1", "123", country, cyclingTeam);
        Cyclist cyclistSave = cyclistRepository.save(cyclist);
        assertNotNull(cyclistSave);
    }

    @Test
    @Order(3)
    void  listCyclist(){
        List<Cyclist> cyclists = (List<Cyclist>) cyclistRepository.findAll();
        for(Cyclist cyclist : cyclists){
            log.info(cyclist+"-------------------------------------------------");
        }
        assertThat(cyclists).size().isGreaterThan(0);
    }

    @Test
    @Order(2)
    void findByCyclistCode(){
        Country country = new Country(1);
        CyclingTeam cyclingTeam = new CyclingTeam(1);
        String code = "123";
        Cyclist cyclist = new Cyclist( "test1", "123",country, cyclingTeam );
        Optional<Cyclist> cyclistsave = cyclistRepository.findCyclistByCompetitorNumber(code);
        log.info(cyclistsave.toString());
        AssertionsForClassTypes.assertThat(cyclist.getCompetitorNumber()).isEqualTo(code);
    }

    @Test
    @Order(2)
    void findByCyclistTeamCode(){
        Country country = new Country(1);
        CyclingTeam cyclingTeam = new CyclingTeam(1);
        String code = "123";
        Cyclist cyclist = new Cyclist( "test1", "123",country, cyclingTeam );
        List<Cyclist> cyclistsave = cyclistRepository.findByCyclingTeamTeamCode(code);
        log.info(cyclistsave.toString());
        AssertionsForClassTypes.assertThat(cyclist.getCompetitorNumber()).isEqualTo(code);
    }

    @Test
    @Order(4)
    @Rollback(false)
    void updateCountry(){
        Integer id = 1;
        Country country = new Country(1);
        CyclingTeam cyclingTeam = new CyclingTeam(1);
        Optional<Cyclist> cyclistUpdate = cyclistRepository.findById(id);
        log.info(cyclistUpdate+"--------------------------------------------");
        AssertionsForClassTypes.assertThat(cyclistUpdate.get().getId()).isEqualTo(id);
        Cyclist cyclist = new Cyclist("test222","143",country, cyclingTeam);
        country.setId(id);
        cyclistRepository.save(cyclist);

    }

    @Test
    @Order(5)
    @Rollback(false)
    void deleteCounty(){
        Integer id = 1;
        boolean cyclistExistsYes = cyclistRepository.findById(id).isPresent();
        cyclistRepository.deleteById(id);
        boolean cyclistExistsNot = cyclistRepository.findById(id).isPresent();
        assertTrue(cyclistExistsYes);
        assertFalse(cyclistExistsNot);
    }
}