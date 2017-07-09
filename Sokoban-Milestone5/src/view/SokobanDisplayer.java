package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * Sokoban Canvas
 * Drawing the level board
 */
public class SokobanDisplayer extends Canvas{
	
	char[][] levelData;
	int row;
	int col;
	
	private StringProperty wallFileName;
	private StringProperty playerFileName;
	private StringProperty boxFileName;
	private StringProperty targetFileName;
	private StringProperty backgrounFileName;
	
	
	public SokobanDisplayer() {
		this.wallFileName= new SimpleStringProperty();
		this.playerFileName= new SimpleStringProperty();
		this.boxFileName= new SimpleStringProperty();
		this.backgrounFileName= new SimpleStringProperty();
		this.targetFileName= new SimpleStringProperty();
		
		this.col=0;
		this.row=0;
	}
	
	public void setLevelData(char[][] levelData) {
		
		this.row=levelData.length;
		this.col=levelData[0].length;
		this.levelData = levelData;
		
		redraw();
	}
	
	public String getWallFileName() {
		return wallFileName.get();
	}


	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}


	public String getPlayerFileName() {
		return playerFileName.get();
	}


	public void setPlayerFileName(String playerFileName) {
		this.playerFileName.set(playerFileName); ;
	}


	public String getBoxFileName() {
		return boxFileName.get();
	}


	public void setBoxFileName(String boxFileName) {
		this.boxFileName.set(boxFileName);;
	}


	public String getTargetFileName() {
		return targetFileName.get();
	}


	public void setTargetFileName(String targetFileName) {
		this.targetFileName.set(targetFileName);;
	}


	public String getBackgrounFileName() {
		return backgrounFileName.get();
	}

	
	public void setBackgrounFileName(String backgrounFileName) {
		this.backgrounFileName.set(backgrounFileName);;
	}

	/**
	 * 
	 * Drawing the initial level board
	 */
	public void redrawStart(){
		
		double W= getWidth();
		double H= getHeight();
		Image startMenu= null;
		
		GraphicsContext gc =this.getGraphicsContext2D();
		try {
			
			gc.clearRect(0, 0, W, H);
			
			startMenu=new Image(new FileInputStream("./resources/elements/backMain.png"));
			gc.drawImage(startMenu, 0, 0,W, H);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	/**
	 * Drawing the level board
	 */
	public void redraw(){
		
		if(this.levelData!=null){
			
			//the canvas sizes
			double W= getWidth();
			double H= getHeight();
			
			//our items size- all the cells will be in the same size
			double w= W/Math.max(this.col, this.row);
			double h=H/Math.max(this.col, this.row);
			
			GraphicsContext gc =this.getGraphicsContext2D();
			
			Image wall = null;
			Image box = null;
			Image player = null;
			Image background = null;
			Image target = null;

			
			try {
				wall= new Image(new FileInputStream(getWallFileName()));
				box= new Image(new FileInputStream(getBoxFileName()));
				player= new Image(new FileInputStream(getPlayerFileName()));
				background= new Image(new FileInputStream(getBackgrounFileName()));
				target= new Image(new FileInputStream(getTargetFileName()));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gc.clearRect(0, 0, W, H);
			
			for(int i=0;i<this.row;i++)
				for (int j=0;j<this.col;j++){
					switch (levelData[i][j]){
					
					case '#':
						gc.drawImage(wall, j*w, i*h, w, h);
						break;
						
					case '@':
						gc.drawImage(box, j*w, i*h, w, h);
						break;
						
					case 'A':
						gc.drawImage(player, j*w, i*h, w, h);
						break;
						
					case ' ':
						gc.drawImage(background, j*w, i*h, w, h);
						break;
					
					case 'o':
						gc.drawImage(target, j*w, i*h, w, h);
						break;
						
					default:
						gc.setFill(Color.BLUE);
						break;
					}
				}
			
		}
		
	}
	
}
