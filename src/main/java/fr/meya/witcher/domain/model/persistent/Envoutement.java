package fr.meya.witcher.domain.model.persistent;

import fr.meya.witcher.domain.model.enums.DangerEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Envoutement {

    @PrePersist
    public void generateId() {
        if (this.idEnvoutement == null) {
            this.idEnvoutement = UUID.randomUUID();
        }
    }

	@Id
	@Column(name = "id_envoutement")
	private UUID idEnvoutement;

	@NotBlank
	@Size(max = 60)
	private String nom;

	@NotBlank
	@Size(max = 10)
	private String cout;

	@NotBlank
	private String effet;

	@NotBlank
	private String prerequis;

	@NotNull
	@Enumerated(EnumType.STRING)
	private DangerEnum danger;

	//----------------------------------------------------------------------------------------------------------------//
	@OneToMany(mappedBy = "envoutement")
	private List<EnvoutementPersonnage> envoutementPersonnageList = new ArrayList<>();

}
