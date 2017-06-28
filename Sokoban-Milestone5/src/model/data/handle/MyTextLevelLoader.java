package model.data.handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import db.Level;
import sokoElements.ElementMovableFactory;
import sokoElements.ElementUnmovableFactory;
import sokoElements.Player;
import sokoElements.Position;
import sokoElements.Target;
import sokoElements.movable;
import sokoElements.unmovable;
/**
 *<h1>Object Level Loader</h1> 
 *create new level using text file
 */
public class MyTextLevelLoader extends CommonLevelLoader implements LevelLoader {
	
	/**
	 * load level using the InputStream (txt file)
	 */
	@Override
	public Level loadLevel(InputStream file) throws IOException  {
		
		//read the text from the file
		BufferedReader in = new BufferedReader(new InputStreamReader(file));
		String line;
		ArrayList<String> listOfLines=new ArrayList<String>();
        
		int col=0;
        int row=0;
        
        String levelID=in.readLine();
        //TODO-line is empty
        
       	while ((line = in.readLine()) != null) {
            if(line.length()>col)
            	col=line.length();
            row++;
        	listOfLines.add(line);
        }
		
		//create 2D array of char
		char[][] charArr=new char[row][col];
		for (int i=0;i<row;i++)
			charArr[i]=listOfLines.get(i).toCharArray();
			
		ElementMovableFactory movableFactory=new ElementMovableFactory();
		ElementUnmovableFactory unmovableFactory=new ElementUnmovableFactory();
		
		Level newLevel=new Level(row,col,levelID);
		for(int i=0;i<row;i++)
		{
			for (int j=0;j<col;j++)
			{
				switch(getTypeOfElement().get(charArr[i][j])){
					case "movable":
						movable mv=movableFactory.createMovable(charArr[i][j], new Position(i,j));
						newLevel.setMovearrInIndex(mv);
						if(mv instanceof Player)
							newLevel.addToListPlayer(mv);  //Player
						else
							newLevel.addToListBox(mv); //Box
						break;
					case "unmovable":
						unmovable unm=unmovableFactory.createUnmovable(charArr[i][j], new Position(i,j));
						newLevel.setBackgroundInIndex(unm); // Background
						if(unm instanceof Target)
							newLevel.addToListTarget(unm); //Target
						break;
					default:
						throw new IOException("Invalid file input");
				}
			}
		}
		
		newLevel.setInitBoard(newLevel.getLevelBored());
			
		return newLevel;
	}

}
