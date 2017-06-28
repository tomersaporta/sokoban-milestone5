package controller.commands;

import model.IModel;

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
