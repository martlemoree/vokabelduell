package de.htwberlin.kba.game_management.impl;

import de.htwberlin.kba.game_management.export.Round;
import de.htwberlin.kba.vocab_management.export.Translation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public Round getRoundById(Long roundId) {
        Round round = entityManager.find(Round.class, roundId);
        if (round == null) {
            throw new EntityNotFoundException("Can't find Round with roundId" + roundId);
        } else {
            return round;
        }
    }

    @Override
    public void updateRound(Round round) {
        entityManager.merge(round);
    }

    @Override
    public List<Round> getAllRounds() {
        TypedQuery<Round> query = entityManager.createQuery("SELECT rounds FROM Round AS rounds", Round.class);
        List<Round> allRounds = query.getResultList();
        return allRounds;
    }

    @Override
    public void deleteRound(Round round) {
        entityManager.remove(round);
    }

}
