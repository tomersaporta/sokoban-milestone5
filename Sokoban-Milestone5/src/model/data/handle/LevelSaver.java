package model.data.handle;

import java.io.IOException;
import java.io.OutputStream;

import db.Level;
/**
 * <h1>Level Saver interface</h1>
 * defines the behavior that all the level saver needs to implement
 *
 */
public interface LevelSaver  {
	public void saveLevel(Level level,OutputStream out) throws IOException;
	
}
