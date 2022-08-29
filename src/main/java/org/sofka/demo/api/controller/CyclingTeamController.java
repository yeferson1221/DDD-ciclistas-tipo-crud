package org.sofka.demo.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sofka.demo.domain.CyclingTeam;
import org.sofka.demo.repository.CyclingTeamRepository;
import org.sofka.demo.repository.UserRepositoryIpm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * [
 *  CyclingTeamController
 *  contiene metodos crud y dos inyecciones  (CyclingTeamRepository ,   UserRepositoryIpm)
 *  adicional tiene el metodo (findCyclingTeamByCode) para buscar por el numero de equipo
 *  todas las apis estan protegidas con el token (@RequestHeader(value="Authorization")
 * ]
 * @version [1,0.0]
 *
 * * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
@RestController
@RequestMapping("/api")
public class CyclingTeamController {
	@Autowired
	private CyclingTeamRepository teamRepository;

	@Autowired
	private UserRepositoryIpm userRepositoryIpm;


	@GetMapping("/teams")
	public List<CyclingTeam> findAllCyclingTeams(@RequestHeader(value="Authorization") String token) {
		if (!userRepositoryIpm.validateToken(token)) { return null; }
		List<CyclingTeam> teams = new ArrayList<>();
		teamRepository.findAll().forEach(teams::add);
		return teams;
	}

	@PostMapping("/newTeam")
	public CyclingTeam saveCyclingTeam(@Validated @RequestBody CyclingTeam newTeam, @RequestHeader(value="Authorization") String token) {
		if (!userRepositoryIpm.validateToken(token)) { return null; }
		return teamRepository.save(newTeam);
	}
	
	@GetMapping("/team/{team_code}")
	public ResponseEntity<CyclingTeam> findCyclingTeamByCode(
			@PathVariable(name = "team_code") String teamCode, @RequestHeader(value="Authorization") String token) {
		    	Optional<CyclingTeam> team = teamRepository.findCyclingTeamByTeamCode(teamCode);
				if (!userRepositoryIpm.validateToken(token)) { return null; }
				if (team.isPresent()) return ResponseEntity.ok().body(team.get());
				else return ResponseEntity.notFound().build();
	}
}
