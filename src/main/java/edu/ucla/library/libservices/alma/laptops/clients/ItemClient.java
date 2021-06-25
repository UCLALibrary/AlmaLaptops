
package edu.ucla.library.libservices.alma.laptops.clients;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingJsonFactory;

import edu.ucla.library.libservices.alma.laptops.beans.AlmaItem;

import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemClient {

	final static Logger logger = LogManager.getLogger( ItemClient.class );

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
        //final Client client;
        final JsonParser parser;
        final MappingJsonFactory factory;
        final Response response;
        final WebClient client;

		try {
			//client = ClientBuilder.newClient();
			client = WebClient.create(getLink().concat("?apikey=").concat(getKey()));
			client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
			response = client.get();
			factory = new MappingJsonFactory();
			parser = factory.createJsonParser((InputStream)response.getEntity());
			item = parser.readValueAs(AlmaItem.class);
			//item = client.target(getLink().concat("?apikey=").concat(getKey())).request(MediaType.APPLICATION_JSON)
			//        .get(AlmaItem.class);

			return item;
		} catch (IOException details) {
			logger.error(details.getMessage());
			return null;
		}
    }
}
