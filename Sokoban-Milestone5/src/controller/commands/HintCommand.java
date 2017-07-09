package controller.commands;

import model.IModel;
/**
 * <h1>HintCommand</h1>
 * activate the getHint method in the model
 */
public class HintCommand extends Command{

	private IModel model;
	
	public HintCommand(IModel model) {
		this.model=model;
	}
	
	@Override
	public void exceute() {
		this.model.getHint();
	}

}
