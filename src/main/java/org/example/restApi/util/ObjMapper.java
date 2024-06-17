package org.example.restApi.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ObjMapper {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static void objectToJson(Object obj, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), obj);
    }
}
