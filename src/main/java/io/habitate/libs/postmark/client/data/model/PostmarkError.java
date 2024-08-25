package io.habitate.libs.postmark.client.data.model;

import lombok.Data;

/**
 * Postmark standard error.
 */
@Data
public class PostmarkError {

  private Integer errorCode;
  private String  message;
}
