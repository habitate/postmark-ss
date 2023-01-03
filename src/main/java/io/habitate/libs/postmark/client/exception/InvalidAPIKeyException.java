package io.habitate.libs.postmark.client.exception;

import io.habitate.libs.postmark.client.data.model.PostmarkError;

/**
 * Group of classes that identifies main Postmark API exceptions.
 */
public class InvalidAPIKeyException extends PostmarkException {
    public InvalidAPIKeyException(PostmarkError error) {
        super(error.getMessage(), error.getErrorCode());
    }
}
