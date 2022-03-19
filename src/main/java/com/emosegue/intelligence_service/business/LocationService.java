
package com.emosegue.intelligence_service.business;

import com.emosegue.intelligence_service.exception.MessageException;
import com.emosegue.intelligence_service.model.Position;
import com.lemmingapex.trilateration.LinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.emosegue.intelligence_service.exception.LocationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author emosegue
 */
@Service
public class LocationService {

    /**
     * Metodo para triangular una posición a partir de una serie de coordenadas y las distancias al punto
     * @param positions arreglo bidimensional de posiciones
     * @param distances arreglo unidimensional de distancias
     * @return Regresa una Position en caso de ser posible calcularse
     */
    public Position getLocation(double[][] positions, double[] distances) throws LocationException{
    
        double[] calculatedLocation = null; 
        try{
            TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
            LinearLeastSquaresSolver lSolver = new LinearLeastSquaresSolver(trilaterationFunction);
            calculatedLocation  = lSolver.solve().toArray();
            if (calculatedLocation.length < 2)
                throw new MessageException("No es posible determinar la posición del satélite");
        }
        catch (LocationException e){
            System.out.println(e.getMessage());
        }
        return new Position(calculatedLocation[0], calculatedLocation[1]);
        
    }
}
