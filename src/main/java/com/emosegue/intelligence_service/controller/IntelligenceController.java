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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emosegue
 */
@RestController
@RequestMapping(path = "/")
public class IntelligenceController {

    private final LocationService locationService;
    private final MessageService  messageService;
    private final SpaceRepository space;

    public IntelligenceController(LocationService locationService, MessageService messageService, SpaceRepository space) {
        this.locationService = locationService;
        this.messageService = messageService;
        this.space = space;
    }

    @PostMapping(path = "intelligenceservice/v1/topsecret")
    public SatellitesResponse receiveAndDecodeInformation(@RequestBody SatellitesData requestData){
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

    @GetMapping(path = "intelligenceservice/v1/topsecret_split")
    public SatellitesResponse decodeInformation(){
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

    @PostMapping(path = "intelligenceservice/v1/topsecret_split/{satellite_name}")
    @ResponseStatus(HttpStatus.OK)
    public void receiveInformation(@RequestBody SatelliteData requestData, @PathVariable String satellite_name) {
        Satellite targetSatellite = space.getSatelliteDataByName(satellite_name);
        if (targetSatellite != null){
            targetSatellite.setDistance(requestData.getDistance());
            targetSatellite.setMessageReceived(requestData.getMessage());
        }
        else{
            throw new NotInformationException("El satélite no existe");
        }
    }

    @GetMapping(path = "intelligenceservice/v1/test")
    public String test() throws LocationException, MessageException {

        String serviceWorkingtext = "El servicio de inteligencia funciona correctamente! ";
        String htmlCircle = "<span class='dot blink'>\n" +
                "<style>\n" +
                ".dot{\n" +
                "\tbackground-color: green;\n" +
                "\theight:25px;\n" +
                "\twidth:25px;\n" +
                "\tborder-radius:50%;\n" +
                "\tdisplay:inline-block;\n" +
                "\t}\n" +
                ".blink {\n" +
                "  animation: blink 1s steps(1, end) infinite;\n" +
                "}\n" +
                "\n" +
                "@keyframes blink {\n" +
                "  0% {\n" +
                "    opacity: 1;}" +
                "  50% {\n" +
                "    opacity: 0;}" +
                "  100% {\n" +
                "    opacity: 1;}" +
                "}\n" +
                "</style>\n" +
                "</span>";

        return "<html><div><h1>" + serviceWorkingtext + htmlCircle +"</h1></div></html>";
    }

}
