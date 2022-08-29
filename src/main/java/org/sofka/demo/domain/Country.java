package org.sofka.demo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;


/**
 * [
 *  model Country contiene sus parametros  y contructortes, @Data  getter y setter
 * ]
 * @version [1,0.0]
 *
 * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
public class Country {
	@Id
	@Column(name = "id" )
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length=50, nullable=false, unique=false)
	private String name;
	
	@Column(name = "country_code", length=3, nullable=false, unique=true)
	private String countryCode;
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CyclingTeam> cyclingTeams;
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Cyclist> cyclists;

	public Country(int id, String name, String countryCode, List<CyclingTeam> cyclingTeams, List<Cyclist> cyclists) {
		this.id = id;
		this.name = name;
		this.countryCode = countryCode;
		this.cyclingTeams = cyclingTeams;
		this.cyclists = cyclists;
	}

	public Country(int id, String name, String countryCode) {
		this.id = id;
		this.name = name;
		this.countryCode = countryCode;
	}
	public Country(String name, String countryCode) {
		this.name = name;
		this.countryCode = countryCode;
	}

	public Country(int id) {
		this.id = id;
	}
}
