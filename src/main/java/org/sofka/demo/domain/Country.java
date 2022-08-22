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

@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
public class Country {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length=50, nullable=false, unique=false)
	private String name;
	
	@Column(name = "code", length=5, nullable=false, unique=true)
	private String code;
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	@JsonIgnore
	//@JsonSerialize(using = CyclingTeamListSerializer.class)
	private List<CyclingTeam> cyclingTeams;
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Cyclist> cyclists;
}
