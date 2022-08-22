package org.sofka.demo.repository;

import java.util.Optional;

import org.sofka.demo.domain.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
	Optional<Country> findCountryByCode(String code);
}
