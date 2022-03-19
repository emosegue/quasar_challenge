package com.emosegue.intelligence_service.model.repository;

import com.emosegue.intelligence_service.model.Satellite;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class SpaceRepository {

    List<Satellite> satellites;

    public SpaceRepository() {
        this.satellites = new ArrayList<>();
        Satellite satellite1 = new Satellite(-500,-200, "Kenobi");
        Satellite satellite2 = new Satellite(100,-100, "Skywalker");
        Satellite satellite3 = new Satellite(500,100, "Sato");
        this.satellites.add(satellite1);
        this.satellites.add(satellite2);
        this.satellites.add(satellite3);
    }

    public List<Satellite> getSatellites(){
        return this.satellites;
    }

    public Satellite getSatelliteDataByName(String name){
        for(Satellite satellite : satellites){
            if (satellite.getName().equals(name)) {
                return satellite;
            }
        }
        return null;
    }
}
