package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Particularite {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "IDPARTICULARITE")
    private Long idParticularite;

    private String nom;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDRACE", nullable = false)
    private Race race;

}
