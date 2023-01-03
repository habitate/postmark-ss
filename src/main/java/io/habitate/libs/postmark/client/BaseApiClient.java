package io.habitate.libs.postmark.client;

import java.util.Map;

/**
 * Class that handles (on very high level) API requests. All Postmark public endpoints which
 * are reachable can be accessible by classes extending this parent class.
 */
public class BaseApiClient extends HttpClientHandler {

    protected final String baseUrl;

    public BaseApiClient(String baseUrl, Map<String, Object> headers) {
        super(headers);
        this.baseUrl = baseUrl;
    }

    public String getEndpointUrl(String endpoint) {
        return baseUrl + endpoint;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
