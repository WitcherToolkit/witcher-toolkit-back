package fr.meya.witcher.domain.port.out;

import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.infrastructure.adapter.out.IProfessionRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessionRepositoryCustomImpl implements IProfessionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    //@Override
    public List<Profession> getListeDesProfessionsAvecCompetences() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profession> query = cb.createQuery(Profession.class);
        Root<Profession> root = query.from(Profession.class);

        root.fetch("competenceSpecifique", JoinType.LEFT);
        query.orderBy(cb.asc(root.get("nom")));

        return entityManager.createQuery(query).getResultList();
    }
}
