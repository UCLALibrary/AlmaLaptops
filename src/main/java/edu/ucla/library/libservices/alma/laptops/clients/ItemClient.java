
package edu.ucla.library.libservices.alma.laptops.clients;

import edu.ucla.library.libservices.alma.laptops.beans.AlmaItem;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class ItemClient {

    private String key;

    private String link;

    public ItemClient() {
        super();
    }

    private String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public AlmaItem getItem() {
        final AlmaItem item;
        final Client client;

        client = ClientBuilder.newClient();
        item = client.target(getLink().concat("?apikey=").concat(getKey())).request(MediaType.APPLICATION_JSON)
                .get(AlmaItem.class);

        return item;
    }
}
