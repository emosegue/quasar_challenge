package com.emosegue.intelligence_service.business;

import com.emosegue.intelligence_service.model.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class LocationServiceTest {

    @Autowired
    LocationService locationService;

    /**
     * Caso de test para verificar el comportamiento de getLocacion, caso ok
     *
     */
    @Test
    void getLocation_ReturnsOk_1(){

        double [][] positions  = new double[][]{{1.0, 1.0}, {3.0, 1.0}, {2.0, 2.0}};
        double[] distances = new double[]{1.0, 1.0, 1.0};
        Position result = locationService.getLocation(positions,distances);
        assertEquals(2.0,result.getX());
        assertEquals(1.0,result.getY());
    }

    /**
     * Caso de test para verificar el comportamiento de getLocacion, caso ok
     *
     */
    @Test
    void getLocation_ReturnsOk_2(){

        double [][] positions  = new double[][]{{1.0, 1.0}, {1.0, 1.0}, {1.0, 1.0}};
        double[] distances = new double[]{1.0, 1.0, 1.0};
        Position result = locationService.getLocation(positions,distances);
        assertEquals(1.0,result.getX());
        assertEquals(1.0,result.getY());
    }

    /**
     * Caso de test para verificar el comportamiento de getLocacion, caso ok
     *
     */
    @Test
    void getLocation_ReturnsOk_3(){

        double [][] positions  = new double[][]{{1.0, 1.0}, {1.0, 3.0}, {8.0, 8.0}};
        double[] distances = new double[]{5.0, 5.0, 6.36};
        Position result = locationService.getLocation(positions,distances);
        assertEquals(5.896457142857143,result.getX());
        assertEquals(2.0,result.getY());
    }

}