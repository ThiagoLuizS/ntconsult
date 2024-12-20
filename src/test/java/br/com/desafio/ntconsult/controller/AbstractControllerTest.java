package br.com.desafio.ntconsult.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractControllerTest {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
