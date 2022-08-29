package org.sofka.demo.repository;

import java.util.List;
import java.util.Optional;

import org.sofka.demo.domain.Cyclist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * [
 *  CyclistRepository
 *  extiende de CrudRepository metodos usados en el almacenamiento de la BD.
 *  adicinalmente se declara el metodo  (findCyclistByCompetitorNumber) para
 *  listar por codigo de competidor y
 *  (findByCyclingTeamTeamCode) listar por codigo de equipo
 * ]
 * @version [1,0.0]
 *
 * * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
@Repository
public interface CyclistRepository extends CrudRepository<Cyclist, Integer> { 
	public Optional<Cyclist> findCyclistByCompetitorNumber(String competitorNumber);
	public List<Cyclist> findByCyclingTeamTeamCode(String teamCode); 
}
