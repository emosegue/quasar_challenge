package com.emosegue.intelligence_service.controller;

import com.emosegue.intelligence_service.business.LocationService;
import com.emosegue.intelligence_service.business.MessageService;
import com.emosegue.intelligence_service.controller.dto.SatelliteData;
import com.emosegue.intelligence_service.controller.dto.SatellitesData;
import com.emosegue.intelligence_service.controller.dto.SatellitesResponse;
import com.emosegue.intelligence_service.exception.LocationException;
import com.emosegue.intelligence_service.exception.MessageException;
import com.emosegue.intelligence_service.model.Position;
import com.emosegue.intelligence_service.model.Satellite;
import com.emosegue.intelligence_service.model.repository.SpaceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IntelligenceControllerTest {

    @MockBean
    private LocationService locationService;
    @MockBean
    private MessageService messageService;
    @MockBean
    private SpaceRepository space;
    @Autowired
    private IntelligenceController intelligenceController;

    /**
     * Caso de test para verificar el comportamiento del endpoint topsecret donde, caso Ok
     *
     */
    @Test
    void receiveAndDecodeData_ResponseOk() {
        //Definicion de los colaboradores
        Satellite satellite1 = new Satellite(-500, -200, "Kenobi");
        Satellite satellite2 = new Satellite(100, -100, "Skywalker");
        Satellite satellite3 = new Satellite(500, 100, "Sato");

        //Comportamiento de los colaboradores
        Mockito.when(space.getSatellites()).thenReturn(Arrays.asList(satellite1, satellite2, satellite3));
        Mockito.when(locationService.getLocation(Mockito.any(), Mockito.any())).thenReturn(new Position(1.0, 2.0));
        Mockito.when(messageService.getMessage(Mockito.any())).thenReturn("este es un mensaje secreto");

        SatellitesData satellitesData = getDTOSatellitesData();

        SatellitesResponse satellitesResponse = intelligenceController.receiveAndDecodeInformation(satellitesData);
        assertNotNull(satellitesResponse);

    }

    /**
     * Caso de test para verificar el comportamiento del endpoint topsecret donde, caso en donde falla getLocation
     *
     */
    @Test
    void receiveAndDecodeData_ResponseFailsLocation() {
        //Definicion de los colaboradores
        Satellite satellite1 = new Satellite(-500, -200, "Kenobi");
        Satellite satellite2 = new Satellite(100, -100, "Skywalker");
        Satellite satellite3 = new Satellite(500, 100, "Sato");

        //Comportamiento de los colaboradores
        Mockito.when(space.getSatellites()).thenReturn(Arrays.asList(satellite1, satellite2, satellite3));
        Mockito.when(locationService.getLocation(Mockito.any(), Mockito.any())).thenThrow(new LocationException("No es posible determinar la posición del satélite"));

        SatellitesData satellitesData = getDTOSatellitesData();
        assertThrows(LocationException.class, () -> intelligenceController.receiveAndDecodeInformation(satellitesData));

    }

    /**
     * Caso de test para verificar el comportamiento del endpoint topsecret donde, caso donde falla getMessage
     *
     */
    @Test
    void receiveAndDecodeData_ResponseFailsMessage() {
        //Definicion de los colaboradores
        Satellite satellite1 = new Satellite(-500, -200, "Kenobi");
        Satellite satellite2 = new Satellite(100, -100, "Skywalker");
        Satellite satellite3 = new Satellite(500, 100, "Sato");

        //Comportamiento de los colaboradores
        Mockito.when(space.getSatellites()).thenReturn(Arrays.asList(satellite1, satellite2, satellite3));
        Mockito.when(messageService.getMessage(Mockito.any())).thenThrow(new MessageException("La estructura de los mensajes es inválida, no es posible decodificarlo"));

        SatellitesData satellitesData = getDTOSatellitesData();
        assertThrows(MessageException.class, () -> intelligenceController.receiveAndDecodeInformation(satellitesData));

    }

    /**
     * Metodo auxiliar para inicializar el DTO de los satelites que debería ingresar como body al endpoint.
     *
     */
    private SatellitesData getDTOSatellitesData() {
        SatellitesData satellitesData = new SatellitesData();
        SatelliteData satelliteData1 = new SatelliteData();
        satelliteData1.setName("Kenobi");
        satelliteData1.setDistance(10);
        satelliteData1.setMessage(Arrays.asList("este", "es", "un", "", "secreto"));

        SatelliteData satelliteData2 = new SatelliteData();
        satelliteData2.setName("Skywalker");
        satelliteData2.setDistance(10);
        satelliteData2.setMessage(Arrays.asList("este", "es", "un", "mensaje", ""));

        SatelliteData satelliteData3 = new SatelliteData();
        satelliteData3.setName("Sato");
        satelliteData3.setDistance(10);
        satelliteData3.setMessage(Arrays.asList("este", "es", "", "", ""));

        satellitesData.setSatellites(Arrays.asList(satelliteData1, satelliteData2, satelliteData3));
        return satellitesData;
    }

}