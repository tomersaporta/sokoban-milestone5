package controller.commands;

import model.IModel;
import view.IView;
/**
 * <h1>DisplayGUICommand</h1>
 * activate the displayGui function in the view using the updated Level from the model
 */
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
