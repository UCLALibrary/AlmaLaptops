
package edu.ucla.library.libservices.alma.laptops.clients;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingJsonFactory;

import edu.ucla.library.libservices.alma.laptops.beans.MemberList;

import java.io.IOException;
import java.io.InputStream;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import javax.net.ssl.SSLException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MemberClient {

    final static Logger logger = LogManager.getLogger( MemberClient.class );

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
		try {
			final WebClient client = WebClient.create(buildURI());
			client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
			Response response = client.get();

			if (response.getStatus() == 200) {
				MappingJsonFactory factory = new MappingJsonFactory();
				JsonParser parser = factory.createJsonParser((InputStream)response.getEntity());
				final MemberList records = parser.readValueAs(MemberList.class);
				return records;
			} else {
				logger.error(response.getStatusInfo().getReasonPhrase());
				return new MemberList();
			}
		} catch (Fault details) {
			logger.error(details.getMessage());
			return new MemberList();
		} catch (SocketTimeoutException details) {
			logger.error(details.getMessage());
			return new MemberList();
		} catch (NoRouteToHostException details) {
			logger.error(details.getMessage());
			return new MemberList();
		} catch (SSLException details) {
			logger.error(details.getMessage());
			return new MemberList();
		} catch (IOException details) {
			logger.error(details.getMessage());
			return new MemberList();
		} catch (Exception details) {
			logger.error(details.getMessage());
			return new MemberList();
		}
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
