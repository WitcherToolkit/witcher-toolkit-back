package fr.meya.witcher.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les caractéristiques d'un personnage (DEX, END, etc...)
 */
@Getter
@Setter
@Entity
public class Caracteristique {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "IDCARACTERISTIQUE")
	private long idCaracteristique;

	@NotBlank
	private String nom;

	@NotBlank
	private String code;

	@NotBlank
	private String description;

}
