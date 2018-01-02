package com.bluespacetech.contactgroup.entity;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.group.entity.Group;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactGroup.
 */
@Entity
@Table(name = "CONTACT_GROUP")
public class ContactGroup implements Serializable
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8104121333570891530L;

    /** The contact group PK. */
    @EmbeddedId
    private ContactGroupPK contactGroupPK = new ContactGroupPK();

    /** The is active. */
    @Column(name = "ISACTIVE")
    private boolean isActive = true;

    /** The un subscribed. */
    @Column(name = "UNSUBSCRIBED")
    private boolean unSubscribed = false;

    /** The unsubscribed date. */
    @Column(name = "UNSUBSCRIBED_DATE", nullable = true)
    private Timestamp unsubscribedDate;

    /**
     * Gets the unsubscribed date.
     *
     * @return the unsubscribed date
     */
    public Timestamp getUnsubscribedDate()
    {
        return this.unsubscribedDate;
    }

    /**
     * Sets the unsubscribed date.
     *
     * @param unsubscribedDate the new unsubscribed date
     */
    public void setUnsubscribedDate(Timestamp unsubscribedDate)
    {
        this.unsubscribedDate = unsubscribedDate;
    }

    /**
     * Gets the contact group PK.
     *
     * @return the contact group PK
     */
    public ContactGroupPK getContactGroupPK()
    {
        return this.contactGroupPK;
    }

    /**
     * Sets the contact group PK.
     *
     * @param contactGroupPK the new contact group PK
     */
    public void setContactGroupPK(ContactGroupPK contactGroupPK)
    {
        this.contactGroupPK = contactGroupPK;
    }

    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    public boolean isActive()
    {
        return this.isActive;
    }

    /**
     * Sets the active.
     *
     * @param isActive the new active
     */
    public void setActive(boolean isActive)
    {
        this.isActive = isActive;
    }

    /**
     * Checks if is un subscribed.
     *
     * @return true, if is un subscribed
     */
    public boolean isUnSubscribed()
    {
        return this.unSubscribed;
    }

    /**
     * Sets the un subscribed.
     *
     * @param unSubscribed the new un subscribed
     */
    public void setUnSubscribed(boolean unSubscribed)
    {
        this.unSubscribed = unSubscribed;
    }

    /**
     * Gets the contact.
     *
     * @return the contact
     */
    @Transient
    public Contact getContact()
    {
        return getContactGroupPK().getContact();
    }

    /**
     * Sets the contact.
     *
     * @param contact the new contact
     */
    public void setContact(Contact contact)
    {
        getContactGroupPK().setContact(contact);
    }

    /**
     * Gets the group.
     *
     * @return the group
     */
    @Transient
    public Group getGroup()
    {
        return getContactGroupPK().getGroup();
    }

    /**
     * Sets the group.
     *
     * @param group the new group
     */
    public void setGroup(Group group)
    {
        getContactGroupPK().setGroup(group);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.contactGroupPK == null ? 0 : this.contactGroupPK.hashCode());
        result = 31 * result + (this.isActive ? 1231 : 1237);
        result = 31 * result + (this.unSubscribed ? 1231 : 1237);
        result = 31 * result + (this.unsubscribedDate == null ? 0 : this.unsubscribedDate.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        ContactGroup other = (ContactGroup) obj;
        if (this.contactGroupPK == null)
        {
            if (other.contactGroupPK != null)
            {
                return false;
            }
        }
        else if (!this.contactGroupPK.equals(other.contactGroupPK))
        {
            return false;
        }
        if (this.isActive != other.isActive)
        {
            return false;
        }
        if (this.unSubscribed != other.unSubscribed)
        {
            return false;
        }
        if (this.unsubscribedDate == null)
        {
            if (other.unsubscribedDate != null)
            {
                return false;
            }
        }
        else if (!this.unsubscribedDate.equals(other.unsubscribedDate))
        {
            return false;
        }
        return true;
    }

    /**
     * Pre persist.
     */
    @PrePersist
    public void prePersist()
    {
        this.unsubscribedDate = null;
    }
}
