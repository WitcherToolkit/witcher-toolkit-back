package fr.meya.witcher.domain.model.persistent;

import fr.meya.witcher.domain.model.enums.DangerEnum;
import fr.meya.witcher.domain.model.enums.TypeCaracteristiqueEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Classe représentant les caractéristiques d'un personnage (DEX, END, etc...)
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Caracteristique {

    @PrePersist
    public void generateId() {
        if (this.idCaracteristique == null) {
            this.idCaracteristique = UUID.randomUUID();
        }
    }

	@Id
	@Column(name = "id_caracteristique")
	private UUID idCaracteristique;

	@NotBlank
	@Size( max = 16)
	private String nom;

	@NotBlank
	@Size( max = 6)
	private String code;

	@NotBlank
	private String description;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TypeCaracteristiqueEnum type;

}
