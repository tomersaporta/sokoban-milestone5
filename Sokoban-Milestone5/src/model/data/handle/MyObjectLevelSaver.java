package model.data.handle;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import db.Level;
/**
 *<h1>Object Level Loader</h1> 
 *save level into object file
 */
public class MyObjectLevelSaver implements LevelSaver {

	/**
	 * save level into the OutputStream (obj file)
	 */
	@Override
	public void saveLevel(Level level, OutputStream out) throws IOException {
		
		ObjectOutputStream obj = new ObjectOutputStream(new BufferedOutputStream(out));
		obj.writeObject(level);
		obj.close();
		
		//System.out.println("OBJ file saved!");
		
	}

}
