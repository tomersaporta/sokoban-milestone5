package model.data.handle;

import java.io.IOException;
import java.io.InputStream;

import db.Level;

/**
 * <h1>Level Saver interface</h1>
 * defines the behavior that all the level saver needs to implement
 *
 */

public interface LevelLoader {
	
	public Level loadLevel(InputStream file) throws IOException, ClassNotFoundException;
	
}
