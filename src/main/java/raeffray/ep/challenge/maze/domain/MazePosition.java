package raeffray.ep.challenge.maze.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;

import raeffray.ep.challenge.maze.helper.Transformable;

import org.codehaus.jackson.annotate.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = NavigationState.class, name = "north"),
		@Type(value = NavigationState.class, name = "east"), @Type(value = NavigationState.class, name = "south"),
		@Type(value = NavigationState.class, name = "west"), })
public class MazePosition implements Transformable {

	private String mazeGuid;

	private String note;

	private boolean atEnd;

	private boolean previouslyVisited;

	private NavigationState north;

	private NavigationState east;

	private NavigationState south;

	private NavigationState west;

	private Map<Direction, Boolean> visitedDirections;

	private int x;

	private int y;

	public MazePosition() {
		visitedDirections = new HashMap<Direction, Boolean>();
	}

	public String getMazeGuid() {
		return mazeGuid;
	}

	public void setMazeGuid(String mazeGuid) {
		this.mazeGuid = mazeGuid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isAtEnd() {
		return atEnd;
	}

	public void setAtEnd(boolean atEnd) {
		this.atEnd = atEnd;
	}

	public boolean isPreviouslyVisited() {
		return previouslyVisited;
	}

	public void setPreviouslyVisited(boolean previouslyVisited) {
		this.previouslyVisited = previouslyVisited;
	}

	public NavigationState getNorth() {
		return north;
	}

	public void setNorth(NavigationState north) {
		this.north = north;
	}

	public NavigationState getEast() {
		return east;
	}

	public void setEast(NavigationState east) {
		this.east = east;
	}

	public NavigationState getSouth() {
		return south;
	}

	public void setSouth(NavigationState south) {
		this.south = south;
	}

	public NavigationState getWest() {
		return west;
	}

	public void setWest(NavigationState west) {
		this.west = west;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public List<Direction> retrieveUnblockedDirections() {

		List<Direction> directions = new ArrayList<Direction>();

		if (getNorth().equals(NavigationState.UNEXPLORED)) {
			directions.add(Direction.NORTH);
		}
		if (getSouth().equals(NavigationState.UNEXPLORED)) {
			directions.add(Direction.SOUTH);
		}
		if (getEast().equals(NavigationState.UNEXPLORED)) {
			directions.add(Direction.EAST);
		}
		if (getWest().equals(NavigationState.UNEXPLORED)) {
			directions.add(Direction.WEST);
		}

		return directions;
	}

	public boolean isWall() {
		if (!getEast().equals(NavigationState.UNEXPLORED) && !getWest().equals(NavigationState.UNEXPLORED)
				&& !getSouth().equals(NavigationState.UNEXPLORED) && !getNorth().equals(NavigationState.UNEXPLORED)) {
			return true;
		}
		return false;
	}

	public void setVisitedDirection(Direction direction) {
		visitedDirections.put(direction, true);
	}

	public boolean visitedDirection(Direction direction) {
		return visitedDirections.get(direction) != null ? visitedDirections.get(direction) : false;
	}

}
