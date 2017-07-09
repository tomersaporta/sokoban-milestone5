package controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import controller.commands.ICommand;
/**
 * <h1>Controller</h1>
 * General controller who manage commands in a blocking queue
 *
 */
public class Controller {
	
	private BlockingQueue<ICommand> commandsQueue;
	private boolean stop;
	
	public Controller() {
		this.commandsQueue=new ArrayBlockingQueue<ICommand>(20);
		this.stop=false;
	}
	
	public BlockingQueue<ICommand> getCommandsQueue() {
		return commandsQueue;
	}

	public void setCommandsQueue(BlockingQueue<ICommand> commandsQueue) {
		this.commandsQueue = commandsQueue;
	}

	public void insertCommand(ICommand command) throws InterruptedException{
		this.commandsQueue.put(command);
	}
	/**
	 * Poll(blocking call) the commands from the queue and execute them.
	 * Runs in a different thread
	 */
	public void start(){
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!stop){
					ICommand command;
					try {
						command = getCommandsQueue().poll(1, TimeUnit.SECONDS);
						if(command!=null)
							command.exceute();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}
	
	/**
	 * Stops the thread who poll commands from the queue
	 */
	public void stop(){
		this.stop=true;
		System.out.println("Goodbye");
	}
}

