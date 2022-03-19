
package com.emosegue.intelligence_service.model;

import java.util.List;

/**
 *
 * @author emosegue
 */
public class Satellite extends Spacecraft{

    private String name;
    private double distance;
    private List<String> messageReceived;

    public Satellite(double x, double y, String name){
        super(new Position(x,y));
        this.name = name;
    }

    public List<String> getMessageReceived() {
        return messageReceived;
    }

    public void setMessageReceived(List<String> messageReceived) {
        this.messageReceived = messageReceived;
    }

    public double getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
