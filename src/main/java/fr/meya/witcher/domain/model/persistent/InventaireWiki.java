package fr.meya.witcher.domain.model.persistent;

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
@Table(name = "inventaire_wiki")
public class InventaireWiki {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventaire_wiki")
    private long idInventaireWiki;

    @NotNull
    private int quantite;

    @NotBlank
    @Size(max = 50)
    private String nom;

    @Size(max = 10)
    private String type;

    private String effet;

    @Column(name = "is_special")
    private  boolean isSpecial;

    @ManyToOne
    @JoinColumn(name = "id_profession")
    private Profession profession;

}
