package model.policy;

import db.Level;
/**
 *<h1> General Sokoban Policy</h1>
 *defines the common data of all Sokoban's Policies
 */

public abstract class GeneralSokobanPolicy implements ISokobanPolicy { 

	Level level;
	
	public GeneralSokobanPolicy() {
		this.level=null;
	}
	
	public GeneralSokobanPolicy(Level level) {
		this.level=level;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	
}
