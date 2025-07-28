package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INVENTAIREWIKI")
public class InventaireWiki {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDINVENTAIREWIKI")
    private long idInventaireWiki;

    private int quantite;

    private String nom;

    private String type;

    private String effet;

    @Column(name = "ISSPECIAL")
    private  boolean isSpecial;

    @ManyToOne
    @JoinColumn(name = "IDPROFESSION")
    private Profession profession;

}
