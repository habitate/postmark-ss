package io.habitate.libs.postmark.client.exception;

/**
 * Group of classes that identifies main Postmark API exceptions.
 */

public class UnknownException extends PostmarkException {
    public UnknownException(String message) {
        super(message);
    }

}

