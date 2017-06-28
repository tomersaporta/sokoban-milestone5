package controller.commands;

import model.IModel;

public class AddRecordCommand extends Command {

	private IModel model;
	
	public AddRecordCommand(IModel model) {
		super();
		this.model = model;
	}

	@Override
	public void exceute() {
		this.model.addRecord(getParams());
	}

}
