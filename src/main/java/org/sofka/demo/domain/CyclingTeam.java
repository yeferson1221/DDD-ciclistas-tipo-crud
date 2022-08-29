package org.sofka.demo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * [
 *  model CyclingTeam contiene sus parametros  y contructortes, @Data  getter y setter
 * ]
 * @version [1,0.0]
 *
 * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor
public class CyclingTeam {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length = 30, nullable=false, unique=false)
	private String name;
	
	@Column(name = "team_code", length = 3, nullable=false, unique=true)
	private String teamCode;
	
	@ManyToOne
	@JoinColumn(name = "id_country", nullable = false)
	private Country country;
	
	@OneToMany(mappedBy = "cyclingTeam", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Cyclist> cyclists;

	public CyclingTeam(int id, String name, String teamCode) {
		this.id = id;
		this.name = name;
		this.teamCode = teamCode;
	}

	public CyclingTeam(String name, String teamCode, Country country) {
		this.name = name;
		this.teamCode = teamCode;
		this.country = country;
	}
	public CyclingTeam(int id, String name, String teamCode, Country country) {
		this.id = id;
		this.name = name;
		this.teamCode = teamCode;
		this.country = country;

	}
	public CyclingTeam(int id) {
		this.id = id;
	}
}
