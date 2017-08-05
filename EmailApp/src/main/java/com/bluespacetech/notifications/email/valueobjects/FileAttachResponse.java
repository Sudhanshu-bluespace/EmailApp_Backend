package com.bluespacetech.notifications.email.valueobjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// TODO: Auto-generated Javadoc
/**
 * The Class FileAttachResponse.
 */
@JsonInclude(Include.NON_NULL)
public class FileAttachResponse
{

    /** The link. */
    private String link;
    
    /** The error. */
    private String error;

    /**
     * Gets the error.
     *
     * @return the error
     */
    public String getError()
    {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param error the new error
     */
    public void setError(String error)
    {
        this.error = error;
    }

    /**
     * Sets the link.
     *
     * @param link the new link
     */
    public void setLink(String link)
    {
        this.link = link;
    }

    /**
     * Gets the link.
     *
     * @return the link
     */
    public String getLink()
    {
        return link;
    }
}
