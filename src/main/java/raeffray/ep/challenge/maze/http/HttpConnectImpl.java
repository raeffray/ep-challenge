package raeffray.ep.challenge.maze.http;

import java.net.URI;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public abstract class HttpConnectImpl implements HttpConnect {

	private ClientConfig config;

	private Client client;

	protected WebResource service;

	protected MultivaluedMap<String, String> parameters;

	Logger logger = LoggerFactory.getLogger(HttpConnectImpl.class);

	protected void configure(String endpoint, int timeout) {
		if (client == null) {
			URI baseUri = UriBuilder.fromUri(endpoint).build();
			config = new DefaultClientConfig();
			client = Client.create(config);
			client.setReadTimeout(timeout);
			service = client.resource(baseUri);
		}
	}

	public void putParameters(Map<String, String> parameters) {
		if (this.parameters == null) {
			this.parameters = new MultivaluedMapImpl();
		}

		for (Iterator<String> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			this.parameters.putSingle(key, (String) parameters.get(key));
		}

	}
}