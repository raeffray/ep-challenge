package raeffray.ep.challenge.maze.domain;

import raeffray.ep.challenge.maze.helper.Transformable;

public class Position implements Transformable{
	
	private MazePosition currentCell;

	public MazePosition getCurrentCell() {
		return currentCell;
	}

	public void setCurrentCell(MazePosition currentCell) {
		this.currentCell = currentCell;
	}
	
	

}
