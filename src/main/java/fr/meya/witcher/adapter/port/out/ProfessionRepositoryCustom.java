package fr.meya.witcher.adapter.port.out;

import fr.meya.witcher.model.persistent.CompetenceSpecifique;
import fr.meya.witcher.model.persistent.Profession;
import fr.meya.witcher.port.out.IProfessionRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessionRepositoryCustom implements IProfessionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Profession> exempleDeMethode(String name, String competence) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profession> query = cb.createQuery(Profession.class);
        Root<Profession> root = query.from(Profession.class);

        // Rejoindre "CompetenceSpecifique"
        Join<Profession, CompetenceSpecifique> competences = root.join("competences");

        // Créer des prédicats pour les critères
        Predicate namePredicate = cb.like(root.get("name"), "%" + name + "%");
        Predicate competencePredicate = cb.like(competences.get("name"), "%" + competence + "%");

        // Appliquer les prédicats
        query.where(cb.and(namePredicate, competencePredicate));

        return entityManager.createQuery(query).getResultList();
    }
}
