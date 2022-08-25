package org.sofka.demo.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sofka.demo.domain.Country;
import org.sofka.demo.repository.CountryRepository;
import org.sofka.demo.repository.UserRepositoryIpm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CountryController {
	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private UserRepositoryIpm userRepositoryIpm;

	@GetMapping("/countries")
	public List<Country> findAllCountries(@RequestHeader(value="Authorization") String token) {
		if (!userRepositoryIpm.validateToken(token)) { return null; }
		List<Country> countries = new ArrayList<>();
		countryRepository.findAll().forEach(countries::add);
		return countries;
	}
	
	@GetMapping("/country/{code}")
	public ResponseEntity<Country> findCountryByCode
			(@PathVariable(name = "code") String code, @RequestHeader(value="Authorization") String token) {
		if (!userRepositoryIpm.validateToken(token)) { return null; }
		Optional<Country> country = countryRepository.findCountryByCode(code);
		if (country.isPresent()) {
			return ResponseEntity.ok().body(country.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/newCountry")
	public Country saveCountry(@Validated @RequestBody Country country, @RequestHeader(value="Authorization") String token) {
		if (!userRepositoryIpm.validateToken(token)) { return null; }
		return countryRepository.save(country);
	}
	
	@PutMapping("/country/{id}")
	public Country updateCountry(@RequestBody Country newCountry, @PathVariable int id, @RequestHeader(value="Authorization") String token) {
		if (!userRepositoryIpm.validateToken(token)) { return null; }
		return countryRepository.findById(id).map(country -> {
			country.setName(newCountry.getName());
			country.setCode(newCountry.getCode());
			return countryRepository.save(country);
		})
		.orElseGet(() -> {
			return countryRepository.save(newCountry);
		});
	}
	
	@DeleteMapping("/api/country/{id}")
	public ResponseEntity<Void> deleteCountry(@PathVariable int id, @RequestHeader(value="Authorization") String token) {
		if (!userRepositoryIpm.validateToken(token)) { return null; }
		if (countryRepository.existsById(id))
			countryRepository.deleteById(id);
		else 
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
