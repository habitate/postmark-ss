package io.habitate.libs.postmark;

import io.habitate.libs.postmark.client.AccountApiClient;
import io.habitate.libs.postmark.client.ApiClient;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Top level class allowing simple access to API clients for doing Postmark API calls.
 */
@Slf4j
public class Postmark {

    private Postmark() {
    }

    // public class methods for accessing API clients

    public static ApiClient getApiClient(String apiToken) {
        return new ApiClient(DEFAULTS.API_URL.value, getHeadersWithAuth(DEFAULTS.SERVER_AUTH_HEADER, apiToken));
    }

    public static AccountApiClient getAccountApiClient(String apiToken) {
        return new AccountApiClient(DEFAULTS.API_URL.value, getHeadersWithAuth(DEFAULTS.ACCOUNT_AUTH_HEADER, apiToken));
    }

    // private methods

    private static Map<String, Object> getHeadersWithAuth(DEFAULTS authType, String apiToken) {
        var headers = DefaultHeaders.headers();
        headers.put(authType.value, apiToken);
        return headers;
    }

    /**
     * Base Postmark APP Constants
     */
    public enum DEFAULTS {
        API_URL("https://api.postmarkapp.com"),
        SERVER_AUTH_HEADER("X-Postmark-Server-Token"),
        ACCOUNT_AUTH_HEADER("X-Postmark-Account-Token");

        public final String value;

        DEFAULTS(String value) {
            this.value = value;
        }
    }

    /**
     * Default headers sent always with API requests.
     */
    public static class DefaultHeaders {

        public static Map<String, Object> headers() {
            var headerValues = new HashMap<String, Object>();
            headerValues.put("User-Agent", "Postmark-ss Java Library");
            headerValues.put("Accept", "application/json");
            headerValues.put("Content-Type", "application/json");
            return headerValues;
        }

    }
}
