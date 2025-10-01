package fr.meya.witcher.domain.model.persistent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventaire")
public class Inventaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventaire")
    private Long idInventaire;

    @NotNull
    private int quantite;

    @NotBlank
    @Size(max = 50)
    private String nom;

    @Size(max = 10)
    private String type;

    private String effet;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_personnage", nullable = false, foreignKey = @ForeignKey(name = "fk_inventaire_personnage"))
    @JsonIgnore
    private Personnage personnage;

}
