package controller.commands;

import model.IModel;

/**
 * <h1>QueryCommand</h1>
 * activate the query method in the model which sends the query to the DB
 */
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
