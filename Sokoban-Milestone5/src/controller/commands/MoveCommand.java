package controller.commands;

import javafx.beans.property.StringProperty;
import model.IModel;

/**
 * <h1>MoveCommand</h1>
 * activate the move method in the model
 */

public class MoveCommand extends Command {
	
	private IModel model;
	private StringProperty countSteps;
	
	public MoveCommand(IModel model,StringProperty countSteps) {
		this.model=model;
		this.countSteps=countSteps;
	}

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}


	/**
	 * activate the move in the model
	 */
	@Override
	public void exceute() {
		this.model.move(getParams());
		int steps=this.model.getSteps();
		this.countSteps.set(""+(steps));
	}
	

}
