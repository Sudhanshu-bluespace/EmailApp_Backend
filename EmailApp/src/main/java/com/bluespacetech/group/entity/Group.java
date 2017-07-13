package com.bluespacetech.group.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.core.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pradeep created date 13-Jul-2016
 */
@Entity
@Table(name = "GROUPS")
public class Group extends BaseEntity implements Serializable
{

    private static final long serialVersionUID = -9115675653111826751L;

    @NotEmpty(message = "Name is mandatory.")
    @Column(name = "NAME")
    private String name;

    @Column(name = "COMMENTS")
    private String comments;

    @JsonIgnore
    @OneToMany(mappedBy = "contactGroupPK.group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ContactGroup> contactGroups = new ArrayList<>();

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((contactCount == null) ? 0 : contactCount.hashCode());
        result = prime * result + ((contactGroups == null) ? 0 : contactGroups.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        if (contactCount == null)
        {
            if (other.contactCount != null)
                return false;
        }
        else if (!contactCount.equals(other.contactCount))
            return false;
        if (contactGroups == null)
        {
            if (other.contactGroups != null)
                return false;
        }
        else if (!contactGroups.equals(other.contactGroups))
            return false;
        if (name == null)
        {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Transient
    private Integer contactCount;

    public Integer getContactCount()
    {
        return contactCount;
    }

    public void setContactCount(Integer contactCount)
    {
        this.contactCount = contactCount;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public Collection<ContactGroup> getContactGroups()
    {
        return contactGroups;
    }

    public void setContactGroups(Collection<ContactGroup> contactGroups)
    {
        this.contactGroups = contactGroups;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "Group [name=" + name + ", Comments=" + comments + "]";
    }

}
