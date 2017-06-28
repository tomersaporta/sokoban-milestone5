package model.data.handle;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import db.Level;
/**
 *<h1>Object Level Loader</h1> 
 *save level into XML file
 */

public class MyXMLLevelSaver implements LevelSaver{

	/**
	 * save level into the OutputStream (xml file)
	 */
	@Override
	public void saveLevel(Level level, OutputStream out) throws IOException {
		
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(out));
		encoder.writeObject(level);
		//System.out.println("XML level saved!");
		encoder.close();
	}
}
