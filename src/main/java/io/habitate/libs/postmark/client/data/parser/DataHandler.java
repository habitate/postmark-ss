package io.habitate.libs.postmark.client.data.parser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;

/**
 * Data serializer/deserializer between API client and HTTP client.
 * It makes sure that proper mechanism is used to convert data to and from data format for HTTP requests and for HTTP responses.
 */
public class DataHandler {

    private final ObjectMapper mapper;

    public DataHandler() {
        this.mapper = JsonMapper.builder()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .propertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .build();
        setLiberalMapper();
    }

    public DataHandler(boolean isStrictMapper) {
        this();

        if (isStrictMapper) {
            setStrictMapper();
        } else {
            setLiberalMapper();
        }
    }

    public String toJson(Object data) throws IOException {
        return this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
    }

    /**
     * @param response  JSON object in String format
     * @param valueType accepts types like ArrayList
     * @param <T>       Class type to return
     * @return requested object type
     * @throws IOException in case converting String to Object fails
     */
    public <T> T fromJson(String response, Class<T> valueType) throws IOException {
        return this.mapper.readValue(response, valueType);
    }

    /**
     * @param response  JSON object in String format
     * @param valueType accepts parametrized types
     * @param <T>       Class type to return
     * @return String object converted to requested object
     * @throws IOException in case converting String to Object fails
     */
    public <T> T fromJson(String response, TypeReference<T> valueType) throws IOException {
        return this.mapper.readValue(response, valueType);
    }

    /**
     * Sets data mapper to be strict when making conversion of data to objects.
     * If there is a mismatch between object and String in any other case than letter case,
     * exception will be thrown.
     */
    public void setStrictMapper() {
        this.mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
        this.mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }

    /**
     * Sets data mapper to be very liberal when making conversion of data to objects.
     * Most of the time exception will NOT be thrown.
     */
    public void setLiberalMapper() {
        this.mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        this.mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

}
