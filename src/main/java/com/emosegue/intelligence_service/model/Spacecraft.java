package com.emosegue.intelligence_service.model;

/**
 *
 * @author emosegue
 */
public class Spacecraft {
    
    private Position position;

    public Spacecraft(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Spacecraft position(Position position) {
        setPosition(position);
        return this;
    }

}

    
