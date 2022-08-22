package org.sofka.demo.repository;

import java.util.Optional;

import org.sofka.demo.domain.CyclingTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CyclingTeamRepository extends CrudRepository<CyclingTeam, Integer> {
	public Optional<CyclingTeam> findCyclingTeamByTeamCode(String teamCode);
}
