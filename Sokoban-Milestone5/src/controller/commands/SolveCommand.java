package controller.commands;

import model.IModel;

/**
 * <h1>SolveCommand<h1>
 * activate the solve method in the model
 */
public class SolveCommand extends Command {

	private IModel model;
	
	public SolveCommand(IModel model) {
		this.model=model;
	}
	
	@Override
	public void exceute() {
		this.model.getSolution();
	}

}
