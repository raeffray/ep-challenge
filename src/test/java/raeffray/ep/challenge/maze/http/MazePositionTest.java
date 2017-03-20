package raeffray.ep.challenge.maze.http;

import org.junit.Assert;
import org.junit.Test;

import raeffray.ep.challenge.maze.domain.MazePosition;
import raeffray.ep.challenge.maze.domain.NavigationState;

public class MazePositionTest {
	
	@Test
	public void testConfliectedPoint_true(){
		
		MazePosition mazePosition = new MazePosition();
		
		mazePosition.setNorth(NavigationState.UNEXPLORED);
		mazePosition.setEast(NavigationState.BLOCKED);
		mazePosition.setWest(NavigationState.UNEXPLORED);
		mazePosition.setSouth(NavigationState.BLOCKED);
				
		Assert.assertEquals(2, mazePosition.retrieveUnblockedDirections().size());
		
	}
	@Test
	public void testConfliectedPoint_false(){
		
		MazePosition mazePosition = new MazePosition();
		
		mazePosition.setNorth(NavigationState.VISITED);
		mazePosition.setEast(NavigationState.VISITED);
		mazePosition.setWest(NavigationState.BLOCKED);
		mazePosition.setSouth(NavigationState.UNEXPLORED);
		
		Assert.assertEquals(1, mazePosition.retrieveUnblockedDirections().size());
		
	}
}
