package model.policy;

import sokoElements.Player;
import model.policy.moveType.IMoveType;
/**
 * <h1>Sokoban Policy interface</h1>
 * defines the behavior that all the Sokoban's policies needs to implement
 *
 */
public interface ISokobanPolicy {
	
	public boolean checkPolicy(Player p, IMoveType moveType);
}
