package org.lanit.modelsJson;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TickersItem{

	@JsonProperty("ticker")
	private String ticker;
	@JsonProperty("alerts")
	private List<AlertsItem> alerts;


	public void setAlerts(List<AlertsItem> alerts){
		this.alerts = alerts;
	}

	public List<AlertsItem> getAlerts(){
		return alerts;
	}

	public void setTicker(String ticker){
		this.ticker = ticker;
	}

	public String getTicker(){
		return ticker;
	}

	public TickersItem(List<AlertsItem> alerts, String ticker) {
		this.alerts = alerts;
		this.ticker = ticker;
	}

	public TickersItem() {
	}

	@Override
	public String toString() {
		return "TickersItem{" +
				"ticker='" + ticker + '\'' +
				", alerts=" + alerts +
				'}';
	}
}