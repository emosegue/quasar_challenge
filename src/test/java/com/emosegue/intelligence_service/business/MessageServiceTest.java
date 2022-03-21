package com.emosegue.intelligence_service.business;

import com.emosegue.intelligence_service.exception.MessageException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class MessageServiceTest {

    @Autowired
    MessageService messageService;

    /**
     * Caso de test para verificar el comportamiento de getMessage, caso ok
     *
     */
    @Test
    void getMessage_ReturnsOk() {

        List<List<String>> messageList = instanceSatellitesWithMessages(true);
        String decodedMessage = messageService.getMessage(messageList);
        assertEquals("este es un mensaje", decodedMessage);
    }

    /**
     * Caso de test para verificar el comportamiento de getMessage, de excepcion
     *
     */
    @Test
    void getMessage_ReturnsException() {

        List<List<String>> messageList = instanceSatellitesWithMessages(false);
        assertThrows(MessageException.class, () -> {
            messageService.getMessage(messageList);
        });
    }

    /**
     * Metodo auxiliar para instanciar satelites
     *
     */
    private List<List<String>> instanceSatellitesWithMessages(boolean isValidMessageList) {

        List<List<String>> messageList = new ArrayList<>();
        messageList.add(Arrays.asList("", "este", "es", "un", "mensaje"));
        messageList.add(Arrays.asList("este", "", "un", "mensaje"));
        if (!isValidMessageList)
            messageList.add(Arrays.asList("", "", "este", "", "", "mensaje", "failWord"));
        else
            messageList.add(Arrays.asList("", "", "este", "", "", "mensaje"));
        return messageList;
    }

}