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

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventaire_wiki")
public class InventaireWiki {

    @PrePersist
    public void generateId() {
        if (this.idInventaireWiki == null) {
            this.idInventaireWiki = UUID.randomUUID();
        }
    }

    @Id
    @Column(name = "id_inventaire_wiki")
    private UUID idInventaireWiki;

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
    @JsonIgnore
    @JoinColumn(name = "id_profession", foreignKey = @ForeignKey(name = "fk_inventaire_wiki_profession"))
    private Profession profession;

}
