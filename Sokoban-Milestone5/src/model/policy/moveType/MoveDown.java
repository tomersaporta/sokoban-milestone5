package model.policy.moveType;

import sokoElements.Position;

public class MoveDown implements IMoveType {
	/**
	 * return the increased position (x+1,y)
	 */
	@Override
	public Position getNextPosition(Position pos) {
		return new Position(pos.getX()+1, pos.getY());
	}

}
