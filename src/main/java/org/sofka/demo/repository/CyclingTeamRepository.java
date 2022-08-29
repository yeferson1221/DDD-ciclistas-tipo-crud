package org.sofka.demo.repository;

import java.util.Optional;

import org.sofka.demo.domain.CyclingTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * [
 *  CyclingTeamRepository
 *  extiende de CrudRepository metodos usados en el almacenamiento de la BD.
 *  adicinalmente se declara el metodo  (findCyclingTeamByTeamCode) para
 *  listar por codigo de equipo
 * ]
 * @version [1,0.0]
 *
 * * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
@Repository
public interface CyclingTeamRepository extends CrudRepository<CyclingTeam, Integer> {
	public Optional<CyclingTeam> findCyclingTeamByTeamCode(String teamCode);
}
