package controller.commands;

import model.IModel;
import view.IView;

public class DisplayGUICommand extends Command {

	private IView view;
	private IModel model;
	 
	public DisplayGUICommand(IModel model,IView view) {
		this.view=view;
		this.model=model;
	}
	@Override
	public void exceute() {
		this.view.displayGUI(this.model.getCurrentLevel());
	}


}
