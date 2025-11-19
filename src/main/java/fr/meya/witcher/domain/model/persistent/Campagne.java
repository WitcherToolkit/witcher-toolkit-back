package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Campagne géré par les utilisateurs.
 */
@Getter
@Setter
@Entity
public class Campagne {

    @PrePersist
    public void generateId() {
        if (this.idCampagne == null) {
            this.idCampagne = UUID.randomUUID();
        }
    }

	@Id
	@Column(name = "id_campagne")
	private UUID idCampagne;

	@NotBlank
	private String nom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_campagne_user"))
	private User user;

}
