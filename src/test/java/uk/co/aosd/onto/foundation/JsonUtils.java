package uk.co.aosd.onto.foundation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * JSON Utility Methods.
 *
 * @author Tony Walmsley
 */
public class JsonUtils {

    /**
     * Convert an object to JSON and dump it to the console.
     *
     * @param o Object
     */
    public static void dumpJson(final Object o) {
        final ObjectMapper objectMapper = new ObjectMapper().registerModule(new Jdk8Module()).registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            final String jsonString = objectMapper.writeValueAsString(o);
            System.out.println(jsonString);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
