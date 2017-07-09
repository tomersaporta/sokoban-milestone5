package view;

import javafx.scene.input.KeyCode;
/**
 * <h1>ViewSettings</h1>
 * Defined the view settings
 *
 */
public class ViewSettings {
	
	private KeyCode moveUp;
	private KeyCode moveDown;
	private KeyCode moveLeft;
	private KeyCode moveRight;
	
	public ViewSettings() {
		this.moveDown=KeyCode.DOWN;
		this.moveLeft=KeyCode.LEFT;
		this.moveUp=KeyCode.UP;
		this.moveRight=KeyCode.RIGHT;
	}
	
	

	public KeyCode getMoveUp() {
		return moveUp;
	}



	public void setMoveUp(KeyCode moveUp) {
		this.moveUp = moveUp;
	}



	public KeyCode getMoveDown() {
		return moveDown;
	}

	public void setMoveDown(KeyCode moveDown) {
		this.moveDown = moveDown;
	}

	public KeyCode getMoveLeft() {
		return moveLeft;
	}

	public void setMoveLeft(KeyCode moveLeft) {
		this.moveLeft = moveLeft;
	}

	public KeyCode getMoveRight() {
		return moveRight;
	}

	public void setMoveRight(KeyCode moveRight) {
		this.moveRight = moveRight;
	}

	
}
