package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Round;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class RoundDaoImpl implements RoundDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createRound(Round round) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(round);
        entityTransaction.commit();
    }

    @Override
    public Round getRoundById(Long roundId) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Round round = entityManager.find(Round.class, roundId);
        entityTransaction.commit();
        if (round == null) {
            throw new EntityNotFoundException("Can't find Round with roundId" + roundId);
        } else {
            return round;
        }
    }

    @Override
    public void updateRound(Round round) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(round);
        entityTransaction.commit();
    }

    @Override
    public List<Round> getAllRounds() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<Round> query = entityManager.createQuery("SELECT rounds FROM Round AS rounds", Round.class);
        List<Round> allRounds = query.getResultList();
        entityTransaction.commit();
        return allRounds;
    }

    @Override
    public void deleteRound(Round round) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(round);
        entityTransaction.commit();
    }

}
