package raeffray.ep.challenge.maze.http;

import org.junit.Before;
import org.junit.Test;

import raeffray.ep.challenge.maze.http.exception.HttpRequestException;
import raeffray.ep.challenge.maze.service.MazeExplorer;

public class MazeExplorerTest {

	MazeExplorer mazeExplorer;

	@Before
	public void configure() {

		mazeExplorer = new MazeExplorer();
	}

	//@Test
	public void moveTest() {

		try {
			mazeExplorer.move();
		} catch (HttpRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
