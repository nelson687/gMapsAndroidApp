package com.jammala.mapapp;

public class DirectionsResult {

    String origin;
    String destination;
    String distanceText;
    long distanceValue;
    String duration;

    public DirectionsResult(){
        
    }
    
    public DirectionsResult(String origin, String destination, String distanceText, long distanceValue, String duration){
        this.origin = origin;
        this.destination = destination;
        this.distanceText = distanceText;
        this.distanceValue = distanceValue;
        this.duration = duration;
    }
}
