package org.lanit.modelsJson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Delete{

	@JsonProperty("tickerName")
	private String tickerName;

	@JsonProperty("alertIndex")
	private int alertIndex;

	public void setTickerName(String tickerName){
		this.tickerName = tickerName;
	}

	public String getTickerName(){
		return tickerName;
	}

	public void setAlertIndex(int alertIndex){
		this.alertIndex = alertIndex;
	}

	public int getAlertIndex(){
		return alertIndex;
	}

	public Delete() {
	}

	public Delete(String tickerName, int alertIndex) {
		this.tickerName = tickerName;
		this.alertIndex = alertIndex;
	}

	@Override
	public String toString() {
		return "Delete{" +
				"tickerName='" + tickerName + '\'' +
				", alertIndex=" + alertIndex +
				'}';
	}
}