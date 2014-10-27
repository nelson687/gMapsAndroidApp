package com.jammala.mapapp;

import java.io.Serializable;

public class DirectionsResult implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
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
