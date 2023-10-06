package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.tests.vendorportal.VendorPortalTest;
import org.example.tests.vendorportal.model.VendorPortalTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T getTestData(String path, Class<T> type){
        try (InputStream stream = ResourceLoader.getResource(path)){
            return objectMapper.readValue(stream, type);
        } catch (Exception e) {
            log.error("unable to read test data {}", path, e);
        }
        return null;

    }

}
