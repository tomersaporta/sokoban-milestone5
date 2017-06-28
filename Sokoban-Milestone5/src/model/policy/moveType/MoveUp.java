package model.policy.moveType;

import sokoElements.Position;

public class MoveUp implements IMoveType {

	/**
	 * return the decreased position (x-1,y)
	 */
	@Override
	public Position getNextPosition(Position pos) {
		return new Position(pos.getX()-1, pos.getY());
	}

}
