package controller.commands;

import controller.server.SokobanClientHandler;
import view.IView;

public class ErrorCommand extends Command {

	private IView view;
	private SokobanClientHandler ch;
	
	public ErrorCommand(IView view, SokobanClientHandler ch) {
		this.ch=ch;
		this.view= view;
	}
	
	@Override
	public void exceute() {
		
      	this.view.displayError(getParams());
      	if(ch!=null)
      		this.ch.insertToQueue(getParams());
      	
	}

}
