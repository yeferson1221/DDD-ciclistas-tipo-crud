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
	
	@Column(name = "competitor_number", length = 5, nullable = false, unique = true)
	private String competitorNumber;
	
	@ManyToOne
	@JoinColumn(name = "id_country", nullable = false)
	private Country country;
	
	@ManyToOne
	@JoinColumn(name = "team_id", nullable = false)
	private CyclingTeam cyclingTeam;
}
