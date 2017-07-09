package controller.commands;
/**
 * <h1>Command</h1>
 * Abstract class which defines the common commands' data 
 */
public abstract class Command implements ICommand {
	
	private String params;

	public String getParams() {
		return params;
	}
	
	/**
	 * Initialize the parameters necessary for the receiver
	 */
	@Override
	public void setParams(String params) {
		this.params=params;

	}

}
