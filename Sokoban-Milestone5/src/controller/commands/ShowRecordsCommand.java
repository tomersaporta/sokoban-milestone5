package controller.commands;

import model.IModel;
import view.IView;

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
