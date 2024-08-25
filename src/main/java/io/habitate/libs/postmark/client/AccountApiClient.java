package io.habitate.libs.postmark.client;

import io.habitate.libs.postmark.client.data.model.RequestResponse;
import io.habitate.libs.postmark.client.data.model.domains.Domain;
import io.habitate.libs.postmark.client.data.model.domains.DomainDetails;
import io.habitate.libs.postmark.client.data.model.domains.Domains;
import io.habitate.libs.postmark.client.data.model.senders.SignatureDetails;
import io.habitate.libs.postmark.client.data.model.senders.SignatureToCreate;
import io.habitate.libs.postmark.client.data.model.senders.Signatures;
import io.habitate.libs.postmark.client.data.model.server.Server;
import io.habitate.libs.postmark.client.data.model.servers.Servers;
import io.habitate.libs.postmark.client.data.model.templates.TemplatesPush;
import io.habitate.libs.postmark.client.data.model.templates.TemplatesPushRequest;
import io.habitate.libs.postmark.client.exception.PostmarkException;

import java.io.IOException;
import java.util.Map;

/**
 * Class that handles on very top level all API requests. All Postmark public endpoints which
 * are reachable can be accessible by methods in this class.
 * <p>
 * For details about each endpoint check out Developer Docs:
 * https://postmarkapp.com/developer
 */
public class AccountApiClient extends BaseApiClient {

    private final String serversEndpoint = "/servers/";
    private final String domainsEndpoint = "/domains/";
    private final String sendersEndpoint = "/senders/";

    public AccountApiClient(String baseUrl, Map<String, Object> headers) {
        super(baseUrl, headers);
    }


    /*
      Servers endpoints for managing Servers in account.
     */

    public Server getServers(Integer id) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.GET, getEndpointUrl(serversEndpoint + id));
        return dataHandler.fromJson(response, Server.class);
    }

    public Server createServer(Server data) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.POST, getEndpointUrl(serversEndpoint), data);
        return dataHandler.fromJson(response, Server.class);
    }

    public Server setServer(Integer id, Server data) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.PUT, getEndpointUrl(serversEndpoint + id), data);
        return dataHandler.fromJson(response, Server.class);
    }

    public Servers getServers(Parameters parameters) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.GET, getEndpointUrl(serversEndpoint + parameters));
        return dataHandler.fromJson(response, Servers.class);
    }

    public RequestResponse deleteServer(Integer id) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.DELETE, getEndpointUrl(serversEndpoint + id));
        return dataHandler.fromJson(response, RequestResponse.class);
    }

    /*
      Domain endpoints
     */

    public Domains getDomains(Parameters parameters) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.GET, getEndpointUrl(domainsEndpoint + parameters));
        return dataHandler.fromJson(response, Domains.class);
    }

    public DomainDetails getDomainDetails(Integer id) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.GET, getEndpointUrl(domainsEndpoint + id));
        return dataHandler.fromJson(response, DomainDetails.class);
    }

    public DomainDetails createDomain(Domain domain) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.POST, getEndpointUrl(domainsEndpoint), domain);
        return dataHandler.fromJson(response, DomainDetails.class);
    }

    public DomainDetails setDomain(Integer id, DomainDetails domain) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.PUT, getEndpointUrl(domainsEndpoint + id), domain);
        return dataHandler.fromJson(response, DomainDetails.class);
    }

    public String deleteDomain(Integer id) throws PostmarkException, IOException {
        return execute(HttpClient.REQUEST_TYPES.DELETE, getEndpointUrl(domainsEndpoint + id));
    }

    public DomainDetails verifyDomainDKIM(Integer id) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.PUT, getEndpointUrl(domainsEndpoint + id + "/verifyDKIM"));
        return dataHandler.fromJson(response, DomainDetails.class);
    }

    public DomainDetails verifyDomainReturnPath(Integer id) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.PUT, getEndpointUrl(domainsEndpoint + id + "/verifyReturnPath"));
        return dataHandler.fromJson(response, DomainDetails.class);
    }

    public String verifyDomainSPF(Integer id) throws PostmarkException, IOException {
        return execute(HttpClient.REQUEST_TYPES.POST, getEndpointUrl(domainsEndpoint + id + "/verifySPF"));
    }

    public String rotateDomainDKIM(Integer id) throws PostmarkException, IOException {
        return execute(HttpClient.REQUEST_TYPES.POST, getEndpointUrl(domainsEndpoint + id + "/rotateDKIM"));
    }

    /*
      Sender Signature endpoints
     */

    public Signatures getSenderSignatures(Parameters parameters) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.GET, getEndpointUrl(sendersEndpoint + parameters));
        return dataHandler.fromJson(response, Signatures.class);
    }

    public SignatureDetails getSenderSignatureDetails(Integer id) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.GET, getEndpointUrl(sendersEndpoint + id));
        return dataHandler.fromJson(response, SignatureDetails.class);
    }

    public SignatureDetails createSenderSignature(SignatureToCreate signature) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.POST, getEndpointUrl(sendersEndpoint), signature);
        return dataHandler.fromJson(response, SignatureDetails.class);
    }

    public SignatureDetails setSenderSignature(Integer id, SignatureToCreate signature) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.PUT, getEndpointUrl(sendersEndpoint + id), signature);
        return dataHandler.fromJson(response, SignatureDetails.class);
    }

    public String deleteSenderSignature(Integer id) throws PostmarkException, IOException {
        return execute(HttpClient.REQUEST_TYPES.DELETE, getEndpointUrl(sendersEndpoint + id));
    }

    public String resendSenderSignatureConfirmation(Integer id) throws PostmarkException, IOException {
        return execute(HttpClient.REQUEST_TYPES.POST, getEndpointUrl(sendersEndpoint + id + "/resend"));
    }

    public SignatureDetails verifySenderSignatureSPF(Integer id) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.POST, getEndpointUrl(sendersEndpoint + id + "/verifySPF"));
        return dataHandler.fromJson(response, SignatureDetails.class);
    }

    public String requestSenderSignatureDKIM(Integer id) throws PostmarkException, IOException {
        return execute(HttpClient.REQUEST_TYPES.POST, getEndpointUrl(sendersEndpoint + id + "/requestNewDKIM"));
    }

    /*
      Templates endpoints
     */

    public TemplatesPush pushTemplates(TemplatesPushRequest data) throws PostmarkException, IOException {
        String response = execute(HttpClient.REQUEST_TYPES.PUT, getEndpointUrl("/templates/push"), data);
        return dataHandler.fromJson(response, TemplatesPush.class);
    }
}
