package controller.commands;

import model.IModel;

public class RestartCommand extends Command{

	private IModel model;
	
	public RestartCommand(IModel model) {
		this.model=model;
	}
	
	@Override
	public void exceute() {
		this.model.restart();
	}

}
