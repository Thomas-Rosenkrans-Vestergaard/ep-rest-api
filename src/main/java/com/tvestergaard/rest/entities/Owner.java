package com.tvestergaard.rest.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Owner.findAll", query = "SELECT o FROM Owner o"),
        @NamedQuery(name = "Owner.findById", query = "SELECT o FROM Owner o WHERE o.id = :id")
})
@Entity public class Owner
{
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private Collection<Pet> pets;

    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;

    }

    @Basic @Column(name = "first_name") public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;

    }

    @Basic @Column(name = "last_name") public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;

    }

    @Basic @Column(name = "address") public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String address)
    {
        this.address = address;

    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id == owner.id &&
               Objects.equals(firstName, owner.firstName) &&
               Objects.equals(lastName, owner.lastName) &&
               Objects.equals(address, owner.address);
    }

    @Override public int hashCode()
    {
        return Objects.hash(id, firstName, lastName, address);
    }

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER) public Collection<Pet> getPets()
    {
        return this.pets;
    }

    public void setPets(Collection<Pet> pets)
    {
        this.pets = pets;

    }
}
