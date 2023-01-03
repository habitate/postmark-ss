package io.habitate.libs.postmark.client.data.model;

/**
 * Postmark standard error.
 */
public class PostmarkError {

    private Integer errorCode;
    private String message;

    public Integer getErrorCode() {

        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
