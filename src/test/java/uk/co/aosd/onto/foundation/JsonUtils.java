package uk.co.aosd.onto.foundation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;

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

    static {
        JacksonConfig.registerSubtypes(objectMapper, "uk.co.aosd.onto.reference");
        final PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfSubType(UniquelyIdentifiable.class)
            .allowIfSubType(Set.class)
            .allowIfSubType(ScalarValue.class)
            .build();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
    }

    /**
     * Convert an object to JSON and dump it to the console.
     *
     * @param o
     *            Object
     */
    public static void dumpJsonToConsole(final Object o) {
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
    public static String writeJsonString(final Object o) throws JsonProcessingException {
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

    private static class JacksonConfig {

        public static void registerSubtypes(final ObjectMapper objectMapper, final String packageToScan) {
            final var classes = JsonTypeNameScanner.scanForJsonTypeNameClasses(packageToScan);

            for (final var clazz : classes) {
                @SuppressWarnings("unchecked")
                final JsonTypeName annotation = (JsonTypeName) clazz.getAnnotation(JsonTypeName.class);
                if (annotation != null) {
                    objectMapper.registerSubtypes(new NamedType(clazz, annotation.value()));
                }
            }
        }
    }

    class JsonTypeNameScanner {

        @SuppressWarnings("rawtypes")
        public static List<java.lang.Class> scanForJsonTypeNameClasses(final String packageName) {
            try (final var scanResult = new ClassGraph()
                .enableClassInfo()
                .enableAnnotationInfo()
                .acceptPackages(packageName)
                .scan()) {

                return scanResult.getClassesWithAnnotation(JsonTypeName.class.getName())
                    .stream()
                    .map(ClassInfo::loadClass)
                    .filter(java.lang.Class::isRecord)
                    .collect(Collectors.toList());
            }
        }
    }
}
