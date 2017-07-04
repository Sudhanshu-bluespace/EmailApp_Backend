package com.bluespacetech.server.analytics.repository;

public class RecentlyUnsubscribedCountDTO
{
    private String unsubscribedOn;
    private int count;
    public String getUnsubscribedOn()
    {
        return unsubscribedOn;
    }
    public void setUnsubscribedOn(String unsubscribedOn)
    {
        this.unsubscribedOn = unsubscribedOn;
    }
    public int getCount()
    {
        return count;
    }
    public void setCount(int count)
    {
        this.count = count;
    }
    @Override
    public String toString()
    {
        return "RcentlyUnsubscribedCountDTO [unsubscribedOn=" + unsubscribedOn + ", count=" + count + "]";
    }
    
    

}
