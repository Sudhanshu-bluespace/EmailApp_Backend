package com.bluespacetech.core.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="CITIES")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City extends BaseEntity implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 109106418830060348L;
    
    @Column(name="NAME")
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "STATE_ID", nullable = false)
    private State state;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public State getState()
    {
        return state;
    }

    public void setState(State state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return "City [name=" + name + ", state=" + state + "]";
    }
    
    

}
