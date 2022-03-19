/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emosegue.intelligence_service.controller;

import com.emosegue.intelligence_service.business.LocationService;
import com.emosegue.intelligence_service.business.MessageService;
import com.emosegue.intelligence_service.controller.dto.SatelliteData;
import com.emosegue.intelligence_service.controller.dto.SatellitesData;
import com.emosegue.intelligence_service.controller.dto.SatellitesResponse;
import com.emosegue.intelligence_service.exception.LocationException;
import com.emosegue.intelligence_service.exception.MessageException;
import com.emosegue.intelligence_service.exception.NotInformationException;
import com.emosegue.intelligence_service.model.Position;
import com.emosegue.intelligence_service.model.Satellite;
import com.emosegue.intelligence_service.model.repository.SpaceRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author emosegue
 */
@RestController
@RequestMapping(path = "/")
public class IntelligenceController {

    private LocationService locationService;
    private MessageService  messageService;
    private SpaceRepository space;

    public IntelligenceController(LocationService locationService, MessageService messageService, SpaceRepository space) {
        this.locationService = locationService;
        this.messageService = messageService;
        this.space = space;
    }

    @PostMapping(path = "/topsecret")
    public SatellitesResponse receiveAndDecodeData(@RequestBody SatellitesData requestData){
        List<Satellite> quasarSpace = space.getSatellites();
        double[][] positionList = new double[quasarSpace.size()][2];
        double[] distances = new double[quasarSpace.size()];
        List<List<String>> messageList = new ArrayList<>();
        int i = 0;
        for (Satellite satellite: quasarSpace) {
            positionList[i++] = new double[]{satellite.getPosition().getX(), satellite.getPosition().getY()};
        }
        i = 0;
        for (SatelliteData data: requestData.getSatellites()){
            distances[i++] = data.getDistance();
            messageList.add(data.getMessage());
        }
        Position calculatedPosition = locationService.getLocation(positionList, distances);
        String decodedMessage = messageService.getMessage(messageList);
        return new SatellitesResponse(calculatedPosition, decodedMessage);
    }

    @GetMapping(path = "/topsecret_split")
    public SatellitesResponse DecodeData(){
        if(checkDecodeConditions()){
            List<Satellite> quasarSpace = space.getSatellites();
            double[][] positionList = new double[quasarSpace.size()][2];
            double[] distances = new double[quasarSpace.size()];
            List<List<String>> messageList = new ArrayList<>();
            int i = 0;
            for(Satellite satellite : quasarSpace){
                messageList.add(satellite.getMessageReceived());
                positionList[i] = new double[]{satellite.getPosition().getX(), satellite.getPosition().getY()};
                distances[i] = satellite.getDistance();
                i++;
            }
            Position calculatedPosition = locationService.getLocation(positionList, distances);
            String decodedMessage = messageService.getMessage(messageList);
            return new SatellitesResponse(calculatedPosition, decodedMessage);
        }
        else{
            throw new NotInformationException("No hay Información suficiente para calcular la posición o el mensaje");
        }
    }

    private boolean checkDecodeConditions(){
        List<Satellite> quasarSpace = space.getSatellites();
        for (Satellite satellite: quasarSpace){
            if (satellite.getDistance() == 0.0 || satellite.getMessageReceived() == null)
                return false;
        }
        return true;
    }

    @PostMapping(path = "/topsecret_split/{satellite_name}")
    @ResponseStatus(HttpStatus.OK)
    public void ReceiveData(@RequestBody SatelliteData requestData, @PathVariable String satellite_name) {
        Satellite targetSatellite = space.getSatelliteDataByName(satellite_name);
        if (targetSatellite != null){
            targetSatellite.setDistance(requestData.getDistance());
            targetSatellite.setMessageReceived(requestData.getMessage());
        }
        else{
            throw new NotInformationException("El satélite no existe");
        }
    }

    @GetMapping(path = "/test")
    public SatellitesResponse test() throws LocationException, MessageException {

        checkDecodeConditions();
        Position calculatedPosition = locationService.getLocation(new double[][]{{1.0, 1.0}, {3.0, 1.0}, {2.0, 2.0}}, new double[]{1.0, 1.0, 1.0});
        List<List<String>> messageList = new ArrayList<>();
        List<String> message1 = new ArrayList<>();
        message1.add("");
        message1.add("este");
        message1.add("es");
        message1.add("un");
        message1.add("mensaje");
        List<String> message2 = new ArrayList<>();
        message2.add("este");
        message2.add("");
        message2.add("un");
        message2.add("mensaje");
        List<String> message3 = new ArrayList<>();
        message3.add("");
        message3.add("");
        message3.add("este");
        message3.add("");
        message3.add("");
        message3.add("mensaje");

        messageList.add(message1);
        messageList.add(message2);
        messageList.add(message3);
        String decodedMessage = messageService.getMessage(messageList);
        return new SatellitesResponse(calculatedPosition, decodedMessage);
    }

}
