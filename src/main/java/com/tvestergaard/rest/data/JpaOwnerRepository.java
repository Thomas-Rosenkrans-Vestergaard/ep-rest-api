package com.tvestergaard.rest.data;

import com.tvestergaard.rest.entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

public class JpaOwnerRepository implements OwnerRepository
{

    private final EntityManagerFactory entityManagerFactory;

    public JpaOwnerRepository(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Finds all the owners in the system.
     *
     * @return The complete list of the owners in the system.
     */
    @Override public List<Owner> get()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createNamedQuery("Owner.findAll", Owner.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Returns the owner with the provided id.
     *
     * @param id The id of the owner to return.
     * @return The owner with the provided id, {@code null} when no such entity exists.
     */
    @Override public Owner get(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createNamedQuery("Owner.findById", Owner.class)
                                .setParameter("id", id)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }
}
