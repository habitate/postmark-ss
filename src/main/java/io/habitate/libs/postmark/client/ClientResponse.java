package io.habitate.libs.postmark.client;

/**
 * Class that represents simplified HTTP request response.
 */
public record ClientResponse(int code, String message, java.net.http.HttpHeaders headers) {

}
