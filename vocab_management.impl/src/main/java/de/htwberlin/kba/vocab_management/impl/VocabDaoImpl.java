package de.htwberlin.kba.vocab_management.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class VocabDaoImpl implements VocabDao{

    @PersistenceContext
    private EntityManager em;

}
