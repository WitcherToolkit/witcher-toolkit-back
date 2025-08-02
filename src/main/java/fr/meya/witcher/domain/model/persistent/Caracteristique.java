package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentant les caractéristiques d'un personnage (DEX, END, etc...)
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Caracteristique {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "IDCARACTERISTIQUE")
	private long idCaracteristique;

	@NotBlank
	@Size( max = 16)
	private String nom;

	@NotBlank
	@Size( max = 6)
	private String code;

	@NotBlank
	private String description;

}
