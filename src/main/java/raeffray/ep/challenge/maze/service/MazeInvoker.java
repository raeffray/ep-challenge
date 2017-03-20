package raeffray.ep.challenge.maze.service;

import java.util.HashMap;
import java.util.Map;

import raeffray.ep.challenge.maze.domain.Direction;
import raeffray.ep.challenge.maze.domain.MazeAction;
import raeffray.ep.challenge.maze.domain.MazePosition;
import raeffray.ep.challenge.maze.domain.Position;
import raeffray.ep.challenge.maze.helper.JSONBuilder;
import raeffray.ep.challenge.maze.http.HttpConnectMethoGet;
import raeffray.ep.challenge.maze.http.HttpConnectMethodPost;
import raeffray.ep.challenge.maze.http.exception.HttpRequestException;


public class MazeInvoker {
	
	MazePosition mazePosition = null;
	
	Map<String,String> parameters;
	
	private MazePosition executeActionPost(MazeAction action, Direction direction, int x, int y) throws HttpRequestException{
		
		HttpConnectMethodPost methodPost = new HttpConnectMethodPost();
		
		methodPost.setEndpoint(action.getUrl());
		methodPost.setTimeout(2000);
		
		if(parameters!=null){
			methodPost.putParameters(parameters);			
		}
		
		String call;
		try {
			
			call = methodPost.call();
			
			Object object = JSONBuilder.getObject(call, Position.class);
			
			Position pos = (Position) object;
			
			return pos.getCurrentCell();
			
		} catch (HttpRequestException e) {
			throw e;
		}
	}
	
	private MazePosition executeActionGet(MazeAction action, Direction direction, int x, int y){
		
		HttpConnectMethoGet methodGet = new HttpConnectMethoGet();
		
		methodGet.setEndpoint(action.getUrl());
		methodGet.setTimeout(2000);
		
		if(parameters!=null){
			methodGet.putParameters(parameters);			
		}
		
		String call;
		try {
			
			call = methodGet.call();
			
			Object object = JSONBuilder.getObject(call, Position.class);
			
			Position pos = (Position) object;
			
			return pos.getCurrentCell();
			
		} catch (HttpRequestException e) {
			throw new RuntimeException(e);
		}
	}
	
	public MazePosition initialize() throws HttpRequestException{
		mazePosition = executeActionPost(MazeAction.INITIALIZE, null, 0 , 0);
		parameters = new HashMap<String, String>();
		parameters.put("mazeGuid", mazePosition.getMazeGuid());
		return mazePosition;
	}
	
	public MazePosition move(Direction direction) throws HttpRequestException{
		parameters.remove("x");
		parameters.remove("y");
		parameters.put("direction", direction.toString());
		mazePosition = executeActionPost(MazeAction.MOVE, direction, 0, 0);
		return mazePosition;
	}
	
	public MazePosition jump(int x, int y) throws HttpRequestException{
		parameters.remove("direction");
		parameters.put("x", String.valueOf(x));
		parameters.put("y", String.valueOf(y));
		mazePosition = executeActionPost(MazeAction.JUMP, null, x, y);
		return mazePosition;
	}

	public MazePosition currentCell(MazePosition mazePosition){
		if(mazePosition!=null){
			this.mazePosition = mazePosition;
		}
		this.mazePosition = mazePosition;
		if(parameters == null){
			parameters = new HashMap<String, String>();
			parameters.put("mazeGuid", mazePosition.getMazeGuid());
		}
		mazePosition = executeActionGet(MazeAction.CURRENT_CELL, null, 0, 0);
		return mazePosition;
	}
	
	public MazePosition currentCell(){
		return currentCell(null);
	}
}
