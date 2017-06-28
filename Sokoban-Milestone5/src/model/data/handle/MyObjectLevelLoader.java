package model.data.handle;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;


import db.Level;
/**
 *<h1>Object Level Loader</h1> 
 *create new level using object file
 */
public class MyObjectLevelLoader implements LevelLoader {

	/**
	 * load level using the InputStream (obj file)
	 */
	@Override
	public Level loadLevel(InputStream file) throws IOException, ClassNotFoundException {
		
		ObjectInputStream obj = new ObjectInputStream(new BufferedInputStream(file));
		Level level = (Level)obj.readObject();
		obj.close();
		
		//System.out.println("OBJ file loaded!");
		
		return level;
	}

	
}
