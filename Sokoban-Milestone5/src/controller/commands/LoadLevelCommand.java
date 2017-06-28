package controller.commands;

import model.IModel;

/**
 * <h1>LoadLevelCommand</h1>
 *activate the LoadLevel in the model
 */
public class LoadLevelCommand extends Command {

	private IModel model;

	public LoadLevelCommand(IModel model) {
		this.model=model;
	}
	

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}
	
	/**
	 * activate the suitable method in the model
	 */
	@Override
	public void exceute(){
		
		this.model.LoadLevel(getParams());
	}

		
}
