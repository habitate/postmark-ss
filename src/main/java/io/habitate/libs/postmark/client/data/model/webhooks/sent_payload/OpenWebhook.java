package io.habitate.libs.postmark.client.data.model.webhooks.sent_payload;

import io.habitate.libs.postmark.client.data.model.messages.OutboundMessageOpen;

import java.util.HashMap;

/**
 * Open webhook object.
 */
public class OpenWebhook extends OutboundMessageOpen {

    private Boolean firstOpen;
    private HashMap<String, String> metadata;

    // GETTERS AND SETTERS

    public HashMap<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<String, String> metadata) {
        this.metadata = metadata;
    }

    public Boolean getFirstOpen() {
        return firstOpen;
    }

    public void setFirstOpen(Boolean firstOpen) {
        this.firstOpen = firstOpen;
    }
}
