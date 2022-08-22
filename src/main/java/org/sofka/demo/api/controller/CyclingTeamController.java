package org.sofka.demo.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sofka.demo.domain.CyclingTeam;
import org.sofka.demo.repository.CyclingTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CyclingTeamController {
	@Autowired
	private CyclingTeamRepository teamRepository;
	
	@GetMapping("/teams")
	public List<CyclingTeam> findAllCyclingTeams() {
		List<CyclingTeam> teams = new ArrayList<>();
		teamRepository.findAll().forEach(teams::add);
		return teams;
	}
	
	@PostMapping("/newTeam")
	public CyclingTeam saveCyclingTeam(@Validated @RequestBody CyclingTeam newTeam) {
		return teamRepository.save(newTeam);
	}
	
	@GetMapping("/team/{team_code}")
	public ResponseEntity<CyclingTeam> findCyclingTeamByCode(@PathVariable(name = "team_code") String teamCode) {
		Optional<CyclingTeam> team = teamRepository.findCyclingTeamByTeamCode(teamCode);
		if (team.isPresent()) return ResponseEntity.ok().body(team.get());
		else return ResponseEntity.notFound().build();
	}
}
