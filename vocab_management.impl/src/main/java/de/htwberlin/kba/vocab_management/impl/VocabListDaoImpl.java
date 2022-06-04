package de.htwberlin.kba.vocab_management.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class VocabListDaoImpl implements VocabListDao{

    @PersistenceContext
    private EntityManager em;


}
