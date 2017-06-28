package model.policy.moveType;

import sokoElements.Position;

public class MoveRight implements IMoveType {

	/**
	 * return the increased position (x,y+1)
	 */
	@Override
	public Position getNextPosition(Position pos) {
		return new Position(pos.getX(), pos.getY()+1);
	}

}
