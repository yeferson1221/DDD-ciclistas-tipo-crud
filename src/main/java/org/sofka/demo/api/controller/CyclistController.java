package org.sofka.demo.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.sofka.demo.domain.Cyclist;
import org.sofka.demo.repository.CyclistRepository;
import org.sofka.demo.repository.UserRepositoryIpm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * [
 *  CyclistController
 *  contiene metodos crud y dos inyecciones  (CyclistRepository ,   UserRepositoryIpm)
 *  adicional tiene el metodo (findCyclistByCompetitorNumber) para buscar por el numero de competidor
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
public class CyclistController {
	@Autowired
	private CyclistRepository cyclistRepository;
	
	@Autowired
    private UserRepositoryIpm userRepositoryIpm;
	
	
	@GetMapping("/cyclists")
	public ResponseEntity<List<Cyclist>> findAllCyclists(
			@RequestParam Map<String, String> reqParam , @RequestHeader(value="Authorization") String token) {
		if (!userRepositoryIpm.validateToken(token)) { return null; }
		if (!reqParam.isEmpty()) return ResponseEntity.badRequest().build(); 
		List<Cyclist> cyclists = new ArrayList<>();
		cyclistRepository.findAll().forEach(cyclists::add);
		return ResponseEntity.ok().body(cyclists);
	}

	

	@PostMapping("/newCyclist")
	public Cyclist saveNewCyclist(@Validated @RequestBody Cyclist newCyclist, @RequestHeader(value="Authorization") String token) {
		if (!userRepositoryIpm.validateToken(token)) { return null; }
		return cyclistRepository.save(newCyclist);
	}
	
	@GetMapping("/cyclist/{competitorNumber}")
	public ResponseEntity<Cyclist> findCyclistByCompetitorNumber(
			@PathVariable(name = "competitorNumber") String competitorNumber, @RequestHeader(value="Authorization") String token) {
		if (!userRepositoryIpm.validateToken(token)) { return null; }
		Optional<Cyclist> cyclist = cyclistRepository.findCyclistByCompetitorNumber(competitorNumber);
		if (cyclist.isPresent()) return ResponseEntity.ok().body(cyclist.get());
		else return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/cyclists", method = RequestMethod.GET, params = "teamCode")
	public List<Cyclist> findCyclistsByTeamCode(
			@RequestParam(name = "teamCode") String teamCode,@RequestHeader(value="Authorization") String token) {
		if (!userRepositoryIpm.validateToken(token)) { return null; }
		List<Cyclist> cyclists = new ArrayList<>();
		cyclistRepository.findByCyclingTeamTeamCode(teamCode).forEach(cyclists::add);
		return cyclists;
	}

}
