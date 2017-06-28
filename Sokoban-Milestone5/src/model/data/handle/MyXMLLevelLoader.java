package model.data.handle;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import db.Level;
/**
 *<h1>Object Level Loader</h1> 
 *create new level using XML file
 */
public class MyXMLLevelLoader  implements LevelLoader{

	/**
	 * load level using the InputStream (xml file)
	 */
	@Override
	public Level loadLevel(InputStream file) throws IOException {
		
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(file));
		Level level = (Level)decoder.readObject();
		
		//System.out.println("XML file loaded");
		
		decoder.close();
		
		return level;
	}
	

}
