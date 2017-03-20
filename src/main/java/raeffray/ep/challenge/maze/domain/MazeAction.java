package raeffray.ep.challenge.maze.domain;

public enum MazeAction {

	INITIALIZE ("http://www.epdeveloperchallenge.com/api/init"),
	MOVE ("http://www.epdeveloperchallenge.com/api/move"),
	JUMP ("http://www.epdeveloperchallenge.com/api/jump"),
	CURRENT_CELL ("http://www.epdeveloperchallenge.com/api/currentCell");
	
	private String url;

	MazeAction(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
}
