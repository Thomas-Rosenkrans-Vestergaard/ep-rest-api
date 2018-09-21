package com.tvestergaard.rest.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Pet.findAll", query = "SELECT p FROM Pet p"),
        @NamedQuery(name = "Pet.count", query = "SELECT count(p) FROM Pet p"),
        @NamedQuery(name = "Pet.findById", query = "SELECT p FROM Pet p WHERE p.id = :id"),
        @NamedQuery(name = "Pet.findLiving", query = "SELECT p FROM Pet p WHERE p.death = null"),
        @NamedQuery(name = "Pet.findDead", query = "SELECT p FROM Pet p WHERE p.death != null")
})
@Entity public class Pet
{
    private int               id;
    private String            name;
    private Date              birth;
    private String            species;
    private Date              death;
    private Collection<Event> events;
    private Owner             owner;

    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;

    }

    @Basic @Column(name = "name") public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;

    }

    @Basic @Column(name = "birth") public Date getBirth()
    {
        return this.birth;
    }

    public void setBirth(Date birth)
    {
        this.birth = birth;

    }

    @Basic @Column(name = "species") public String getSpecies()
    {
        return this.species;
    }

    public void setSpecies(String species)
    {
        this.species = species;

    }

    @Basic @Column(name = "death") public Date getDeath()
    {
        return this.death;
    }

    public void setDeath(Date death)
    {
        this.death = death;

    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id &&
               Objects.equals(name, pet.name) &&
               Objects.equals(birth, pet.birth) &&
               Objects.equals(species, pet.species) &&
               Objects.equals(death, pet.death);
    }

    @Override public int hashCode()
    {
        return Objects.hash(id, name, birth, species, death);
    }

    @OneToMany(mappedBy = "pet", fetch = FetchType.EAGER) public Collection<Event> getEvents()
    {
        return this.events;
    }

    public void setEvents(Collection<Event> events)
    {
        this.events = events;

    }

    @ManyToOne @JoinColumn(name = "owner_id", referencedColumnName = "id") public Owner getOwner()
    {
        return this.owner;
    }

    public void setOwner(Owner owner)
    {
        this.owner = owner;

    }
}
