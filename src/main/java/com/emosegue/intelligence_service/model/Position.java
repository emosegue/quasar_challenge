package com.emosegue.intelligence_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author emosegue
 */
public class Position {
    
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @JsonIgnore
    public Double[] getPosition(){
        Double[] positions = {this.x,this.y};
        return positions;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }
  
}
