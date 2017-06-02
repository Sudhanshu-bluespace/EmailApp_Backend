package com.bluespacetech.server.analytics.repository;


public class RepositoryResponseChartDTO {
	
	private int reach;
	private int clicks;
	private int unsubscribes;
	
	public int getReach() {
		return reach;
	}
	public void setReach(int reach) {
		this.reach = reach;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	@Override
	public String toString() {
		return "RepositoryResponseChartDTO [reach=" + reach + ", clicks=" + clicks + ", unsubscribes=" + unsubscribes
				+ "]";
	}
	public int getUnsubscribes() {
		return unsubscribes;
	}
	public void setUnsubscribes(int unsubscribes) {
		this.unsubscribes = unsubscribes;
	}

}

