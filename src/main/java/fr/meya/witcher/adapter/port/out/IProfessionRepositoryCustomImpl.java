package fr.meya.witcher.adapter.port.out;

import fr.meya.witcher.model.persistent.Profession;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IProfessionRepositoryCustomImpl implements IProfessionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Profession> getListeDesProfessionsAvecCompetences() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profession> query = cb.createQuery(Profession.class);
        Root<Profession> root = query.from(Profession.class);

        // Joindre avec CompetenceSpecifique
        root.fetch("competenceSpecifique", JoinType.LEFT);

        // Tri par nom de la profession, puis nom des compétences
        query.orderBy(cb.asc(root.get("nom")));

        return entityManager.createQuery(query).getResultList();
    }
}
