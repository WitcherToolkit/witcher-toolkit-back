package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe représentant les compétences d'un personnage (connaissance de la rue, arbalète, etc...).
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Competence {

    @PrePersist
    public void generateId() {
        if (this.idCompetence == null) {
            this.idCompetence = UUID.randomUUID();
        }
    }

	@Id
	@Column(name = "id_competence")
	private UUID idCompetence;

	@Size(max = 50)
	private String nom;

	@NotBlank
	private String description;

	@Column(name = "exclusive")
	private boolean exclusive;

	@Size( max = 20)
	private String prerequis;

	@Size( max = 20)
	private String specialisation;

	private int step;

	private String type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_caracteristique", nullable = false, foreignKey = @ForeignKey(name = "fk_competence_caracteristique"))
	private Caracteristique caracteristique;

	@OneToMany(mappedBy = "competence", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompetenceProfession> professionList = new ArrayList<>();

}
