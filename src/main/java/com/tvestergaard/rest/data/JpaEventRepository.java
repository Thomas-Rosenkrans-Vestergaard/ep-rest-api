package com.tvestergaard.rest.data;

import com.tvestergaard.rest.entities.Event;
import com.tvestergaard.rest.entities.Pet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

public class JpaEventRepository implements EventRepository
{


    private final EntityManagerFactory entityManagerFactory;

    public JpaEventRepository(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Returns all the events in the system.
     *
     * @return The events in the system.
     */
    @Override public List<Event> get()
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createNamedQuery("Event.findAll", Event.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Returns the event with the provided id.
     *
     * @param id The id of the event to return.
     * @return The event with the provided id, {@code null} when no such entity exists.
     */
    @Override public Event get(int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createNamedQuery("Event.findById", Event.class)
                                .setParameter("id", id)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Returns the events associated with the provided pet id.
     *
     * @param pet The pet id to return the events of.
     * @return The events associated with the provided pet id, {@code null} when the provided pet id does not exist.
     */
    @Override public List<Event> getFromPet(int pet)
    {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.createNamedQuery("Event.findByPet", Event.class)
                                .setParameter("pet", pet)
                                .getResultList();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Creates and returns a new event.
     *
     * @param pet      The pet the event belongs to.
     * @param eventMsg The event string.
     * @param remark   The remark string.
     * @param date     The date the event transpired on.
     * @return The event entity.
     */
    @Override public Event create(Pet pet, String eventMsg, String remark, Date date)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Event event = new Event();
        event.setEvent(eventMsg);
        event.setRemark(remark);
        event.setDate(date);
        event.setPet(pet);

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(event);
            entityManager.getTransaction().rollback();
            return event;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
