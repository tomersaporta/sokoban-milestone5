package controller.commands;

import model.IModel;

public class QueryCommand extends Command {

	private IModel model;
	
	public QueryCommand(IModel model) {
		this.model = model;
	}

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}

	@Override
	public void exceute() {
		this.model.dbQuery(getParams());
	}

}
