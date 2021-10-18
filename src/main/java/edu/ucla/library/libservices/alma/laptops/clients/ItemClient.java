
package edu.ucla.library.libservices.alma.laptops.clients;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingJsonFactory;

import edu.ucla.library.libservices.alma.laptops.beans.AlmaItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.Fault;
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
        final JsonParser parser;
        final MappingJsonFactory factory;
        final Response response;
        final WebClient client;

		try {
			client = WebClient.create(getLink().concat("?apikey=").concat(getKey()));
			client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
			response = client.get();
			if (response.getStatus() == 200) {
				factory = new MappingJsonFactory();
				parser = factory.createJsonParser((InputStream)response.getEntity());
				item = parser.readValueAs(AlmaItem.class);

				return item;
			} else {
				logger.error(response.getStatusInfo().getReasonPhrase());
				return new AlmaItem();
			}
		} catch (UnknownHostException details) {
			logger.error(details.getMessage());
			return new AlmaItem();
		} catch (Fault details) {
			logger.error(details.getMessage());
			return new AlmaItem();
		} catch (SocketTimeoutException details) {
			logger.error(details.getMessage());
			return new AlmaItem();
		} catch (NoRouteToHostException details) {
			logger.error(details.getMessage());
			return new AlmaItem();
		} catch (SSLException details) {
			logger.error(details.getMessage());
			return new AlmaItem();
		} catch (IOException details) {
			logger.error(details.getMessage());
			return new AlmaItem();
		} catch (Exception details) {
			logger.error(details.getMessage());
			return new AlmaItem();
		}
    }
}
