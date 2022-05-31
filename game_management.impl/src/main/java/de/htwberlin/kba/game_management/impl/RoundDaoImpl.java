package de.htwberlin.kba.game_management.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoundDaoImpl implements RoundDao{

    @PersistenceContext
    private EntityManager em;

}
