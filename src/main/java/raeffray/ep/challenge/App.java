package raeffray.ep.challenge;

import raeffray.ep.challenge.maze.http.exception.HttpRequestException;
import raeffray.ep.challenge.maze.service.MazeExplorer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws HttpRequestException {

		MazeExplorer mazeExplorer = new MazeExplorer();
		
		mazeExplorer.move();

	}
}
