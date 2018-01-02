package com.bluespacetech.group.entity;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.core.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The Class Group.
 */
@Entity
@Table(name = "GROUPS")
public class Group extends BaseEntity implements Serializable
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -9115675653111826751L;

    /** The name. */
    @NotEmpty(message = "Name is mandatory.")
    @Column(name = "NAME")
    private String name;

    /** The comments. */
    @Column(name = "COMMENTS")
    private String comments;

    /** The contact groups. */
    @JsonIgnore
    @OneToMany(mappedBy = "contactGroupPK.group", cascade = { javax.persistence.CascadeType.ALL }, orphanRemoval = true)
    private List<ContactGroup> contactGroups = new ArrayList<>();

    /** The contact count. */
    @Transient
    private Integer contactCount;

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.core.model.BaseEntity#hashCode()
     */
    public int hashCode()
    {
        int prime = 31;
        int result = super.hashCode();
        result = 31 * result + (this.contactCount == null ? 0 : this.contactCount.hashCode());
        result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.bluespacetech.core.model.BaseEntity#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!super.equals(obj))
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        Group other = (Group) obj;
        if (this.contactCount == null)
        {
            if (other.contactCount != null)
            {
                return false;
            }
        }
        else if (!this.contactCount.equals(other.contactCount))
        {
            return false;
        }
        if (this.name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        }
        else if (!this.name.equals(other.name))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the contact count.
     *
     * @return the contact count
     */
    public Integer getContactCount()
    {
        return this.contactCount;
    }

    /**
     * Sets the contact count.
     *
     * @param contactCount the new contact count
     */
    public void setContactCount(Integer contactCount)
    {
        this.contactCount = contactCount;
    }

    /**
     * Gets the comments.
     *
     * @return the comments
     */
    public String getComments()
    {
        return this.comments;
    }

    /**
     * Sets the comments.
     *
     * @param comments the new comments
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }

    /**
     * Gets the contact groups.
     *
     * @return the contact groups
     */
    public List<ContactGroup> getContactGroups()
    {
        return this.contactGroups;
    }

    /**
     * Sets the contact groups.
     *
     * @param contactGroups the new contact groups
     */
    public void setContactGroups(List<ContactGroup> contactGroups)
    {
        this.contactGroups = contactGroups;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return "Group [name=" + this.name + ", Comments=" + this.comments + "]";
    }
}
