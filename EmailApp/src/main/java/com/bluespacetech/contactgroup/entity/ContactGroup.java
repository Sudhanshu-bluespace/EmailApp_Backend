package com.bluespacetech.contactgroup.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.group.entity.Group;

@Entity
@Table(name = "CONTACT_GROUP")
public class ContactGroup implements Serializable
{

    private static final long serialVersionUID = 8104121333570891530L;

    @EmbeddedId
    private ContactGroupPK contactGroupPK = new ContactGroupPK();

    @Column(name = "ISACTIVE")
    private boolean isActive = true;

    @Column(name = "UNSUBSCRIBED")
    private boolean unSubscribed = false;

    @Column(name = "UNSUBSCRIBED_DATE",nullable=true)
    private Timestamp unsubscribedDate;

    public Timestamp getUnsubscribedDate()
    {
        return unsubscribedDate;
    }

    public void setUnsubscribedDate(Timestamp unsubscribedDate)
    {
        this.unsubscribedDate = unsubscribedDate;
    }

    public ContactGroupPK getContactGroupPK()
    {
        return contactGroupPK;
    }

    public void setContactGroupPK(ContactGroupPK contactGroupPK)
    {
        this.contactGroupPK = contactGroupPK;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean isActive)
    {
        this.isActive = isActive;
    }

    public boolean isUnSubscribed()
    {
        return unSubscribed;
    }

    public void setUnSubscribed(boolean unSubscribed)
    {
        this.unSubscribed = unSubscribed;
    }

    @Transient
    public Contact getContact()
    {
        return this.getContactGroupPK().getContact();
    }

    public void setContact(Contact contact)
    {
        this.getContactGroupPK().setContact(contact);
    }

    @Transient
    public Group getGroup()
    {
        return this.getContactGroupPK().getGroup();
    }

    public void setGroup(Group group)
    {
        this.getContactGroupPK().setGroup(group);
    }

    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contactGroupPK == null) ? 0 : contactGroupPK.hashCode());
        result = prime * result + (isActive ? 1231 : 1237);
        result = prime * result + (unSubscribed ? 1231 : 1237);
        result = prime * result + ((unsubscribedDate == null) ? 0 : unsubscribedDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContactGroup other = (ContactGroup) obj;
        if (contactGroupPK == null)
        {
            if (other.contactGroupPK != null)
                return false;
        }
        else if (!contactGroupPK.equals(other.contactGroupPK))
            return false;
        if (isActive != other.isActive)
            return false;
        if (unSubscribed != other.unSubscribed)
            return false;
        if (unsubscribedDate == null)
        {
            if (other.unsubscribedDate != null)
                return false;
        }
        else if (!unsubscribedDate.equals(other.unsubscribedDate))
            return false;
        return true;
    }

    /**
     * Pre persist.
     */
    @PrePersist
    public void prePersist()
    {
        // Created time stamp
        //final java.util.Date date = new java.util.Date();
            // last updated time stamp
        unsubscribedDate = null;
    }

}
