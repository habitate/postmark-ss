package io.habitate.libs.postmark.client;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.function.Function;

/**
 * Base HTTP client class solely responsible for making
 * client requests and returning simple HTTP response.
 */
@Slf4j
public class HttpClient {

  private static final Function<Integer, java.net.http.HttpClient> clients =
          (timeout) -> java.net.http.HttpClient.newBuilder()
                                               .connectTimeout(Duration.ofSeconds(timeout))
                                               .followRedirects(
                                                       java.net.http.HttpClient.Redirect.NORMAL)
                                               .build();

  private final Map<String, Object> headers;
  private       int                 connectTimeoutSeconds = 60;
  private       int                 readTimeoutSeconds    = 60;

  public HttpClient(Map<String, Object> headers,
                    int connectTimeoutSeconds,
                    int readTimeoutSeconds) {
    this(headers);
    this.connectTimeoutSeconds = connectTimeoutSeconds;
    this.readTimeoutSeconds    = readTimeoutSeconds;
  }


  public HttpClient(Map<String, Object> headers) {
    this.headers = headers;
  }

  /**
   * Overload method for executing requests, which doesn't contain data
   *
   * @param request_type type of HTTP request
   * @param url          request URL
   *
   * @return simplified HTTP request response (status and response text)
   *
   * @see #execute(REQUEST_TYPES, String, String) for details
   */
  public ClientResponse execute(REQUEST_TYPES request_type, String url) {
    return execute(request_type, url, null);
  }

  /**
   * Execute HTTP request.
   *
   * @param requestType type of HTTP request to initiate
   * @param url         request url
   * @param data        data sent for POST/PUT requests
   *
   * @return response from HTTP request
   */
  public ClientResponse execute(REQUEST_TYPES requestType, String url, String data) {
    try {
      var body = data == null ?
                 HttpRequest.BodyPublishers.noBody() :
                 HttpRequest.BodyPublishers.ofString(data);
      var builder = HttpRequest.newBuilder()
                               .uri(URI.create(url))
                               .timeout(Duration.ofSeconds(readTimeoutSeconds))
                               .method(requestType.name(), body);
      headers.forEach((s, o) -> builder.header(s, String.valueOf(o)));
      var response = getClient().send(builder.build(),
                                      HttpResponse.BodyHandlers.ofString());
      return transformResponse(response);
    } catch (IOException | InterruptedException e) {
      log.error("Error while executing request", e);
      throw new RuntimeException(e);
    }
  }

  public java.net.http.HttpClient getClient() {
    return clients.apply(connectTimeoutSeconds);
  }

  /**
   * Gets simplified HTTP request response that will contain only response and status.
   *
   * @param response HTTP request response result
   *
   * @return simplified HTTP request response
   */
  private ClientResponse transformResponse(HttpResponse<String> response) {
    return new ClientResponse(response.statusCode(), response.body(), response.headers());
  }

  /**
   * Allowed HTTP request types.
   */
  public enum REQUEST_TYPES {
    POST,
    GET,
    PUT,
    PATCH,
    DELETE
  }

}
