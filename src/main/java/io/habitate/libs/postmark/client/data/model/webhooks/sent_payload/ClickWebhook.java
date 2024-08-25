package io.habitate.libs.postmark.client.data.model.webhooks.sent_payload;

import io.habitate.libs.postmark.client.data.model.messages.OutboundMessageClick;

import java.util.HashMap;

/**
 * Click webhook object.
 */
public class ClickWebhook extends OutboundMessageClick {

    private HashMap<String, String> metadata;

    // GETTERS AND SETTERS

    public HashMap<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<String, String> metadata) {
        this.metadata = metadata;
    }
}
