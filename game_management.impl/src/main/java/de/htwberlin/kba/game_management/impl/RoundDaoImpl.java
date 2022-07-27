package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.CustomLockException;
import de.htwberlin.kba.game_management.export.CustomObjectNotFoundException;
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
        entityManager.persist(round);
    }

    @Override
    public Round getRoundById(Long roundId) throws CustomObjectNotFoundException {
        Round round = null;
        try {
            round = entityManager.find(Round.class, roundId);
        } catch (NoResultException e) {
            throw new CustomObjectNotFoundException("Can't find Round with roundId" + roundId);
        }
        return round;
    }

    @Override
    public void updateRound(Round round) throws CustomLockException {
        try {
            entityManager.merge(round);
        } catch (OptimisticLockException e) {
            throw new CustomLockException("Das Update der Round konnte leider nicht durchgeführt werden. Der Vorgang wird wiederholt.");
        }
    }

    @Override
    public List<Round> getAllRounds() {
        TypedQuery<Round> query = entityManager.createNamedQuery("getAllRounds", Round.class);
        List<Round> allRounds = query.getResultList();
        return allRounds;
    }

    @Override
    public void deleteRound(Round round) {
        entityManager.remove(round);
    }
}
