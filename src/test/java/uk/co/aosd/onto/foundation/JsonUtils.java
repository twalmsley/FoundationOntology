package uk.co.aosd.onto.foundation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

/**
 * JSON Utility Methods.
 *
 * @author Tony Walmsley
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule())
        .registerModule(new ParameterNamesModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    /**
     * Convert an object to JSON and dump it to the console.
     *
     * @param o
     *            Object
     */
    public static void dumpJson(final Object o) {
        try {
            final String jsonString = objectMapper.writeValueAsString(o);
            System.out.println(jsonString);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert an object to a JSON String.
     *
     * @param o
     *            Object
     * @return String
     * @throws JsonProcessingException
     *             on error
     */
    public static String dumpJsonString(final Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    /**
     * Convert a JSON string to a specified type.
     *
     * @param <T>
     *            The type to return.
     * @param json
     *            String
     * @param valueType
     *            java.lang.Class of T
     * @return T
     * @throws JsonMappingException
     *             on error
     * @throws JsonProcessingException
     *             on error
     */
    public static <T extends UniquelyIdentifiable> T readJsonString(final String json, final java.lang.Class<T> valueType)
        throws JsonMappingException, JsonProcessingException {
        return (T) objectMapper.readValue(json, valueType);
    }
}
