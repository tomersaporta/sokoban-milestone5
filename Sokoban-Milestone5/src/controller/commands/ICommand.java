package controller.commands;


/**
 * <h1>command interface</h1>
 * defines the behavior that all the command needs to implement
 *
 */
public interface ICommand {
	public void exceute();
	void setParams(String params);
}
