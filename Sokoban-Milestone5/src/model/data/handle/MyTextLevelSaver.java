package model.data.handle;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import db.Level;
import sokoElements.Box;
import sokoElements.Player;
import sokoElements.movable;
/**
 *<h1>Object Level Loader</h1> 
 *save level into text file
 */
public class MyTextLevelSaver implements LevelSaver {

	/**
	 * save level into the OutputStream (txt file)
	 */
	@Override
	public void saveLevel(Level myLevel, OutputStream file) throws IOException {
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(file));
		out.write(myLevel.getLevelID());
		out.newLine();
		for(int i=0;i<myLevel.getRow() ;i++){
			for (int j=0;j<myLevel.getCol();j++){
				movable m = myLevel.getMovearr()[i][j];
				if(m instanceof Player || m instanceof Box)
					out.write(m.getType());
				else
					out.write(myLevel.getBackground()[i][j].getType());
				
			}
			if (i == myLevel.getRow() - 1)
				break;
			
			out.newLine();
		}
		
		out.close();			
	}

}
