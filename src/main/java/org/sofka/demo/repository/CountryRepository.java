package org.sofka.demo.repository;

import java.util.Optional;

import org.sofka.demo.domain.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * [
 *  CountryRepository
 *  extiende de CrudRepository metodos usados en el almacenamiento de la BD.
 *  adicinalmete se declara el metodo  (findCountryByCountryCode) para
 *  listar por codigo de ciudad
 * ]
 * @version [1,0.0]
 *
 * * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
	Optional<Country> findCountryByCountryCode(String countryCode);
}
