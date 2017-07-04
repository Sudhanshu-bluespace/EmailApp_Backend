
/**
 * This document is a part of the source code and related artifacts for Emilent.
 * www.bluespacetech.com Copyright � 2016 bluespacetech
 *
 */
package com.bluespacetech.core.model;

/**
 * @author pradeep created date 25-June-2015
 *
 */
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import com.bluespacetech.core.exceptions.InvalidArgumentException;
import com.bluespacetech.core.utility.ViewUtil;

/**
 * The Class BaseEntity.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable
{

    /**
     * Universal serial version id for this class.
     */
    private static final long serialVersionUID = -246686943403665670L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    /** The version. */
    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    /** The last updated date. */
    @Column(name = "LAST_UPDATE_DATE", nullable = false)
    private Timestamp lastUpdatedDate;

    /** The last updated user. */
    @Column(name = "LAST_UPDATED_USER", nullable = false)
    private String lastUpdatedUser;

    /** The creation date. */
    @Column(name = "CREATION_DATE", nullable = false)
    private Timestamp creationDate;

    /** The created user. */
    @Column(name = "CREATED_USER", nullable = false)
    private String createdUser;

    /** The resource object id. */
    transient private Long resourceObjectId;
    
    transient private boolean autoRunEnabled;
    
    transient private final String AUTO_ADMIN="auto_admin";
    

    public boolean isAutoRunEnabled()
    {
        return autoRunEnabled;
    }

    public void setAutoRunEnabled(boolean autoRunEnabled)
    {
        this.autoRunEnabled = autoRunEnabled;
    }

    /**
     * Creates a clone of the given date. Returns null when the given date is null.
     *
     * @param date Date The date for which to create a clone.
     * @return Date The clone of the given date. Null when the given date is null.
     */
    public Date cloneDate(final Date date)
    {
        return date != null ? (Date) date.clone() : null;
    }

    /**
     * Creates a clone of the given time stamp. Returns null when the given time stamp is null.
     *
     * @param timestamp Time stamp the time stamp for which to create a clone.
     * @return Time stamp The clone of the given time stamp. Null when the given time stamp is null.
     */
    public Timestamp cloneTimestamp(final Timestamp timestamp)
    {
        return timestamp != null ? (Timestamp) timestamp.clone() : null;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final BaseEntity other = (BaseEntity) obj;
        if (id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        }
        else if (!id.equals(other.id))
        {
            return false;
        }
        return true;
    }

    /**
     * Gets the created user.
     *
     * @return the createdUser
     */
    public String getCreatedUser()
    {
        return createdUser;
    }

    /**
     * Gets the creation date.
     *
     * @return the creationDate
     */
    public Timestamp getCreationDate()
    {
        return creationDate;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Gets the last updated date.
     *
     * @return the lastUpdatedDate
     */
    public Timestamp getLastUpdatedDate()
    {
        return lastUpdatedDate;
    }

    /**
     * Gets the last updated user.
     *
     * @return the lastUpdatedUser
     */
    public String getLastUpdatedUser()
    {
        return lastUpdatedUser;
    }

    /**
     * Gets the resource object id.
     *
     * @return the resourceObjectId
     */
    public Long getResourceObjectId()
    {
        return resourceObjectId;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public Long getVersion()
    {
        return version;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + (id == null ? 0 : id.hashCode());
        return result;
    }

    /**
     * Pre persist.
     */
    @PrePersist
    public void prePersist()
    {

        // Created time stamp
        final java.util.Date date = new java.util.Date();
        if (creationDate == null)
        {
            creationDate = new Timestamp(date.getTime());
        }

        if (createdUser == null)
        {
            // Created user name;
            if(!isAutoRunEnabled())
            {
                createdUser = ViewUtil.getPrincipal();
            }
            else
            {
                createdUser = AUTO_ADMIN;
            }
        }

        // last updated time stamp
        lastUpdatedDate = new Timestamp(date.getTime());

        if (lastUpdatedUser == null)
        {
            // last updated user name;
            if(!autoRunEnabled)
            {
                lastUpdatedUser = ViewUtil.getPrincipal();
            }
            else
            {
                lastUpdatedUser=AUTO_ADMIN;
            }
        }
    }

    /**
     * Sets the created user.
     *
     * @param createdUser the createdUser to set
     */
    public void setCreatedUser(final String createdUser)
    {
        this.createdUser = createdUser;
    }

    /**
     * Sets the creation date.
     *
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(final Timestamp creationDate)
    {
        this.creationDate = creationDate;
    }

    /**
     * Sets the last updated date.
     *
     * @param lastUpdatedDate the lastUpdatedDate to set
     */
    public void setLastUpdatedDate(final Timestamp lastUpdatedDate)
    {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * Sets the last updated user.
     *
     * @param lastUpdatedUser the lastUpdatedUser to set
     */
    public void setLastUpdatedUser(final String lastUpdatedUser)
    {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    /**
     * Sets the resource object id.
     *
     * @param resourceObjectId the resourceObjectId to set
     */
    public void setResourceObjectId(final Long resourceObjectId)
    {
        this.resourceObjectId = resourceObjectId;
    }

    /**
     * Validate.
     *
     * @throws InvalidArgumentException the invalid argument exception
     */
    public void validate() throws InvalidArgumentException
    {

    }

}
