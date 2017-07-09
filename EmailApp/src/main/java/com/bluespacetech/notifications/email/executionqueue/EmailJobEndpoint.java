package com.bluespacetech.notifications.email.executionqueue;

import java.util.ArrayList;
import java.util.List;

import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;

/**
 * The Class EmailJobEndpoint.
 */
public class EmailJobEndpoint implements Comparable<EmailJobEndpoint>
{

    /** The batch id. */
    private String batchId;

    /** The request id. */
    private String requestId;
    
    private String requestUrl;
    
    private String campaignName;
    
    private String sender;
    

    public String getCampaignName()
    {
        return campaignName;
    }

    public void setCampaignName(String campaignName)
    {
        this.campaignName = campaignName;
    }

    public String getSender()
    {
        return sender;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
    }

    public String getRequestUrl()
    {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl)
    {
        this.requestUrl = requestUrl;
    }

    /** The email contact group list. */
    private List<EmailContactGroupVO> emailContactGroupList;

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
     * Sets the batch id.
     *
     * @param batchId the new batch id
     */
    public void setBatchId(String batchId)
    {
        this.batchId = batchId;
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
     * Sets the request id.
     *
     * @param requestId the new request id
     */
    public void setRequestId(String requestId)
    {
        this.requestId = requestId;
    }

    /**
     * Gets the email contact group list.
     *
     * @return the email contact group list
     */
    public List<EmailContactGroupVO> getEmailContactGroupList()
    {
        if(emailContactGroupList == null)
        {
            emailContactGroupList = new ArrayList<>();
        }
        return emailContactGroupList;
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

    @Override
    public int compareTo(EmailJobEndpoint endpoint)
    {
        // TODO Auto-generated method stub
        return this.getEmailContactGroupList().size() - endpoint.getEmailContactGroupList().size();
    }

    @Override
    public String toString()
    {
        return "EmailJobEndpoint [batchId=" + batchId + ", requestId=" + requestId + ", requestUrl=" + requestUrl
                + ", campaignName=" + campaignName + ", sender=" + sender + ", emailContactGroupListSize="
                + emailContactGroupList.size() + "]";
    }

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
}
