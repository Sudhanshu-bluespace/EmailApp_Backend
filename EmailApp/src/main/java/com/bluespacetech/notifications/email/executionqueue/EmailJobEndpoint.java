package com.bluespacetech.notifications.email.executionqueue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailJobEndpoint.
 */
public class EmailJobEndpoint implements Comparable<EmailJobEndpoint>
{

    /** The batch id. */
    private String batchId;

    /** The request id. */
    private String requestId;

    /** The request url. */
    private String requestUrl;

    /** The campaign name. */
    private String campaignName;

    /** The sender. */
    private String sender;
    
    /** The reports file path. */
    private Path reportsFilePath;

    /**
     * Gets the reports file path.
     *
     * @return the reports file path
     */
    public Path getReportsFilePath()
    {
        return reportsFilePath;
    }

    /**
     * Sets the reports file path.
     *
     * @param reportsFilePath the new reports file path
     */
    public void setReportsFilePath(Path reportsFilePath)
    {
        this.reportsFilePath = reportsFilePath;
    }

    /** The email contact group list. */
    private List<EmailContactGroupVO> emailContactGroupList;

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(EmailJobEndpoint endpoint)
    {
        // TODO Auto-generated method stub
        return this.getEmailContactGroupList().size() - endpoint.getEmailContactGroupList().size();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmailJobEndpoint other = (EmailJobEndpoint) obj;
        if (batchId == null)
        {
            if (other.batchId != null)
                return false;
        }
        else if (!batchId.equals(other.batchId))
            return false;
        if (campaignName == null)
        {
            if (other.campaignName != null)
                return false;
        }
        else if (!campaignName.equals(other.campaignName))
            return false;
        if (requestId == null)
        {
            if (other.requestId != null)
                return false;
        }
        else if (!requestId.equals(other.requestId))
            return false;
        return true;
    }

    /**
     * Gets the batch id.
     *
     * @return the batch id
     */
    public String getBatchId()
    {
        return batchId;
    }

    /**
     * Gets the campaign name.
     *
     * @return the campaign name
     */
    public String getCampaignName()
    {
        return campaignName;
    }

    /**
     * Gets the email contact group list.
     *
     * @return the email contact group list
     */
    public List<EmailContactGroupVO> getEmailContactGroupList()
    {
        if (emailContactGroupList == null)
        {
            emailContactGroupList = new ArrayList<>();
        }
        return emailContactGroupList;
    }

    /**
     * Gets the request id.
     *
     * @return the request id
     */
    public String getRequestId()
    {
        return requestId;
    }

    /**
     * Gets the request url.
     *
     * @return the request url
     */
    public String getRequestUrl()
    {
        return requestUrl;
    }

    /**
     * Gets the sender.
     *
     * @return the sender
     */
    public String getSender()
    {
        return sender;
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
        result = prime * result + ((batchId == null) ? 0 : batchId.hashCode());
        result = prime * result + ((campaignName == null) ? 0 : campaignName.hashCode());
        result = prime * result + ((requestId == null) ? 0 : requestId.hashCode());
        return result;
    }

    /**
     * Sets the batch id.
     *
     * @param batchId the new batch id
     */
    public void setBatchId(String batchId)
    {
        this.batchId = batchId;
    }

    /**
     * Sets the campaign name.
     *
     * @param campaignName the new campaign name
     */
    public void setCampaignName(String campaignName)
    {
        this.campaignName = campaignName;
    }

    /**
     * Sets the email contact group list.
     *
     * @param emailContactGroupList the new email contact group list
     */
    public void setEmailContactGroupList(List<EmailContactGroupVO> emailContactGroupList)
    {
        this.emailContactGroupList = emailContactGroupList;
    }

    /**
     * Sets the request id.
     *
     * @param requestId the new request id
     */
    public void setRequestId(String requestId)
    {
        this.requestId = requestId;
    }

    /**
     * Sets the request url.
     *
     * @param requestUrl the new request url
     */
    public void setRequestUrl(String requestUrl)
    {
        this.requestUrl = requestUrl;
    }

    /**
     * Sets the sender.
     *
     * @param sender the new sender
     */
    public void setSender(String sender)
    {
        this.sender = sender;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "EmailJobEndpoint [batchId=" + batchId + ", requestId=" + requestId + ", requestUrl=" + requestUrl
                + ", campaignName=" + campaignName + ", sender=" + sender + ", emailContactGroupListSize="
                + emailContactGroupList.size() + "]";
    }
}
