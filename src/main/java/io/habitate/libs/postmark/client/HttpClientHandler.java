package io.habitate.libs.postmark.client;

import io.habitate.libs.postmark.client.data.parser.DataHandler;
import io.habitate.libs.postmark.client.exception.PostmarkException;

import java.io.IOException;
import java.util.Map;

/**
 * Client class acts as handler between HTTP requests handler class (HttpClient) and class which provides access to all endpoints to call.
 * This class provides correct data for both sides and acts as controller. Also it verifies whether data sent and received is correct.
 */
public class HttpClientHandler {

    protected final DataHandler dataHandler;
    private final HttpClient httpClient;
    private final HttpClientErrorHandler httpClientErrorHandler;

    protected HttpClientHandler(Map<String, Object> headers) {
        this.dataHandler = new DataHandler(false);
        this.httpClientErrorHandler = new HttpClientErrorHandler(this.dataHandler);
        httpClient = new HttpClient(headers);
    }

    /**
     * Execute HTTP requests with no data sending required.
     *
     * @param request_type HTTP request type
     * @param url          HTTP request URL
     * @return request response
     * @throws PostmarkException Errors thrown by invalid or unhandled requests made to Postmark
     * @throws IOException       Errors thrown by Data Handler
     * @see #execute(HttpClient.REQUEST_TYPES, String, Object) for details
     */
    protected String execute(HttpClient.REQUEST_TYPES request_type, String url) throws PostmarkException, IOException {
        return execute(request_type, url, null);
    }

    /**
     * Execute HTTP requests
     *
     * @param request_type HTTP request type
     * @param url          HTTP request URL
     * @param data         request data to send
     * @return HTTP response message
     * @throws PostmarkException Errors thrown by invalid or unhandled requests made to Postmark
     * @throws IOException       Errors thrown by Data Handler
     * @see HttpClient for details about HTTP request execution.
     */
    protected String execute(HttpClient.REQUEST_TYPES request_type, String url, Object data) throws PostmarkException, IOException {
        ClientResponse response = httpClient.execute(request_type, url, dataHandler.toJson(data));

        if (response.code() == 200) {
            return response.message();
        } else {
            throw httpClientErrorHandler.throwErrorBasedOnStatusCode(response.code(), response.message());
        }
    }
}
