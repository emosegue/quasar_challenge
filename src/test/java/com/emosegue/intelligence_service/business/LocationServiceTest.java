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

    @Test
    public void getLocationCase1(){

        double [][] positions  = new double[][]{{1.0, 1.0}, {3.0, 1.0}, {2.0, 2.0}};
        double[] distances = new double[]{1.0, 1.0, 1.0};
        Position result = locationService.getLocation(positions,distances);
        assertEquals(2.0,result.getX());
        assertEquals(1.0,result.getY());
    }

    @Test
    public void getLocationCase2(){

        double [][] positions  = new double[][]{{1.0, 1.0}, {1.0, 1.0}, {1.0, 1.0}};
        double[] distances = new double[]{1.0, 1.0, 1.0};
        Position result = locationService.getLocation(positions,distances);
        assertEquals(1.0,result.getX());
        assertEquals(1.0,result.getY());
    }

    @Test
    public void getLocationCase3(){

        double [][] positions  = new double[][]{{1.0, 1.0}, {1.0, 3.0}, {8.0, 8.0}};
        double[] distances = new double[]{5.0, 5.0, 6.36};
        Position result = locationService.getLocation(positions,distances);
        assertEquals(5.896457142857143,result.getX());
        assertEquals(2.0,result.getY());
    }

    @Test
    public void getLocationCase4(){

        double [][] positions  = new double[][]{{-19.6685,-69.1942}, {-20.2705,-70.1311}, {-20.5656,-70.1807}};
        double[] distances = new double[]{84, 114, 120};
        Position result = locationService.getLocation(positions,distances);
        assertEquals(-20.548034139289,result.getX());
        assertEquals(-69.266174618331,result.getY());
    }

}