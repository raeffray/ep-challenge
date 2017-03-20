package raeffray.ep.challenge.maze.http;

import java.net.HttpURLConnection;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;

import raeffray.ep.challenge.maze.http.exception.GatewayUnavailableException;
import raeffray.ep.challenge.maze.http.exception.HttpRequestException;

public class HttpConnectMethoGet extends HttpConnectImpl {

	private final String ENCODE = "application/x-www-form-urlencoded";

	private String endpoint;

	private int timeout;

	public String call() throws HttpRequestException {

		super.configure(endpoint, timeout);

		ClientResponse response = null;

		//logger.debug("[received parameters:  ]");
		//logger.debug("[encode     : " + ENCODE + " ]");
		//logger.debug("[parameters : " + parameters + " ]");

		try {
			
			if(parameters!=null){
				response = service.queryParams(parameters).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
			}else{
				response = service.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
			}

			int httpStatus = response.getStatus();

			if (httpStatus != HttpURLConnection.HTTP_OK) {
				throw new HttpRequestException("Request error :" + httpStatus);
			}

		} catch (UniformInterfaceException e) {
			throw new GatewayUnavailableException(e);
		} catch (ClientHandlerException e) {
			throw new GatewayUnavailableException(e);
		} catch (Throwable e) {
			throw new HttpRequestException(e);
		}

		return response.getEntity(String.class);
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
