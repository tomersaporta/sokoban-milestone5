package controller.commands;

import model.IModel;
import view.IView;


/**
 * <h1>ShowRecordsCommand<h1>
 * activate the showRecords method in the view
 */
public class ShowRecordsCommand extends Command {

	private IView view;
	private IModel model;
	
	
	public ShowRecordsCommand(IView view, IModel model) {
		this.view = view;
		this.model = model;
	}


	@Override
	public void exceute() {
		this.view.showRecords(this.model.getRecordsList());
	}

}
