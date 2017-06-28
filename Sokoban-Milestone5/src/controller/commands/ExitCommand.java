package controller.commands;

import controller.Controller;
import controller.server.MyServer;

/**
 *<h1>Exit Level Command</h1> 
 *close all the files, release resources
 */
public class ExitCommand extends Command{

	private Controller controller;
	private MyServer server;
	
	public ExitCommand(Controller controller, MyServer server) {
		this.controller= controller;
		this.server= server;
	}
	
	
	/**
	 * close all the files, release resources
	 */
	@Override
	public void exceute() {
		this.controller.stop();
		if(this.server!=null)
			this.server.stop();
	}
	
}
