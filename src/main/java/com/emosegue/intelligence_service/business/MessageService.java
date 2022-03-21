package com.emosegue.intelligence_service.business;

import com.emosegue.intelligence_service.exception.MessageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author emosegue
 */
@Service
public class MessageService {

    /**
     * Metodo principal de la clase, el mismo determina el mensaje codificado
     *
     * @param messageList lista que contiene en cada posicion una lista de palabras
     * @return String de palabras que componen el mensaje secreto concatenadas mediante un "blanco".
     */
    public String getMessage(List<List<String>> messageList) throws MessageException {
        List<String> distinctWordsList = getDistinctWords(messageList);
        if (!isValidMessageSize(messageList, distinctWordsList)) {
            throw new MessageException("La estructura de los mensajes es inválida, no es posible decodificarlo");
        }
        List<List<String>> messageListWithoutLTrim = removeLTrim(messageList, distinctWordsList.size());
        return decodeMessage(messageListWithoutLTrim);
    }

    /**
     * Metodo para decodificar el mensaje luego de intentar detectar errores en instancias previas
     *
     * @param messageProcessedList lista que contiene en cada posicion una lista de palabras ya procesada
     * @return String de palabras que componen el mensaje secreto concatenadas mediante un "blanco".
     */
    private static String decodeMessage(List<List<String>> messageProcessedList) {
        List<String> decodedMessage = new ArrayList<>();
        String word;
        for (int i = 0; i < messageProcessedList.get(0).size(); i++) {
            for (List<String> message : messageProcessedList) {
                word = message.get(i);
                if (!"".equals(word) && !decodedMessage.contains(word))
                    decodedMessage.add(word);
            }
        }
        return String.join(" ", decodedMessage);
    }

    /**
     * Metodo para colapsar el arreglo multidimensional en uno de una dimension, eliminando palabras repetidas y vacías
     *
     * @param messageList lista que contiene en cada posicion una lista de palabras
     * @return lista que contiene todas las palabras no repetidas.
     */
    private List<String> getDistinctWords(List<List<String>> messageList) {
        List<String> distinctWords = new ArrayList<>();
        for (List<String> message : messageList) {
            for (String word : message) {
                if (!"".equals(word) && !distinctWords.contains(word))
                    distinctWords.add(word);
            }
        }
        return distinctWords;
    }

    /**
     * Metodo para eliminar el left trim en el mensaje en aquellos casos que sea posible.
     *
     * @param messageList lista que contiene en cada posicion una lista de palabras
     * @param numberOfDistinctWords  numero de palabras no repetidas entre todos los mensajes
     */
    private List<List<String>> removeLTrim(List<List<String>> messageList, int numberOfDistinctWords) {
        List<List<String>> newList = new ArrayList<>();
        int elementsToRemove;
        for (List<String> message : messageList) {
            elementsToRemove = 0;
            for (String word : message) {
                if (message.size() > numberOfDistinctWords && "".equals(word))
                    elementsToRemove++;
                else
                    break;
            }
            newList.add(message.subList(elementsToRemove, message.size()));
        }
        int numberOfElements = newList.get(0).size();
        for (List<String> messageClean : newList) {
            if (messageClean.size() != numberOfElements)
                throw new MessageException("La estructura de los mensajes es inválida, no es posible decodificarlo");
        }
        return newList;
    }

    /**
     * Metodo para determinar si los mensajes enviados, son coherentes con el posible tamaño del mensaje
     *
     * @param messageList    lista que contiene en cada posicion una lista de palabras
     * @param differentWordsList lista que contiene todas las palabras no repetidas.
     * @return Regresa true en caso de que sea consistente y false en caso de que haya un error
     */
    private boolean isValidMessageSize(List<List<String>> messageList, List<String> differentWordsList) {
        boolean isValidMessageSize = true;
        for (List<String> message : messageList) {
            if (message.size() < differentWordsList.size()) {
                isValidMessageSize = false;
                break;
            }
        }
        return isValidMessageSize;
    }
}