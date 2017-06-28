package controller.commands;

import model.IModel;

/**
 * <h1>Save Level Command<h1>
 * activate the SaveLevel in the model
 */
public class SaveLevelCommand extends Command {

	private IModel model;
	
	public SaveLevelCommand(IModel model) {
		this.model=model;
	}

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}

	/**
	 * activate the SaveLevel in the model
	 */
	@Override
	public void exceute() {
		this.model.SaveLevel(getParams());				
	}
	
	
		
}
