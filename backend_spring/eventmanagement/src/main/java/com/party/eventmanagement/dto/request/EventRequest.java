package com.party.eventmanagement.dto.request;

public class EventRequest {

    private String eventName;
    private String descrption;
    private double cost;
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public String getEventName() {
        return eventName;
    }
    public String getDescrption() {
        return descrption;
    }
    public double getCost() {
        return cost;
    }
       
}