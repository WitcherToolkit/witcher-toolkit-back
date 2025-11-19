package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Rituel {

    @PrePersist
    public void generateId() {
        if (this.idRituel == null) {
            this.idRituel = UUID.randomUUID();
        }
    }

	@Id
	@Column(name = "id_rituel")
	private UUID idRituel;

	@NotBlank
	@Size(max = 60)
	private String nom;

	@NotBlank
	@Size(max = 10)
	private String cout;

	@NotBlank
	private String effet;

	@NotBlank
	@Size(max = 10)
	@Column(name = "temps_preparation")
	private String tempsPreparation;

	@NotBlank
	private String sd;

	@NotBlank
	@Size(max = 15)
	private String duree;

	@NotBlank
	private String composant;

	@NotBlank
	@Size(max = 20)
	private String niveau;

	//----------------------------------------------------------------------------------------------------------------//
	@OneToMany(mappedBy = "rituel")
	private List<RituelPersonnage> rituelPersonnageList = new ArrayList<>();

}
