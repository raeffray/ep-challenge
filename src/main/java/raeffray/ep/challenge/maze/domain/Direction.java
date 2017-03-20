package raeffray.ep.challenge.maze.domain;

public enum Direction {

	SOUTH(0), NORTH(1), EAST(2), WEST(3);

	private int index;

	private Direction(int index){
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}

}
