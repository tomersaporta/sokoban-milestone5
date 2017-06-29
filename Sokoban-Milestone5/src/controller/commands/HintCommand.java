package controller.commands;

import model.IModel;

public class HintCommand extends Command{

	private IModel model;
	
	public HintCommand(IModel model) {
		this.model=model;
	}
	
	@Override
	public void exceute() {
		this.model.getHint();
	}

}
