package raeffray.ep.challenge.maze.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import raeffray.ep.challenge.maze.domain.Direction;
import raeffray.ep.challenge.maze.domain.MazePosition;
import raeffray.ep.challenge.maze.http.exception.HttpRequestException;

public class MazeExplorer {

	private MazeInvoker invoker = null;

	private static Logger logger = LoggerFactory.getLogger(MazeExplorer.class);

	Map<Integer[], String> visitedPoints = new HashMap<Integer[], String>();

	// going to use LIFO access strategy
	private LinkedList<MazePosition> disjunctions;

	private int stepCounter;

	public MazeExplorer() {
		invoker = new MazeInvoker();
		disjunctions = new LinkedList<MazePosition>();
		stepCounter = 1;
	}

	public MazePosition move() throws HttpRequestException {
		MazePosition mazePosition = invoker.initialize();

		List<Direction> directions = mazePosition.retrieveUnblockedDirections();

		for (Direction direction : directions) {
			move(direction);
		}
		return mazePosition;

	}

	public MazePosition move(Direction direction) throws HttpRequestException {

		MazePosition mazePosition = invoker.move(direction);

		stepCounter++;

		logger.info("step: {} coord: [{},{}] N: {} S: {}: E: {} W: {} Note: {}", stepCounter, mazePosition.getX(),
				mazePosition.getY(), mazePosition.getNorth(), mazePosition.getSouth(), mazePosition.getEast(),
				mazePosition.getWest(), mazePosition.getNote());
		
		if(mazePosition.isAtEnd()){
			disjunctions.clear();
			return mazePosition;
		}

		if (mazePosition.isWall()) {
			boolean goUpperDisjunction = true;
			while (goUpperDisjunction) {
				MazePosition disjunction = disjunctions.getLast();

				mazePosition = invoker.jump(disjunction.getX(), disjunction.getY());

				logger.info("disj: coord: [{},{}] N: {} S: {}: E: {} W: {} Note: {}", mazePosition.getX(),
						mazePosition.getY(), mazePosition.getNorth(), mazePosition.getSouth(), mazePosition.getEast(),
						mazePosition.getWest(), mazePosition.getNote());

				if (mazePosition.isWall()) {
					disjunctions.removeLast();
					logger.info("disjunction [{},{}] removed", disjunction.getX(), disjunction.getY());
					continue;
				}
				goUpperDisjunction = false;
			}

		}

		List<Direction> directions = mazePosition.retrieveUnblockedDirections();

		if (directions.size() > 1) {
			disjunctions.add(mazePosition);
		}

		for (Direction childrenDirection : directions) {
			if (!mazePosition.visitedDirection(childrenDirection)) {
				mazePosition.setVisitedDirection(childrenDirection);
				return move(childrenDirection);
			}
		}

		return mazePosition;
	}

	public int getStepCounter() {
		return stepCounter;
	}
}
