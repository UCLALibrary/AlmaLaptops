
package edu.ucla.library.libservices.alma.laptops.clients;

import edu.ucla.library.libservices.alma.laptops.beans.MemberList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class MemberClient {

    private String key;

    private String baseURL;

    private String setID;

    private String limit;

    private String offset;

    public MemberClient() {
        super();
    }

    private String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    private String getSetID() {
        return setID;
    }

    public void setSetID(String setID) {
        this.setID = setID;
    }

    private String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    private String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public MemberList getRecords() {
        final Client client;
        final MemberList records;

        client = ClientBuilder.newClient();

        records = client.target(buildURI()).request(MediaType.APPLICATION_JSON).get(MemberList.class);

        return records;
    }

    private String buildURI() {
        StringBuilder uri = new StringBuilder(getBaseURL());
        uri.append(getSetID()).append("/members?");
        if (getLimit() != null) {
            uri.append("limit=").append(getLimit()).append("&");
        }
        if (getOffset() != null) {
            uri.append("offset=").append(getOffset()).append("&");
        }
        uri.append("apikey=").append(getKey());

        return uri.toString();
    }
}
