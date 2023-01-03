package io.habitate.libs.postmark.client;

import io.habitate.libs.postmark.client.data.model.PostmarkError;
import io.habitate.libs.postmark.client.data.parser.DataHandler;
import io.habitate.libs.postmark.client.exception.*;

import java.io.IOException;

/**
 * Handlers errors returned by Http client. Main use of the class is to parse Postmark errors based on error content.
 */
public class HttpClientErrorHandler {
    protected final DataHandler dataHandler;

    public HttpClientErrorHandler(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    /**
     * Validates HTTP request responses.
     *
     * @param statusCode - HTTP status code
     * @param message    - HTTP response message
     * @return postmark error
     * @throws IOException in case invalid HTTP response is returned.
     */
    public PostmarkException throwErrorBasedOnStatusCode(Integer statusCode, String message) throws IOException {
        return switch (statusCode) {
            case 401 -> new InvalidAPIKeyException(postmarkErrorFromResponse(message));
            case 408 -> new TimeoutException(message);
            case 422 -> new InvalidMessageException(postmarkErrorFromResponse(message));
            case 500 -> new InternalServerException(message);
            default -> new UnknownException(message);
        };
    }

    /**
     * Parse postmark error message from string
     *
     * @param message - error message
     * @return - Postmark error object
     * @throws IOException
     */
    private PostmarkError postmarkErrorFromResponse(String message) throws IOException {
        return dataHandler.fromJson(message, PostmarkError.class);
    }
}
