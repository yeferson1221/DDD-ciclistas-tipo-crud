package org.sofka.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * [
 *  model Cyclist contiene sus parametros  y contructortes, @Data  getter y setter
 * ]
 * @version [1,0.0]
 *
 * @author [Yeferson Valencia, yeferson.valencia@sofka.com.co]
 * @since [1,0,0]
 *
 */
@Entity
@Table(name = "cyclists")
@Data
@NoArgsConstructor
public class Cyclist {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "full_name", length = 100, nullable = false)
	private String fullName;
	
	@Column(name = "competitor_number", length = 3, nullable = false, unique = true)
	private String competitorNumber;
	
	@ManyToOne
	@JoinColumn(name = "id_country", nullable = false)
	private Country country;
	
	@ManyToOne
	@JoinColumn(name = "team_id", nullable = false)
	private CyclingTeam cyclingTeam;

	public Cyclist(int id, String fullName, String competitorNumber, Country country, CyclingTeam cyclingTeam) {
		this.id = id;
		this.fullName = fullName;
		this.competitorNumber = competitorNumber;
		this.country = country;
		this.cyclingTeam = cyclingTeam;
	}

	public Cyclist( String fullName, String competitorNumber, Country country, CyclingTeam cyclingTeam) {
		this.fullName = fullName;
		this.competitorNumber = competitorNumber;
		this.country = country;
		this.cyclingTeam = cyclingTeam;
	}

	public Cyclist(int id) {
		this.id = id;
	}
}
