package com.emosegue.intelligence_service.controller.dto;

import com.emosegue.intelligence_service.model.Position;


public class SatellitesResponse {

    private Position position;
    private String message;

    public SatellitesResponse(Position position, String message) {
        this.position = position;
        this.message = message;
    }

    public Position getPosition() {
        return position;
    }

    public String getMessage() {
        return message;
    }
}
