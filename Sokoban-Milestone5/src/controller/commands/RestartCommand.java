package controller.commands;

import model.IModel;

/**
 * <h1>QueryCommand</h1>
 * activate the query method in the model which sends the query to the DB
 */
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
