package com.indra.avitech.producerconsumer.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {
    private static EntityManager entityManager;

    public static synchronized EntityManager createOrGetEntityManager() {
        if(entityManager == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.indra-avitech.hibernate.entitynotfoundexception.h2_persistence_unit");
            EntityManagerProvider.entityManager = emf.createEntityManager();
            return EntityManagerProvider.entityManager;
        }

        return EntityManagerProvider.entityManager;
    }
}
