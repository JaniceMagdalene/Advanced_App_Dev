package com.party.eventmanagement.dto.request;
public class EventUpdateRequest {
    
    private String eventName;
    private String descrption;
    private double cost;
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getDescrption() {
        return descrption;
    }
    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
   
}