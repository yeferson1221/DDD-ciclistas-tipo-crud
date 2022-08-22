package org.sofka.demo.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.sofka.demo.domain.Cyclist;
import org.sofka.demo.repository.CyclistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CyclistController {
	@Autowired
	private CyclistRepository cyclistRepository;
	
	@GetMapping("/api/cyclists")
	public ResponseEntity<List<Cyclist>> findAllCyclists(
			@RequestParam Map<String, String> reqParam) {
		if (!reqParam.isEmpty()) return ResponseEntity.badRequest().build(); 
		List<Cyclist> cyclists = new ArrayList<>();
		cyclistRepository.findAll().forEach(cyclists::add);
		return ResponseEntity.ok().body(cyclists);
	}
	
	@PostMapping("/api/newCyclist")
	public Cyclist saveNewCyclist(@Validated @RequestBody Cyclist newCyclist) {
		return cyclistRepository.save(newCyclist);
	}
	
	@GetMapping("/api/cyclist/{competitorNumber}")
	public ResponseEntity<Cyclist> findCyclistByCompetitorNumber(
			@PathVariable(name = "competitorNumber") String competitorNumber) {
		Optional<Cyclist> cyclist = cyclistRepository.findCyclistByCompetitorNumber(competitorNumber);
		if (cyclist.isPresent()) return ResponseEntity.ok().body(cyclist.get());
		else return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/api/cyclists", method = RequestMethod.GET, params = "teamCode")
	public List<Cyclist> findCyclistsByTeamCode(
			@RequestParam(name = "teamCode") String teamCode) {
		List<Cyclist> cyclists = new ArrayList<>();
		cyclistRepository.findByCyclingTeamTeamCode(teamCode).forEach(cyclists::add);
		return cyclists;
	}

}
