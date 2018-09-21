package com.tvestergaard.rest.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e"),
        @NamedQuery(name = "Event.findById", query = "SELECT e FROM Event e WHERE e.id = :id"),
        @NamedQuery(name = "Event.findByPet", query = "SELECT e FROM Event e WHERE e.pet.id = :pet")
})
@Entity public class Event
{
    private int    id;
    private String event;
    private String remark;
    private Date   date;
    private Pet    pet;

    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;

    }

    @Basic @Column(name = "event") public String getEvent()
    {
        return this.event;
    }

    public void setEvent(String event)
    {
        this.event = event;

    }

    @Basic @Column(name = "remark") public String getRemark()
    {
        return this.remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;

    }

    @Basic @Column(name = "date") public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date date)
    {
        this.date = date;

    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event1 = (Event) o;
        return id == event1.id &&
               Objects.equals(event, event1.event) &&
               Objects.equals(remark, event1.remark) &&
               Objects.equals(date, event1.date);
    }

    @Override public int hashCode()
    {
        return Objects.hash(id, event, remark, date);
    }

    @ManyToOne @JoinColumn(name = "pet_id", referencedColumnName = "id", nullable = false)
    public Pet getPet()
    {
        return this.pet;
    }

    public void setPet(Pet pet)
    {
        this.pet = pet;

    }
}
