package raeffray.ep.challenge.maze.http;

import java.util.Map;

import raeffray.ep.challenge.maze.http.exception.HttpRequestException;

public interface HttpConnect {

	public void putParameters(Map<String, String> parameters);

	public String call() throws HttpRequestException;

	public void setTimeout(int timeout);

	public void setEndpoint(String endpoint);

}