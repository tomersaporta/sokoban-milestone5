package controller.commands;

import model.IModel;

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
