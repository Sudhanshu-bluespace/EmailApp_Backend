package com.bluespacetech.server.analytics.repository;

public class RecentUnsubscribesDTO
{
    private Long serialNo;
    private String firstName;
    private String lastName;
    private String email;
    private String unsubscribedOn;
    
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public Long getSerialNo()
    {
        return serialNo;
    }
    public void setSerialNo(Long serialNo)
    {
        this.serialNo = serialNo;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getUnsubscribedOn()
    {
        return unsubscribedOn;
    }
    public void setUnsubscribedOn(String unsubscribedOn)
    {
        this.unsubscribedOn = unsubscribedOn;
    }
    @Override
    public String toString()
    {
        return "RecentUnsubscribesDTO [serialNo=" + serialNo + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", unsubscribedOn=" + unsubscribedOn + "]";
    }

    
}
