package controller.commands;

import model.IModel;
/**
 * <h1>AddRecordCommand</h1>
 *activate the addRecord method in the model
 */
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
