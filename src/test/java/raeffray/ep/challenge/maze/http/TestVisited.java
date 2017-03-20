package raeffray.ep.challenge.maze.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestVisited {
	
	Map<Integer[],String> visitedPoints = new HashMap<Integer[],String>();
	
	@Test
	public void testVisited(){
		
		Integer[] visited1 = {0,0}; 
		Integer[] visited2 = {1,1};
		
		visitedPoints.put(visited1, null);
		visitedPoints.put(visited2, null);
		
		Integer[] newPoint = {1,1};
		
		Assert.assertTrue(visited(newPoint));

		
	}
	
	public boolean visited(Integer[] newPoint){
		for (Integer[] visitedPoint : visitedPoints.keySet()) {
			if(newPoint[0]==visitedPoint[0]&&newPoint[1]==visitedPoint[1]){
				return true;		
			}
		}
		return false;
	}
	

}

