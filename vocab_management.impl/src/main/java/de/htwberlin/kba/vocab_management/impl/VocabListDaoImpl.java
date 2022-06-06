package de.htwberlin.kba.vocab_management.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Repository
public class VocabListDaoImpl implements VocabListDao{

    @PersistenceContext
    private EntityManager em;


}
