package org.sofka.demo.repository;

import java.util.List;
import java.util.Optional;

import org.sofka.demo.domain.Cyclist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CyclistRepository extends CrudRepository<Cyclist, Integer> { 
	public Optional<Cyclist> findCyclistByCompetitorNumber(String competitorNumber);
	public List<Cyclist> findByCyclingTeamTeamCode(String teamCode); 
}
