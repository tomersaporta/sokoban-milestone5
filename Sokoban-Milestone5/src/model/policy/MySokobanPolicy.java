package model.policy;

import db.Level;
import sokoElements.Box;
import sokoElements.Floor;
import sokoElements.GeneralElement;
import sokoElements.Player;
import sokoElements.Position;
import sokoElements.unmovable;
import model.policy.moveType.IMoveType;

/**
 * <h1>My Sokoban Policy</h1>
 * defines the policy:
 * 1. actor can't walk thought walls
 * 2. actor can't push box on another box/wall
 * 3. actor can't pull boxes
 */

public class MySokobanPolicy extends GeneralSokobanPolicy {

	public MySokobanPolicy(Level level) {
		super(level);
	}
	/**
	 * The method check if the actor can move according to the moveType
	 * If the action is legal, it update the level in accordance
	 */
	@Override
	public boolean checkPolicy(Player player, IMoveType moveType) {
		
		GeneralElement elementInNextPosition=null;
		GeneralElement elementInBoxNextPosition=null;
		
		//get the next Position step
		Position nextPosition= moveType.getNextPosition(player.getPosition());
		
		if(getLevel().isValidPosition(nextPosition))
			elementInNextPosition=getLevel().getElementInPosition(nextPosition);
		else return false;
		
		if(elementInNextPosition instanceof unmovable){
			if(((unmovable) elementInNextPosition).isStepable()){
				getLevel().upDateLevelPlayerMoves(player, nextPosition);
				return true;
			}
				
		}
		
		else if (elementInNextPosition instanceof Box)
		{
			Position boxNextPosition= moveType.getNextPosition(elementInNextPosition.getPosition());
			if(getLevel().isValidPosition(boxNextPosition))
				elementInBoxNextPosition=getLevel().getElementInPosition(boxNextPosition);
			
			else return false;
				
			if(elementInBoxNextPosition instanceof Floor){
				getLevel().upDateLevelPlayerBoxMoves(player,(Box)elementInNextPosition, boxNextPosition);
				return true;
			}
		}
		return false;
	
	}

	
}
