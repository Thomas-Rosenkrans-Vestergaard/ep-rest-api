package com.tvestergaard.rest.data;

import com.tvestergaard.rest.entities.Pet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class JpaPetRepository implements PetRepository
{

    private final EntityManagerFactory entityManagerFactory;

    public JpaPetRepository(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Returns all the pets in the system.
     *
     * @return A list containing all the pets in the system.
     */
    @Override public List<Pet> get()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createNamedQuery("Pet.findAll", Pet.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Returns the pet with the provided id.
     *
     * @param id The id of the pet to retrieve.
     * @return The pet with the provided id, {@code null} when no such entity exists.
     */
    @Override public Pet get(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createNamedQuery("Pet.findById", Pet.class)
                                .setParameter("id", id)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Returns the number of pets in the system.
     *
     * @return The number of pets in the system.
     */
    @Override public long count()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return (long) entityManager.createNamedQuery("Pet.count").getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Returns all the living pets in the system.
     *
     * @return The living pets in the system.
     */
    @Override public List<Pet> getLiving()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createNamedQuery("Pet.findLiving", Pet.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Returns all the dead pets in the system
     *
     * @return The dead pets in the system.
     */
    @Override public List<Pet> getDead()
    {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createNamedQuery("Pet.findDead", Pet.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Returns all the pets with an event on the provided date.
     *
     * @param date
     * @return The pets with an event on the provided date.
     */
    @Override public List<Pet> getByEventDate(Date date)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createQuery("SELECT e.pet FROM Event e WHERE e.date = :date", Pet.class)
                                .setParameter("date", date)
                                .getResultList();
        } finally {
            entityManager.close();
        }
    }
}
