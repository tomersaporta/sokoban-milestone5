package controller.commands;

import model.IModel;
/**
 * <h1>AddUserCommand</h1>
 *activate the addUser method in the model
 */
public class AddUserCommand extends Command {

	private IModel model;
	
	public AddUserCommand(IModel model) {
		this.model = model;
	}

	@Override
	public void exceute() {
		this.model.addUser(getParams());
	}

}
